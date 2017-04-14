package com.devils.hr.controllers.apis;

import com.devils.hr.pojo.roles.Subject;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.responses.RespWrapper;
import com.devils.hr.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AndyL on 2017/4/5.
 */
@RestController
@Validated
@RequestMapping("/api/v1/subject")
public class SubjectApiCtrl {

    @Autowired
    private SubjectService subjectService;

    /**
     * 添加科目
     * @param name  名称
     * @param desc  描述
     * */
    @ApiOperation(value = "添加科目", notes = "添加科目")
    @RequestMapping(method = RequestMethod.POST)
    public RespWrapper addSubject(@NotEmpty @RequestParam(required = true) String name,
                                  @RequestParam(required = false) String desc){
        Subject subject = new Subject();
        subject.setName(name);
        subject.setDesc(desc);

        SingleQueryResult<Subject> singleQueryResult = subjectService.save(subject);

        return RespWrapper.builder()
                .success()
                .addCustomParam("subject", singleQueryResult.getOne())
                .build();
    }

}
