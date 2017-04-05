package com.devils.hr.repository;

import com.devils.hr.pojo.roles.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndyL on 2017/4/5.
 */
@Repository
public interface SubjectRepo extends MongoRepository<Subject, String> {

    Subject findByName(String name);

}
