package com.devils.hr.repository;

import com.devils.hr.pojo.records.RefStuPar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndyL on 2017/4/18.
 */
@Repository
public interface RefStuParRepo extends MongoRepository<RefStuPar, String> {

}
