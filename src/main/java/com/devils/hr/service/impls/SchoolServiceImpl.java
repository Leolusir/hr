package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.School;
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
    public School save(School school) {
        long currentTime = System.currentTimeMillis();
        school.setUpdateTime(currentTime);
        school.setCreateTime(currentTime);
        return schoolRepo.save(school);
    }

    @Override
    public void deleteById(String id) {
        schoolRepo.delete(id);
    }

    @Override
    public School findOneById(String id) {
        return schoolRepo.findOne(id);
    }

    @Override
    public long count() {
        return schoolRepo.count();
    }

    @Override
    public School update(School school) {
        school.setUpdateTime(System.currentTimeMillis());
        return schoolRepo.save(school);
    }

}
