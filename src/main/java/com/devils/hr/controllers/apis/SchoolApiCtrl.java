package com.devils.hr.controllers.apis;

import com.devils.hr.responses.RespWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AndyL on 2017/4/2.
 */
@RestController
@RequestMapping("/api/school/v1")
public class SchoolApiCtrl {

    @ApiOperation(value="学校信息", notes="获取学校信息")
    @RequestMapping(method = RequestMethod.GET)
    public RespWrapper getById(@RequestParam(required = true) String id){
        return new RespWrapper(1001, "success");
    }

}
