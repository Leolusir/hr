package com.devils.hr.responses;

import java.util.Map;

/**
 * Created by AndyL on 2017/4/4.
 */
public abstract class AbstractRespFactory {

    public abstract RespWrapper createRespSuccess();

    public abstract RespWrapper createRespSuccess(Map<String, Object> result);

    public abstract RespWrapper createRespParamsIsNull(String... params);

    public abstract RespWrapper createRespNotFound();

    public abstract RespWrapper createRespErrorWithCustomMsg(String message);

    public abstract RespWrapper createRespError();

}
