package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.School;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.repository.SchoolRepo;
import com.devils.hr.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AndyL on 2017/4/2.
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepo schoolRepo;

    @Override
    public SingleQueryResult<School> save(School school) {
        long currentTime = System.currentTimeMillis();
        school.setUpdateTime(currentTime);
        school.setCreateTime(currentTime);
        return SingleQueryResult.create(schoolRepo.save(school));
    }

    @Override
    public void deleteById(String id) {
        schoolRepo.delete(id);
    }

    @Override
    public SingleQueryResult<School> findOneById(String id) {
        return SingleQueryResult.create(schoolRepo.findOne(id));
    }

    @Override
    public long count() {
        return schoolRepo.count();
    }

    @Override
    public SingleQueryResult<School> update(School school) {
        school.setUpdateTime(System.currentTimeMillis());
        return SingleQueryResult.create(schoolRepo.save(school));
    }

}
