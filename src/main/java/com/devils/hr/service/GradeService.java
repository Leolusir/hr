package com.devils.hr.service;

import com.devils.hr.pojo.roles.Grade;
import com.devils.hr.querys.ListQueryResult;
import com.devils.hr.service.base.BaseService;

/**
 * Created by AndyL on 2017/4/18.
 */
public interface GradeService extends BaseService<Grade>{

    ListQueryResult<Grade> findAll();

    int generateNewSortNum();

}
