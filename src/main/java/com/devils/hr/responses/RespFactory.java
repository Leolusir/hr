package com.devils.hr.responses;

import com.devils.hr.constants.ResponseStatus;
import com.devils.hr.constants.ResponseMessage;

import java.util.Arrays;
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
        return new RespWrapper(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS, result);
    }

    @Override
    public RespWrapper createRespParamsIsNull(String... params) {
        return new RespWrapper(ResponseStatus.MISS_PARAMS, ResponseMessage.MISS_PARAMS + Arrays.toString(params));
    }

    @Override
    public RespWrapper createRespNotFound() {
        return new RespWrapper(ResponseStatus.NOT_FOUND, ResponseMessage.NOT_FOUND);
    }

    @Override
    public RespWrapper createRespErrorWithCustomMsg(String message) {
        return new RespWrapper(ResponseStatus.ERROR_WITH_CUSTOM_MESSAGE, message);
    }

    @Override
    public RespWrapper createRespError() {
        return new RespWrapper(ResponseStatus.ERROR, ResponseMessage.ERROR);
    }
}
