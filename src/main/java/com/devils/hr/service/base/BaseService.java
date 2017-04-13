package com.devils.hr.service.base;

import com.devils.hr.querys.SingleQueryResult;

/**
 * Created by AndyL on 2017/4/2.
 */
public interface BaseService<T> {

    /**
     * 增
     * */
    SingleQueryResult<T> save(T t);

    /**
     * 删
     * */
    void deleteById(String id);

    /**
     * 查
     * */
    SingleQueryResult<T> findOneById(String id);

    long count();

    /**
     * 改
     * */
    SingleQueryResult<T> update(T t);

}
