package com.devils.hr.repository;

import com.devils.hr.pojo.roles.SClass;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndyL on 2017/4/4.
 */
@Repository
public interface SClassRepo extends MongoRepository<SClass, String> {



}
