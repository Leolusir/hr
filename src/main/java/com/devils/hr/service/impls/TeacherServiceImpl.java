package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.Teacher;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.repository.TeacherRepo;
import com.devils.hr.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AndyL on 2017/4/2.
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    @Override
    public SingleQueryResult<Teacher> save(Teacher teacher) {
        long currentTime = System.currentTimeMillis();
        teacher.setUpdateTime(currentTime);
        teacher.setCreateTime(currentTime);
        return SingleQueryResult.create(teacherRepo.save(teacher));
    }

    @Override
    public void deleteById(String id) {
        teacherRepo.delete(id);
    }

    @Override
    public SingleQueryResult<Teacher> findOneById(String id) {
        return SingleQueryResult.create(teacherRepo.findOne(id));
    }

    @Override
    public long count() {
        return teacherRepo.count();
    }

    @Override
    public SingleQueryResult<Teacher> update(Teacher teacher) {
        teacher.setUpdateTime(System.currentTimeMillis());
        return SingleQueryResult.create(teacherRepo.save(teacher));
    }

}
