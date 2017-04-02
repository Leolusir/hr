package com.devils.hr.repository;

import com.devils.hr.pojo.roles.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by AndyL on 2017/4/2.
 */
public interface StudentRepo extends MongoRepository<Student, String>{

}
