package com.devils.hr.controllers.apis;

import com.devils.hr.configs.AppConfig;
import com.devils.hr.pojo.roles.Manager;
import com.devils.hr.pojo.roles.Student;
import com.devils.hr.responses.RespFactory;
import com.devils.hr.responses.RespWrapper;
import com.devils.hr.querys.Page;
import com.devils.hr.responses.modules.StudentResp;
import com.devils.hr.service.ManagerService;
import com.devils.hr.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AndyL on 2017/4/2.
 */
@RestController
@RequestMapping("/api/v1/student")
public class StudentApiCtrl {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ManagerService managerService;

    /**
     * 创建学生信息
     * @param managerId 管理员ID
     * @param name      姓名
     * @param gender    性别  'f' or 'm'
     * @param birthday  出生  'yyyy-mm-dd'
     * @param IDNumber  身份证号
     * */
    @ApiOperation(value = "创建学生信息", notes = "创建学生信息 tips: 性别 => 'f' or 'm' 出生日期 => 'yyyy-mm-dd'")
    @RequestMapping(method = RequestMethod.POST)
    public RespWrapper createStudent(@RequestParam(required = true) String managerId,
                                     @RequestParam(required = true) String name,
                                     @RequestParam(required = true) String gender,
                                     @RequestParam(required = true) String birthday,
                                     @RequestParam(required = true) String IDNumber){
        if(StringUtils.isEmpty(managerId)){
            return RespFactory.getInstance().createRespParamsIsNull("managerId");
        }else if(StringUtils.isEmpty(name)){
            return RespFactory.getInstance().createRespParamsIsNull("name");
        }else if(StringUtils.isEmpty(gender)){
            return RespFactory.getInstance().createRespParamsIsNull("gender");
        }else if(StringUtils.isEmpty(birthday)){
            return RespFactory.getInstance().createRespParamsIsNull("birthday");
        }else if(StringUtils.isEmpty(IDNumber)){
            return RespFactory.getInstance().createRespParamsIsNull("IDNumber");
        }

        Manager manager = managerService.findOneById(managerId);
        if(manager == null || StringUtils.isEmpty(manager.getId())){
            return RespFactory.getInstance().createRespErrorWithCustomMsg("只有管理员才能操作");
        }

        if(Manager.ROLE_READ.equals(manager.getRole())){
            return RespFactory.getInstance().createRespErrorWithCustomMsg("没有足够的权限");
        }

        Student existStudent = studentService.findByIDNumber(IDNumber);

        if(existStudent != null && !StringUtils.isEmpty(existStudent.getId())){
            return RespFactory.getInstance().createRespErrorWithCustomMsg("学生已存在,请检查学生姓名和身份证号填写是否有误");
        }

        Student student = new Student();
        student.setStatus(Student.STATUS_INACTIVATED);
        student.setName(name);
        student.setGender(gender);
        student.setBirthday(birthday);
        student.setIDNumber(IDNumber);
        student.setNumber(studentService.generateNumber());
        student.setPassword(AppConfig.INIT_PASSWORD_MD5);

        Student newStudent = studentService.save(student);

        Map<String, Object> result = new HashMap<>();
        result.put("id", newStudent.getId());
        result.put("name", newStudent.getName());
        result.put("gender", newStudent.getGender());
        result.put("birthday", newStudent.getBirthday());
        result.put("number", String.valueOf(newStudent.getNumber()));
        result.put("IDNumber", newStudent.getIDNumber());

        return RespFactory.getInstance().createRespSuccess(result);
    }

    /**
     * @param number    学号
     * @param phone     手机号
     * @param password  密码
     * */
    @ApiOperation(value = "学生登陆", notes = "学生登陆")
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public RespWrapper login(@RequestParam(required = false) long   number,
                             @RequestParam(required = false) String phone,
                             @RequestParam(required = true)  String password){
        if(number < 1 && StringUtils.isEmpty(phone)){
            return RespFactory.getInstance().createRespErrorWithCustomMsg("请输入手机号或学号");
        }

        if(StringUtils.isEmpty(password)){
            return RespFactory.getInstance().createRespErrorWithCustomMsg("请输入密码");
        }

        Student student = studentService.findByNumberOrPhone(number, phone);

        if(student == null || StringUtils.isEmpty(student.getId())){
            return RespFactory.getInstance().createRespErrorWithCustomMsg("未找到该学生相关信息，检查手机号或学号填写是否有误");
        }

        if(!password.equals(student.getPassword())){
            return RespFactory.getInstance().createRespErrorWithCustomMsg("密码错误");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", student.getId());
        result.put("name", student.getName());

        return RespFactory.getInstance().createRespSuccess(result);
    }

    /**
     * 查询学生信息
     * @param id    学生 id
     * */
    @ApiOperation(value = "查询学生信息", notes = "根据 id 查询学生信息")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public RespWrapper findById(@PathVariable String id){
        Student student = studentService.findOneById(id);
        if(student == null || StringUtils.isEmpty(student.getId())){
            return RespFactory.getInstance().createRespErrorWithCustomMsg("未查到该学生的信息");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", student.getId());
        result.put("name", student.getName());
        result.put("number", student.getNumber());
        result.put("birthday", student.getBirthday());

        return RespFactory.getInstance().createRespSuccess(result);
    }

    /**
     * 学号顺序查询学生信息
     * @param cursor
     * @param count
     * @param skip
     * */
    @ApiOperation(value = "学号顺序查询所有学生", notes = "cursor 首次请求传 0 ，之后服务端会返回新的 cursor 供前段下次请求, count 每次请求消息条数，不传默认为20")
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public RespWrapper findByPageInNumber(@RequestParam(required = true,  defaultValue = "0") long cursor,
                                          @RequestParam(required = false, defaultValue = "0") int  count,
                                          @RequestParam(required = false, defaultValue = "0") int  skip){
        if(count  == 0) count  = AppConfig.defaultDataQueryCount;

        Page page = new Page(cursor, skip, count);
        List<Student> students = studentService.findByPageInNumber(page);
        Boolean isEnd = students == null || students.size() < count;

        Map<String, Object> result = new HashMap<>();
        if(students == null || students.size() < 1){
            result.put("isEnd", isEnd);
            result.put("cursor", 0);
            result.put("count", 0);
            return RespFactory.getInstance().createRespSuccess(result);
        }

        List<StudentResp> studentRespList = new ArrayList<>();
        students.forEach(student -> studentRespList.add(new StudentResp(student)));

        result.put("isEnd", isEnd);
        result.put("cursor", isEnd ? 0 : students.get(students.size() - 1).getNumber());
        result.put("count", studentRespList.size());
        result.put("students", studentRespList);

        return RespFactory.getInstance().createRespSuccess(result);
    }

    /**
     * 删除学生
     * @param id    学生 id
     * */
    @ApiOperation(value = "删除学生", notes = "根据 id 删除学生信息")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public RespWrapper deleteById(@PathVariable String id){
        Student student = studentService.findOneById(id);
        if(student == null || StringUtils.isEmpty(student.getId())){
            return RespFactory.getInstance().createRespErrorWithCustomMsg("未查到该学生");
        }

        studentService.deleteById(id);

        return RespFactory.getInstance().createRespSuccess();
    }
}
