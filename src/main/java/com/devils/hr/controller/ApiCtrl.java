package com.devils.hr.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AndyL on 2017/4/2.
 */
@RestController
@RequestMapping("/api")
public class ApiCtrl {

    @RequestMapping(method = RequestMethod.GET)
    public String hello(){
        return "Hello, here is the apis for HR EDU CLOUD SERVER";
    }

}
