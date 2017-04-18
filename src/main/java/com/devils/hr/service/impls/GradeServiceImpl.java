package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.Grade;
import com.devils.hr.querys.ListQueryResult;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.repository.GradeRepo;
import com.devils.hr.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AndyL on 2017/4/18.
 */
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepo gradeRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public SingleQueryResult<Grade> save(Grade grade) {
        long currentTime = System.currentTimeMillis();
        grade.setUpdateTime(currentTime);
        grade.setCreateTime(currentTime);
        return SingleQueryResult.create(gradeRepo.save(grade));
    }

    @Override
    public void deleteById(String id) {
        gradeRepo.delete(id);
    }

    @Override
    public SingleQueryResult<Grade> findOneById(String id) {
        return SingleQueryResult.create(gradeRepo.findOne(id));
    }

    @Override
    public long count() {
        return gradeRepo.count();
    }

    @Override
    public SingleQueryResult<Grade> update(Grade grade) {
        grade.setUpdateTime(System.currentTimeMillis());
        return SingleQueryResult.create(gradeRepo.save(grade));
    }

    @Override
    public ListQueryResult<Grade> findAll() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "sortNum"));
        List<Grade> gradeList = mongoTemplate.find(query, Grade.class);
        int count = gradeList == null ? 0 : gradeList.size();

        return ListQueryResult.create(gradeList, count, count, true);
    }

    @Override
    public int generateNewSortNum() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "sortNum")).limit(1);
        Grade grade = mongoTemplate.findOne(query, Grade.class);

        return grade == null ? 1 : (grade.getSortNum() + 1);
    }
}
