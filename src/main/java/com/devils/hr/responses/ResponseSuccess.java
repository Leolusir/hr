package com.devils.hr.responses;

/**
 * Created by AndyL on 2017/4/12.
 */
public class ResponseSuccess implements Response {

    private static final int    status  = 1001;

    private static final String message = "success";

    @Override
    public RespWrapper createRespWrapper() {
        return new RespWrapper(status, message);
    }

}
