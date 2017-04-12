package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.Manager;
import com.devils.hr.querys.ListQueryResult;
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
    public Manager save(Manager manager) {
        long currentTime = System.currentTimeMillis();
        manager.setUpdateTime(currentTime);
        manager.setCreateTime(currentTime);
        return managerRepo.save(manager);
    }

    @Override
    public void deleteById(String id) {
        managerRepo.delete(id);
    }

    @Override
    public Manager findOneById(String id) {
        return managerRepo.findOne(id);
    }

    @Override
    public long count() {
        return managerRepo.count();
    }

    @Override
    public Manager update(Manager manager) {
        manager.setUpdateTime(System.currentTimeMillis());
        return managerRepo.save(manager);
    }

    @Override
    public Manager findByUserName(String username) {
        return managerRepo.findByUsername(username);
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
        long          totalCount    = count();
        boolean       isEnd         = resultCount < count;

        return ListQueryResult.create(managers, resultCount, totalCount, isEnd);
    }
}
