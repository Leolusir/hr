package com.devils.hr.controllers.apis;

import com.devils.hr.pojo.records.Exam;
import com.devils.hr.pojo.roles.Subject;
import com.devils.hr.responses.RespFactory;
import com.devils.hr.responses.RespWrapper;
import com.devils.hr.service.ExamService;
import com.devils.hr.service.SubjectService;
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
 * Created by AndyL on 2017/4/5.
 */
@RestController
@RequestMapping("/api/v1/exam")
public class ExamApiCtrl {

    @Autowired
    private ExamService examService;

    @Autowired
    private SubjectService subjectService;

    /**
     * 添加考试
     * @param subjectId     科目 id
     * @param name
     * @param desc
     * @param year
     * @param month
     * @param day
     * */
    @ApiOperation(value = "添加考试", notes = "添加考试 选择科目 填写考试名称 描述(可选) 以及考试时间")
    @RequestMapping(method = RequestMethod.POST)
    public RespWrapper addExam(@RequestParam(required = true)  String subjectId,
                               @RequestParam(required = true)  String name,
                               @RequestParam(required = false) String desc,
                               @RequestParam(required = true)  int    year,
                               @RequestParam(required = true)  int    month,
                               @RequestParam(required = true)  int    day){
        Subject subject = subjectService.findOneById(subjectId);
        if(subject == null || StringUtils.isEmpty(subject.getId())){
            return RespFactory.getInstance().createRespErrorWithCustomMsg("科目不存在");
        }

        Exam exam = new Exam();
        exam.setName(name);
        exam.setDesc(desc);
        exam.setYear(year);
        exam.setMonth(month);
        exam.setDay(day);
        exam.setSubject(subject);

        Exam newExam = examService.save(exam);

        Map<String, Object> result = new HashMap<>();
        result.put("id", newExam.getId());

        return RespFactory.getInstance().createRespSuccess(result);
    }

}
