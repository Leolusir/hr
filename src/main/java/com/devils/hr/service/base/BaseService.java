package com.devils.hr.service.base;

/**
 * Created by AndyL on 2017/4/2.
 */
public interface BaseService<T> {

    /**
     * 增
     * */
    T save(T t);

    /**
     * 删
     * */
    void deleteById(String id);

    /**
     * 查
     * */
    T findOneById(String id);

    long count();

    /**
     * 改
     * */
    T update(T t);

}
