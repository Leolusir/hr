package com.devils.hr.controllers.apis;

import com.devils.hr.pojo.roles.Grade;
import com.devils.hr.querys.ListQueryResult;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.responses.RespWrapper;
import com.devils.hr.responses.modules.GradeResp;
import com.devils.hr.service.GradeService;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AndyL on 2017/4/18.
 */
@RestController
@Validated
@RequestMapping("/api/v1/grade")
public class GradeCtrl {

    @Autowired
    private GradeService gradeService;

    /**
     * 添加年级
     * */
    @ApiOperation(value = "添加年级", notes = "添加年级")
    @RequestMapping(method = RequestMethod.POST)
    public RespWrapper addGrade(@NotEmpty @RequestParam(required = true)  String name,
                                          @RequestParam(required = false) String desc){
        Grade grade = new Grade();
        grade.setName(name);
        grade.setDesc(desc);
        grade.setSortNum(gradeService.generateNewSortNum());

        SingleQueryResult<Grade> result = gradeService.save(grade);

        return RespWrapper.builder()
                .success()
                .addCustomParam("grade", result.convertToResp(GradeResp.class))
                .build();
    }

    @ApiOperation(value = "获取所有年级", notes = "获取所有年级")
    @RequestMapping(method = RequestMethod.GET)
    public RespWrapper getAllGrade(){
        ListQueryResult<Grade> result = gradeService.findAll();

        return RespWrapper.builder()
                .success()
                .addCustomParam("grades", result.convertToResp(GradeResp.class))
                .build();
    }

}
