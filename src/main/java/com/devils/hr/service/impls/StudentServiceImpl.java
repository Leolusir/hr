package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.Student;
import com.devils.hr.repository.StudentRepo;
import com.devils.hr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Created by AndyL on 2017/4/2.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public Student save(Student student) {
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
        return studentRepo.save(student);
    }

    @Override
    public Student findByIDNumber(String IDNumber) {
        return studentRepo.findByIDNumber(IDNumber);
    }

    @Override
    public long generateNumber() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();

        Query query = new Query();
        long count = mongoTemplate.count(query, Student.class);

        return year * 1000000 + count + 1;
    }
}
