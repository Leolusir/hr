package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.Subject;
import com.devils.hr.repository.SubjectRepo;
import com.devils.hr.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;

/**
 * Created by AndyL on 2017/4/5.
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepo subjectRepo;

    @Override
    public Subject save(Subject subject) {
        long currentTime = Clock.systemDefaultZone().millis();
        subject.setUpdateTime(currentTime);
        subject.setCreateTime(currentTime);
        return subjectRepo.save(subject);
    }

    @Override
    public void deleteById(String id) {
        subjectRepo.delete(id);
    }

    @Override
    public Subject findOneById(String id) {
        return subjectRepo.findOne(id);
    }

    @Override
    public Subject update(Subject subject) {
        subject.setUpdateTime(Clock.systemDefaultZone().millis());
        return subjectRepo.save(subject);
    }

    @Override
    public Subject findByName(String name) {
        return subjectRepo.findByName(name);
    }
}
