package com.devils.hr.service.impls;

import com.devils.hr.configs.AppConfig;
import com.devils.hr.pojo.roles.Parent;
import com.devils.hr.querys.ListQueryResult;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.repository.ParentRepo;
import com.devils.hr.request.ParentReqMod;
import com.devils.hr.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndyL on 2017/4/16.
 */
@Service
public class ParentServiceImpl implements ParentService{

    @Autowired
    private ParentRepo parentRepo;

    @Override
    public SingleQueryResult<Parent> save(Parent parent) {
        if(StringUtils.isEmpty(parent.getPassword())){
            parent.setStatus(Parent.STATUS_INACTIVE);
            parent.setPassword(AppConfig.INIT_PASSWORD_MD5);
        }
        long currentTime = System.currentTimeMillis();
        parent.setUpdateTime(currentTime);
        parent.setCreateTime(currentTime);
        return SingleQueryResult.create(parentRepo.save(parent));
    }

    @Override
    public void deleteById(String id) {
        parentRepo.delete(id);
    }

    @Override
    public SingleQueryResult<Parent> findOneById(String id) {
        return SingleQueryResult.create(parentRepo.findOne(id));
    }

    @Override
    public long count() {
        return parentRepo.count();
    }

    @Override
    public SingleQueryResult<Parent> update(Parent parent) {
        parent.setUpdateTime(System.currentTimeMillis());
        return SingleQueryResult.create(parentRepo.save(parent));
    }

    @Override
    public SingleQueryResult<Parent> findByPhone(String phone) {
        return SingleQueryResult.create(parentRepo.findByPhone(phone));
    }

    @Override
    public ListQueryResult<Parent> saveAll(List<Parent> parents) {
        List<Parent> result = new ArrayList<>();
        for (Parent parent : parents) {
            if(StringUtils.isEmpty(parent.getPassword())){
                parent.setStatus(Parent.STATUS_INACTIVE);
                parent.setPassword(AppConfig.INIT_PASSWORD_MD5);
            }
            long currentTime = System.currentTimeMillis();
            parent.setUpdateTime(currentTime);
            parent.setCreateTime(currentTime);
            result.add(parentRepo.save(parent));
        }
        int count = result.size();

        return ListQueryResult.create(result, count, count);
    }

    @Override
    public ListQueryResult<Parent> saveAllByReqMod(List<ParentReqMod> parentReqMods) {
        List<Parent> parents = new ArrayList<>();
        for (ParentReqMod parentReqMod : parentReqMods) {
            Parent parent = new Parent();
            parent.setName(parentReqMod.getName());
            parent.setPhone(parentReqMod.getPhone());
            parents.add(parent);
        }

        return saveAll(parents);
    }

}
