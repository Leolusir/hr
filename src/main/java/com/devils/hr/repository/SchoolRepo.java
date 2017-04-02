package com.devils.hr.repository;

import com.devils.hr.pojo.roles.School;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by AndyL on 2017/4/2.
 */
public interface SchoolRepo extends MongoRepository<School, String> {

}
