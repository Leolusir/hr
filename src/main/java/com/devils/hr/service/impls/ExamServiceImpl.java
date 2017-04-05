package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.Exam;
import com.devils.hr.repository.ExamRepo;
import com.devils.hr.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;

/**
 * Created by AndyL on 2017/4/5.
 */
@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepo examRepo;

    @Override
    public Exam save(Exam exam) {
        long currentTime = Clock.systemDefaultZone().millis();
        exam.setUpdateTime(currentTime);
        exam.setCreateTime(currentTime);
        return examRepo.save(exam);
    }

    @Override
    public void deleteById(String id) {
        examRepo.delete(id);
    }

    @Override
    public Exam findOneById(String id) {
        return examRepo.findOne(id);
    }

    @Override
    public Exam update(Exam exam) {
        exam.setUpdateTime(Clock.systemDefaultZone().millis());
        return examRepo.save(exam);
    }
}
