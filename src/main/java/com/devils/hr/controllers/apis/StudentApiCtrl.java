package com.devils.hr.controllers.apis;

import com.devils.hr.configs.AppConfig;
import com.devils.hr.pojo.roles.Manager;
import com.devils.hr.pojo.roles.Student;
import com.devils.hr.querys.ListQueryResult;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.responses.RespFactory;
import com.devils.hr.responses.RespWrapper;
import com.devils.hr.responses.modules.StudentResp;
import com.devils.hr.service.ManagerService;
import com.devils.hr.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
            return RespWrapper.builder().missParams("managerId").build();
        }else if(StringUtils.isEmpty(name)){
            return RespWrapper.builder().missParams("name").build();
        }else if(StringUtils.isEmpty(gender)){
            return RespWrapper.builder().missParams("gender").build();
        }else if(StringUtils.isEmpty(birthday)){
            return RespWrapper.builder().missParams("birthday").build();
        }else if(StringUtils.isEmpty(IDNumber)){
            return RespWrapper.builder().missParams("IDNumber").build();
        }

        Manager manager = managerService.findOneById(managerId).getOne();
        if(manager == null || StringUtils.isEmpty(manager.getId())){
            return RespWrapper.builder().error("只有管理员才能操作").build();
        }

        if(Manager.ROLE_READ.equals(manager.getRole())){
            return RespWrapper.builder().error("没有足够的权限").build();
        }

        Student existStudent = studentService.findByIDNumber(IDNumber).getOne();

        if(existStudent != null && !StringUtils.isEmpty(existStudent.getId())){
            return RespWrapper.builder().error("学生已存在,请检查学生姓名和身份证号填写是否有误").build();
        }

        Student student = new Student();
        student.setStatus(Student.STATUS_INACTIVATED);
        student.setName(name);
        student.setGender(gender);
        student.setBirthday(birthday);
        student.setIDNumber(IDNumber);
        student.setNumber(studentService.generateNumber());
        student.setPassword(AppConfig.INIT_PASSWORD_MD5);

        SingleQueryResult<Student> singleQueryResult = studentService.save(student);

        return RespWrapper.builder()
                .success()
                .addCustomParam("student", singleQueryResult.getOne())
                .build();
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
            return RespWrapper.builder().missParams("请输入手机号或学号").build();
        }

        if(StringUtils.isEmpty(password)){
            return RespWrapper.builder().missParams("请输入密码").build();
        }

        SingleQueryResult<Student> singleQueryResult = studentService.findByNumberOrPhone(number, phone);
        Student student = singleQueryResult.getOne();

        if(student == null || StringUtils.isEmpty(student.getId())){
            return RespWrapper.builder().notFound("未找到该学生相关信息，检查手机号或学号填写是否有误").build();
        }

        if(!password.equals(student.getPassword())){
            return RespWrapper.builder().error("密码错误").build();
        }

        return RespWrapper.builder()
                .success()
                .addCustomParam("student", singleQueryResult.convertToResp(StudentResp.class))
                .build();
    }

    /**
     * 查询学生信息
     * @param id    学生 id
     * */
    @ApiOperation(value = "查询学生信息", notes = "根据 id 查询学生信息")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public RespWrapper findById(@PathVariable String id){
        SingleQueryResult<Student> singleQueryResult = studentService.findOneById(id);
        Student student = singleQueryResult.getOne();

        if(student == null || StringUtils.isEmpty(student.getId())){
            return RespWrapper.builder().notFound("未查到该学生的信息").build();
        }

        return RespWrapper.builder()
                .success()
                .addCustomParam("student", singleQueryResult.convertToResp(StudentResp.class))
                .build();
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

        ListQueryResult<Student> listQueryResult = studentService.findByPageInNumber(cursor, count, skip);

        return RespWrapper.builder()
                .success()
                .addCount(listQueryResult.getCount())
                .addTotalCount(listQueryResult.getTotalCount())
                .addIsEnd(listQueryResult.isEnd())
                .addCursor(listQueryResult.getLastElement() == null ? 0 : listQueryResult.getLastElement().getNumber())
                .addCustomParam("students", listQueryResult.convertToResp(StudentResp.class))
                .build();
    }

    /**
     * 删除学生
     * @param id    学生 id
     * */
    @ApiOperation(value = "删除学生", notes = "根据 id 删除学生信息")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public RespWrapper deleteById(@PathVariable String id){
        Student student = studentService.findOneById(id).getOne();
        if(student == null || StringUtils.isEmpty(student.getId())){
            return RespWrapper.builder().notFound("未查到该学生").build();
        }

        studentService.deleteById(id);

        return RespFactory.getInstance().createRespSuccess();
    }
}
