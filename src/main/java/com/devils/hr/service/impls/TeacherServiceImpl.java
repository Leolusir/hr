package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.Teacher;
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
    public Teacher save(Teacher teacher) {
        long currentTime = System.currentTimeMillis();
        teacher.setUpdateTime(currentTime);
        teacher.setCreateTime(currentTime);
        return teacherRepo.save(teacher);
    }

    @Override
    public void deleteById(String id) {
        teacherRepo.delete(id);
    }

    @Override
    public Teacher findOneById(String id) {
        return teacherRepo.findOne(id);
    }

    @Override
    public long count() {
        return teacherRepo.count();
    }

    @Override
    public Teacher update(Teacher teacher) {
        teacher.setUpdateTime(System.currentTimeMillis());
        return teacherRepo.save(teacher);
    }

}
