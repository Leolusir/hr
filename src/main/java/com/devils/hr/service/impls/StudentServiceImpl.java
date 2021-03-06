package com.devils.hr.service.impls;

import com.devils.hr.configs.AppConfig;
import com.devils.hr.pojo.roles.Student;
import com.devils.hr.querys.ListQueryResult;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.repository.StudentRepo;
import com.devils.hr.request.StudentReqMod;
import com.devils.hr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
    public SingleQueryResult<Student> save(Student student) {
        student.setNumber(generateNumber());//生成学号
        if(StringUtils.isEmpty(student.getPassword())){
            //初始密码
            student.setStatus(Student.STATUS_INACTIVE);
            student.setPassword(AppConfig.INIT_PASSWORD_MD5);
        }
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
        String admissionTime = format.format(new Date(currentTime));
        student.setAdmissionTime(admissionTime);
        student.setUpdateTime(currentTime);
        student.setCreateTime(currentTime);

        return SingleQueryResult.create(studentRepo.save(student));
    }

    @Override
    public void deleteById(String id) {
        studentRepo.delete(id);
    }

    @Override
    public SingleQueryResult<Student> findOneById(String id) {
        return SingleQueryResult.create(studentRepo.findOne(id));
    }

    @Override
    public long count() {
        return studentRepo.count();
    }

    @Override
    public SingleQueryResult<Student> update(Student student) {
        student.setUpdateTime(System.currentTimeMillis());
        return SingleQueryResult.create(studentRepo.save(student));
    }

    @Override
    public SingleQueryResult<Student> findByNumberOrPhone(long number, String phone) {
        return SingleQueryResult.create(studentRepo.findByNumberOrPhone(number, phone));
    }

    @Override
    public SingleQueryResult<Student> findByIDNumber(String IDNumber) {
        return SingleQueryResult.create(studentRepo.findByIDNumber(IDNumber));
    }

    @Override
    public ListQueryResult<Student> findByPageInNumber(long startNumber, int count, int skip) {
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("number").gt(startNumber));

        Query query = new Query();
        query.addCriteria(criteria);
        query.with(new Sort(Sort.Direction.ASC, "number"))
                .limit(count)
                .skip(skip);

        List<Student> students = mongoTemplate.find(query, Student.class);
        long resultCount = students == null ? 0 : students.size();
        long totalCount  = mongoTemplate.count(query, Student.class);
        boolean isEnd    = resultCount < count;

        return ListQueryResult.create(students, resultCount, totalCount, isEnd);
    }

    @Override
    public long generateNumber() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        long count = count();

        return year * 1000000 + count + 1;
    }

    @Override
    public SingleQueryResult<Student> saveByReqMod(StudentReqMod studentReqMod) {
        Student student = new Student();
        student.setName(studentReqMod.getName());
        student.setDesc(studentReqMod.getDesc());

        return save(student);
    }
}
