package com.devils.hr.responses;

import com.devils.hr.constants.ResponseCode;
import com.devils.hr.constants.ResponseStr;

import java.util.Map;

/**
 * Created by AndyL on 2017/4/4.
 */
public class RespFactory extends AbstractRespFactory{

    private static RespFactory ourInstance = new RespFactory();

    public static RespFactory getInstance() {
        return ourInstance;
    }

    private RespFactory() {}

    @Override
    public RespWrapper createRespSuccess() {
        return createRespSuccess(null);
    }

    @Override
    public RespWrapper createRespSuccess(Map<String, Object> result) {
        return new RespWrapper(ResponseCode.SUCCESS, ResponseStr.SUCCESS, result);
    }

    @Override
    public RespWrapper createRespParamsIsNull(String... params) {
        return new RespWrapper(ResponseCode.PARAMS_IS_NULL, ResponseStr.PARAMS_IS_NULL + params.toString());
    }

    @Override
    public RespWrapper createRespNotFound() {
        return new RespWrapper(ResponseCode.NOT_FOUND, ResponseStr.NOT_FOUND);
    }

    @Override
    public RespWrapper createRespErrorWithCustomeMsg(String message) {
        return new RespWrapper(ResponseCode.ERROR_WITH_CUNTOME_MESSAGE, message);
    }

    @Override
    public RespWrapper createRespError() {
        return new RespWrapper(ResponseCode.ERROR, ResponseStr.ERROR);
    }
}
