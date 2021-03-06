package com.devils.hr.controllers.apis;

import com.devils.hr.configs.AppConfig;
import com.devils.hr.pojo.records.RefStuPar;
import com.devils.hr.pojo.roles.Manager;
import com.devils.hr.pojo.roles.Parent;
import com.devils.hr.pojo.roles.Student;
import com.devils.hr.querys.ListQueryResult;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.request.StudentReqMod;
import com.devils.hr.responses.RespWrapper;
import com.devils.hr.responses.modules.StudentResp;
import com.devils.hr.service.ManagerService;
import com.devils.hr.service.ParentService;
import com.devils.hr.service.RefStuParService;
import com.devils.hr.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * Created by AndyL on 2017/4/2.
 */
@RestController
@Validated
@RequestMapping("/api/v1/student")
public class StudentApiCtrl {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ParentService parentService;

    @Autowired
    private RefStuParService refStuParService;

    /**
     * 创建学生信息
     * @param student 学生信息
     * */
    @ApiOperation(value = "创建学生信息", notes = "创建学生信息")
    @RequestMapping(method = RequestMethod.POST)
    public RespWrapper createStudent(@RequestBody StudentReqMod student){

        Manager manager = managerService.findOneById(student.getManagerId()).getOne();
        if(manager == null || StringUtils.isEmpty(manager.getId())){
            return RespWrapper.builder().error("只有管理员才能操作").build();
        }

        if(Manager.ROLE_READ.equals(manager.getRole())){
            return RespWrapper.builder().error("没有足够的权限").build();
        }


        SingleQueryResult<Student> saveStudentResult = studentService.saveByReqMod(student);
        ListQueryResult<Parent> saveParentResult = parentService.saveAllByReqMod(student.getParentsInfo());
        for (Parent parent : saveParentResult.getList()) {
            RefStuPar refStuPar = new RefStuPar();
            refStuPar.setStudent(saveStudentResult.getOne());
            refStuPar.setParent(parent);
            refStuParService.save(refStuPar);
        }

        return RespWrapper.builder()
                .success()
                .build();
    }

    /**
     * @param number    学号
     * @param phone     手机号
     * @param password  密码
     * */
    @ApiOperation(value = "学生登陆", notes = "学生登陆")
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public RespWrapper login(@Min(value = 0) @RequestParam(required = false) long   number,
                                             @RequestParam(required = false) String phone,
                             @NotEmpty       @RequestParam(required = true)  String password){

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
    public RespWrapper findById(@NotEmpty @PathVariable String id){

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
    public RespWrapper findByPageInNumber(@Min(value = 0) @RequestParam(required = true,  defaultValue = "0") long cursor,
                                          @Min(value = 0) @RequestParam(required = false, defaultValue = "0") int  count,
                                          @Min(value = 0) @RequestParam(required = false, defaultValue = "0") int  skip){
        if(count  == 0) count  = AppConfig.defaultDataQueryCount;

        ListQueryResult<Student> listQueryResult = studentService.findByPageInNumber(cursor, count, skip);

        return RespWrapper.builder()
                .success()
                .addCount(listQueryResult.getCount())
                .addTotalCount(listQueryResult.getTotalCount())
                .addIsEnd(listQueryResult.isEnd())
                .addCursor(cursor)
                .addCustomParam("students", listQueryResult.convertToResp(StudentResp.class))
                .build();
    }

    /**
     * 删除学生
     * @param id    学生 id
     * */
    @ApiOperation(value = "删除学生", notes = "根据 id 删除学生信息")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public RespWrapper deleteById(@NotEmpty @PathVariable String id){
        Student student = studentService.findOneById(id).getOne();
        if(student == null || StringUtils.isEmpty(student.getId())){
            return RespWrapper.builder().notFound("未查到该学生").build();
        }

        studentService.deleteById(id);

        return RespWrapper.builder()
                .success()
                .build();
    }
}
