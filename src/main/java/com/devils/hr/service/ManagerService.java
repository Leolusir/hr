package com.devils.hr.service;

import com.devils.hr.pojo.roles.Manager;
import com.devils.hr.service.base.BaseService;

/**
 * Created by AndyL on 2017/4/4.
 */
public interface ManagerService extends BaseService<Manager> {

    /**
     * 根据 Username 查找 manager
     * */
    Manager findByUserName(String username);

}
