package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.Manager;
import com.devils.hr.querys.ListQueryResult;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.repository.ManagerRepo;
import com.devils.hr.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AndyL on 2017/4/4.
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepo managerRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public SingleQueryResult<Manager> save(Manager manager) {
        long currentTime = System.currentTimeMillis();
        manager.setUpdateTime(currentTime);
        manager.setCreateTime(currentTime);
        return SingleQueryResult.create(managerRepo.save(manager));

    }

    @Override
    public void deleteById(String id) {
        managerRepo.delete(id);
    }

    @Override
    public SingleQueryResult<Manager> findOneById(String id) {
        return SingleQueryResult.create(managerRepo.findOne(id));
    }

    @Override
    public long count() {
        return managerRepo.count();
    }

    @Override
    public SingleQueryResult<Manager> update(Manager manager) {
        manager.setUpdateTime(System.currentTimeMillis());
        return SingleQueryResult.create(managerRepo.save(manager));
    }

    @Override
    public SingleQueryResult<Manager> findByUserName(String username) {
        return SingleQueryResult.create(managerRepo.findByUsername(username));
    }

    @Override
    public ListQueryResult<Manager> findByPageInUpdateTime(long updateTimeCursor, int count, int skip) {
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("updateTime").lt(updateTimeCursor));

        Query query = new Query();
        query.addCriteria(criteria);
        query.with(new Sort(Sort.Direction.DESC, "updateTime"))
            .skip(skip)
            .limit(count);

        List<Manager> managers      = mongoTemplate.find(query, Manager.class);
        long          resultCount   = managers == null ? 0 : managers.size();
        long          totalCount    = mongoTemplate.count(query, Manager.class);
        boolean       isEnd         = resultCount < count;

        return ListQueryResult.create(managers, resultCount, totalCount, isEnd);
    }
}
