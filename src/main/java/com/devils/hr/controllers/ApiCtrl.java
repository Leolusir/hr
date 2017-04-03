package com.devils.hr.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AndyL on 2017/4/2.
 */
@RestController
@RequestMapping("/api/v1")
public class ApiCtrl {

    @ApiOperation(value = "测试", notes = "测试api")
    @RequestMapping(method = RequestMethod.GET)
    public String hello(){
        return "Hello, here is the apis for HR EDU CLOUD SERVER";
    }

}
