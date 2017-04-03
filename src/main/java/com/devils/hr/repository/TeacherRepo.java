package com.devils.hr.repository;

import com.devils.hr.pojo.roles.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndyL on 2017/4/2.
 */
@Repository
public interface TeacherRepo extends MongoRepository<Teacher, String> {

}
