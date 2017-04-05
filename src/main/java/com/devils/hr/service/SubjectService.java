package com.devils.hr.service;

import com.devils.hr.pojo.roles.Subject;
import com.devils.hr.service.base.BaseService;

/**
 * Created by AndyL on 2017/4/5.
 */
public interface SubjectService extends BaseService<Subject>{

    /**
     *  根据科目名称查找
     *  @param name 科目名称
     */
    Subject findByName(String name);

}
