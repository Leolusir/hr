package com.devils.hr.repository;

import com.devils.hr.pojo.roles.Parent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndyL on 2017/4/16.
 */
@Repository
public interface ParentRepo extends MongoRepository<Parent, String> {

    Parent findByPhone(String phone);

}
