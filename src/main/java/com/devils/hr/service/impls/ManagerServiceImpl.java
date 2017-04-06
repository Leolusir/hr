package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.Manager;
import com.devils.hr.repository.ManagerRepo;
import com.devils.hr.responses.modules.Page;
import com.devils.hr.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Clock;
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
        long currentTime = Clock.systemDefaultZone().millis();
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
    public Manager update(Manager manager) {
        manager.setUpdateTime(Clock.systemDefaultZone().millis());
        return managerRepo.save(manager);
    }

    @Override
    public Manager findByUserName(String username) {
        return managerRepo.findByUsername(username);
    }

    @Override
    public List<Manager> findByPageInUpdateTime(Page page) {
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("updateTime").lt(page.getCursor()));

        Query query = new Query();
        query.addCriteria(criteria);
        query.with(new Sort(Sort.Direction.DESC, "updateTime"))
            .skip(page.getSkip())
            .limit(page.getCount());

        List<Manager> managers = mongoTemplate.find(query, Manager.class);

        return managers;
    }
}
