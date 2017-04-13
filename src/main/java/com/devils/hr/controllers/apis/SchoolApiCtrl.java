package com.devils.hr.controllers.apis;

import com.devils.hr.responses.RespWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by AndyL on 2017/4/2.
 */
@RestController
@RequestMapping("/api/v1/school")
@ApiIgnore
public class SchoolApiCtrl {

    @ApiOperation(value="获取学校信息", notes="根据 ID 获取学校信息")
    @RequestMapping(method = RequestMethod.GET)
    public RespWrapper getById(@RequestParam(required = true) String id){
        if(StringUtils.isEmpty(id)){
            return RespWrapper.builder().missParams("id").build();
        }

        return RespWrapper.builder()
                .success()
                .build();
    }

}
