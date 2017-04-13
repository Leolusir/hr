package com.devils.hr.responses;

import com.devils.hr.constants.ResponseMessage;
import com.devils.hr.constants.ResponseStatus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AndyL on 2017/4/3.
 */
public class RespWrapper {

    private int     status;

    private String  message = null;

    private Map<String,Object> result = null;

    public static RespWrapperBuilder builder(){
        return new RespWrapperBuilder();
    }

    public RespWrapper(RespWrapperBuilder builder) {
        this.status  = builder._status;
        this.message = builder._message;
        this.result  = builder._result;
    }

    public RespWrapper(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public RespWrapper(int status, String message, Map<String, Object> result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RespWrapper{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    public static class RespWrapperBuilder {
        private int     _status;
        private String  _message;
        private Map<String,Object> _result;


        RespWrapperBuilder() {
            _result = new HashMap<>();
        }

        public RespWrapperBuilder setStatus(int status) {
            _status = status;
            return this;
        }

        public RespWrapperBuilder setMessage(String message) {
            _message = message;
            return this;
        }

        public RespWrapperBuilder addIsEnd(boolean isEnd){
            _result.put("isEnd", isEnd);
            return this;
        }

        public RespWrapperBuilder addCount(long count){
            _result.put("count", count);
            return this;
        }

        public RespWrapperBuilder addTotalCount(long totalCount){
            _result.put("totalCount", totalCount);
            return this;
        }

        public RespWrapperBuilder addCursor(long cursor){
            _result.put("cursor", cursor);
            return this;
        }

        public RespWrapperBuilder addCustomParam(String name, Object value){
            _result.put(name, value);
            return this;
        }

        //templates
        public RespWrapperBuilder success(){
            _status = ResponseStatus.SUCCESS;
            _message = ResponseMessage.SUCCESS;
            return this;
        }

        public RespWrapperBuilder missParams(String... paramNames){
            _status = ResponseStatus.MISS_PARAMS;
            _message = ResponseMessage.MISS_PARAMS + Arrays.toString(paramNames);
            return this;
        }

        public RespWrapperBuilder notFound(String desc){
            _status = ResponseStatus.NOT_FOUND;
            _message = ResponseMessage.NOT_FOUND + desc;
            return this;
        }

        public RespWrapperBuilder error(String message){
            _status = ResponseStatus.ERROR_WITH_CUSTOM_MESSAGE;
            _message = message;
            return this;
        }

        public RespWrapperBuilder error(){
            _status = ResponseStatus.ERROR;
            _message = ResponseMessage.ERROR;
            return this;
        }

        public RespWrapper build(){
            return new RespWrapper(this);
        }
    }

}
