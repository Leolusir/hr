package com.devils.hr.repository;

import com.devils.hr.pojo.roles.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by AndyL on 2017/4/2.
 */
public interface TeacherRepo extends MongoRepository<Teacher, String> {

}
