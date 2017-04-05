package com.devils.hr.controllers.apis;

import com.devils.hr.constants.Constants;
import com.devils.hr.constants.Status;
import com.devils.hr.pojo.roles.Manager;
import com.devils.hr.pojo.roles.Student;
import com.devils.hr.responses.RespFactory;
import com.devils.hr.responses.RespWrapper;
import com.devils.hr.service.ManagerService;
import com.devils.hr.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
            return RespFactory.getInstance().createRespErrorWithCustomeMsg("只有管理员才能操作");
        }

        if(Constants.MANAGER_ROLE_READ.equals(manager.getRole())){
            return RespFactory.getInstance().createRespErrorWithCustomeMsg("没有足够的权限");
        }

        Student existStudent = studentService.findByIDNumber(IDNumber);

        if(existStudent != null && !StringUtils.isEmpty(existStudent.getId())){
            return RespFactory.getInstance().createRespErrorWithCustomeMsg("学生已存在,请检查学生姓名和身份证号填写是否有误");
        }

        Student student = new Student();
        student.setStatus(Status.STUDENT_INACTIVATED);
        student.setName(name);
        student.setGender(gender);
        student.setBirthday(birthday);
        student.setIDNumber(IDNumber);
        student.setNumber(studentService.generateNumber());
        student.setPassword(Constants.INIT_PASSWORD_MD5);

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

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public RespWrapper login(@RequestParam(required = false) long   number,
                             @RequestParam(required = false) String phone,
                             @RequestParam(required = true)  String password){
        if(number < 1 && StringUtils.isEmpty(phone)){
            return RespFactory.getInstance().createRespErrorWithCustomeMsg("请输入手机号或学号");
        }

        if(StringUtils.isEmpty(password)){
            return RespFactory.getInstance().createRespErrorWithCustomeMsg("请输入密码");
        }

        Student student = studentService.findByNumberOrPhone(number, phone);

        if(student == null || StringUtils.isEmpty(student.getId())){
            return RespFactory.getInstance().createRespErrorWithCustomeMsg("未找到该学生相关信息，检查手机号或学号填写是否有误");
        }

        if(!password.equals(student.getPassword())){
            return RespFactory.getInstance().createRespErrorWithCustomeMsg("密码错误");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", student.getId());
        result.put("name", student.getName());

        return RespFactory.getInstance().createRespSuccess(result);
    }
}
