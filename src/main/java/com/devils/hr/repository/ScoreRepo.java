package com.devils.hr.repository;

import com.devils.hr.pojo.roles.Score;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndyL on 2017/4/5.
 */
@Repository
public interface ScoreRepo extends MongoRepository<Score, String>{

}
