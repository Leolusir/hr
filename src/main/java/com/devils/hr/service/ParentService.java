package com.devils.hr.service;

import com.devils.hr.pojo.roles.Parent;
import com.devils.hr.querys.ListQueryResult;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.request.ParentReqMod;
import com.devils.hr.request.StudentReqMod;
import com.devils.hr.service.base.BaseService;

import java.util.List;

/**
 * Created by AndyL on 2017/4/16.
 */
public interface ParentService extends BaseService<Parent>{

    /**
     * 根据手机号查找
     * */
    SingleQueryResult<Parent> findByPhone(String phone);

    /**
     * 批量保存
     * */
    ListQueryResult<Parent> saveAll(List<Parent> parents);

    /**
     * 批量创建
     * @see com.devils.hr.controllers.apis.StudentApiCtrl#createStudent(StudentReqMod)
     * @see com.devils.hr.request.ParentReqMod
     * */
    ListQueryResult<Parent> saveAllByReqMod(List<ParentReqMod> parentReqMods);

}
