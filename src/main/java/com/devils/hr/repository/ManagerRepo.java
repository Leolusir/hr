package com.devils.hr.repository;

import com.devils.hr.pojo.roles.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndyL on 2017/4/4.
 */
@Repository
public interface ManagerRepo extends MongoRepository<Manager, String> {

    Manager findByUsername(String username);

}
