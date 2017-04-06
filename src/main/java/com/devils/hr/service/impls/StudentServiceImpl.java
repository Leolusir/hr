package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.Student;
import com.devils.hr.repository.StudentRepo;
import com.devils.hr.responses.modules.Page;
import com.devils.hr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by AndyL on 2017/4/2.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

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
    public List<Student> findByPageInNumber(Page page) {
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("number").gt(page.getCursor()));

        Query query = new Query();
        query.addCriteria(criteria);
        query.with(new Sort(Sort.Direction.ASC, "number"))
                .limit(page.getCount())
                .skip(page.getSkip());

        List<Student> students = mongoTemplate.find(query, Student.class);

        return students;
    }

    @Override
    public long generateNumber() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        long count = studentRepo.count();

        return year * 1000000 + count + 1;
    }
}
