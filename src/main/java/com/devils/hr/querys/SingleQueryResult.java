package com.devils.hr.querys;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by AndyL on 2017/4/13.
 */
public class SingleQueryResult<T> {

    private T one;

    private SingleQueryResult() {}

    public static <R> SingleQueryResult<R> create(R one){
        SingleQueryResult<R> singleQueryResult = new SingleQueryResult<R>();
        singleQueryResult.setOne(one);
        return singleQueryResult;
    }

    public <W> W convertToResp(Class<W> respModule){
        try {
            return respModule.getConstructor(one.getClass()).newInstance(one);
        } catch (InstantiationException | IllegalAccessException
                | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public T getOne() {
        return one;
    }

    public void setOne(T one) {
        this.one = one;
    }
}
