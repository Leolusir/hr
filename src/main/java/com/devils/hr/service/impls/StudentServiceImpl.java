package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.Student;
import com.devils.hr.repository.StudentRepo;
import com.devils.hr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;

/**
 * Created by AndyL on 2017/4/2.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public Student save(Student student) {
        long currentTime = Clock.systemDefaultZone().millis();
        student.setUpdateTime(currentTime);
        student.setCreateTime(currentTime);
        return studentRepo.save(student);
    }

    @Override
    public void deleteById(String id) {
        studentRepo.delete(id);
    }

    @Override
    public Student findOneById(String id) {
        return studentRepo.findOne(id);
    }

    @Override
    public Student update(Student student) {
        student.setUpdateTime(Clock.systemDefaultZone().millis());
        return studentRepo.save(student);
    }

    @Override
    public Student findByNumberOrPhone(long number, String phone) {
        return studentRepo.findByNumberOrPhone(number, phone);
    }

    @Override
    public Student findByIDNumber(String IDNumber) {
        return studentRepo.findByIDNumber(IDNumber);
    }

    @Override
    public long generateNumber() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        long count = studentRepo.count();

        return year * 1000000 + count + 1;
    }
}
