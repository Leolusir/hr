package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.Manager;
import com.devils.hr.repository.ManagerRepo;
import com.devils.hr.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;

/**
 * Created by AndyL on 2017/4/4.
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepo managerRepo;

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
}
