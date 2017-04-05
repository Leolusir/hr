package com.devils.hr.controllers.apis;

import com.devils.hr.pojo.roles.Subject;
import com.devils.hr.responses.RespFactory;
import com.devils.hr.responses.RespWrapper;
import com.devils.hr.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AndyL on 2017/4/5.
 */
@RestController
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
    public RespWrapper addSubject(@RequestParam(required = true) String name,
                                  @RequestParam(required = false) String desc){
        Subject subject = new Subject();
        subject.setName(name);
        subject.setDesc(desc);

        Subject newSub = subjectService.save(subject);

        Map<String, Object> result = new HashMap<>();
        result.put("id", newSub.getId());
        result.put("name", newSub.getName());
        result.put("desc", newSub.getDesc());

        return RespFactory.getInstance().createRespSuccess(result);
    }

}
