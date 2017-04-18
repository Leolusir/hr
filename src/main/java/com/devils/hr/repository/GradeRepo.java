package com.devils.hr.repository;

import com.devils.hr.pojo.roles.Grade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndyL on 2017/4/18.
 */
@Repository
public interface GradeRepo extends MongoRepository<Grade, String> {

    Grade findBySortNum(int sort);

    Grade findByName(String name);

}
