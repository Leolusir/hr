package com.devils.hr.service.impls;

import com.devils.hr.pojo.records.RefStuPar;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.repository.RefStuParRepo;
import com.devils.hr.service.RefStuParService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AndyL on 2017/4/18.
 */
@Service
public class RefStuParServiceImpl implements RefStuParService {

    @Autowired
    private RefStuParRepo refStuParRepo;

    @Override
    public SingleQueryResult<RefStuPar> save(RefStuPar refStuPar) {
        long currentTime = System.currentTimeMillis();
        refStuPar.setUpdateTime(currentTime);
        refStuPar.setCreateTime(currentTime);

        return SingleQueryResult.create(refStuParRepo.save(refStuPar));
    }

    @Override
    public void deleteById(String id) {
        refStuParRepo.delete(id);
    }

    @Override
    public SingleQueryResult<RefStuPar> findOneById(String id) {
        return SingleQueryResult.create(refStuParRepo.findOne(id));
    }

    @Override
    public long count() {
        return refStuParRepo.count();
    }

    @Override
    public SingleQueryResult<RefStuPar> update(RefStuPar refStuPar) {
        refStuPar.setUpdateTime(System.currentTimeMillis());

        return SingleQueryResult.create(refStuParRepo.save(refStuPar));
    }

}
