package com.devils.hr.service.impls;

import com.devils.hr.pojo.roles.SClass;
import com.devils.hr.repository.SClassRepo;
import com.devils.hr.service.SClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;

/**
 * Created by AndyL on 2017/4/4.
 */
@Service
public class SClassServiceImpl implements SClassService {

    @Autowired
    private SClassRepo sClassRepo;

    @Override
    public SClass save(SClass sClass) {
        long currentTime = Clock.systemDefaultZone().millis();
        sClass.setUpdateTime(currentTime);
        sClass.setCreateTime(currentTime);
        return sClassRepo.save(sClass);
    }

    @Override
    public void deleteById(String id) {
        sClassRepo.delete(id);
    }

    @Override
    public SClass findOneById(String id) {
        return sClassRepo.findOne(id);
    }

    @Override
    public SClass update(SClass sClass) {
        sClass.setUpdateTime(Clock.systemDefaultZone().millis());
        return sClassRepo.save(sClass);
    }
}
