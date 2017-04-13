package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.Subject;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.repository.SubjectRepo;
import com.devils.hr.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AndyL on 2017/4/5.
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepo subjectRepo;

    @Override
    public SingleQueryResult<Subject> save(Subject subject) {
        long currentTime = System.currentTimeMillis();
        subject.setUpdateTime(currentTime);
        subject.setCreateTime(currentTime);
        return SingleQueryResult.create(subjectRepo.save(subject));
    }

    @Override
    public void deleteById(String id) {
        subjectRepo.delete(id);
    }

    @Override
    public SingleQueryResult<Subject> findOneById(String id) {
        return SingleQueryResult.create(subjectRepo.findOne(id));
    }

    @Override
    public long count() {
        return subjectRepo.count();
    }

    @Override
    public SingleQueryResult<Subject> update(Subject subject) {
        subject.setUpdateTime(System.currentTimeMillis());
        return SingleQueryResult.create(subjectRepo.save(subject));
    }

    @Override
    public SingleQueryResult<Subject> findByName(String name) {
        return SingleQueryResult.create(subjectRepo.findByName(name));
    }
}
