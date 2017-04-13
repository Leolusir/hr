package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.SClass;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.repository.SClassRepo;
import com.devils.hr.service.SClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AndyL on 2017/4/4.
 */
@Service
public class SClassServiceImpl implements SClassService {

    @Autowired
    private SClassRepo sClassRepo;

    @Override
    public SingleQueryResult<SClass> save(SClass sClass) {
        long currentTime = System.currentTimeMillis();
        sClass.setUpdateTime(currentTime);
        sClass.setCreateTime(currentTime);
        return SingleQueryResult.create(sClassRepo.save(sClass));
    }

    @Override
    public void deleteById(String id) {
        sClassRepo.delete(id);
    }

    @Override
    public SingleQueryResult<SClass> findOneById(String id) {
        return SingleQueryResult.create(sClassRepo.findOne(id));
    }

    @Override
    public long count() {
        return sClassRepo.count();
    }

    @Override
    public SingleQueryResult<SClass> update(SClass sClass) {
        sClass.setUpdateTime(System.currentTimeMillis());
        return SingleQueryResult.create(sClassRepo.save(sClass));
    }
}
