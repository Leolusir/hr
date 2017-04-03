package com.devils.hr.repository;

import com.devils.hr.pojo.roles.School;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndyL on 2017/4/2.
 */
@Repository
public interface SchoolRepo extends MongoRepository<School, String> {

}
