package com.devils.hr.controllers;

import com.devils.hr.responses.RespFactory;
import com.devils.hr.responses.RespWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AndyL on 2017/4/2.
 */
@RestController
@RequestMapping("/api/v1")
public class ApiCtrl {

    @ApiOperation(value = "测试", notes = "测试api")
    @RequestMapping(method = RequestMethod.GET)
    public RespWrapper hello(){
        Map<String, Object> result = new HashMap<>();
        result.put("key", "value");

        return RespFactory.getInstance().createRespSuccess(result);
    }

}
