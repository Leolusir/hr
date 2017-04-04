package com.devils.hr.controllers.apis;

import com.devils.hr.constants.Constants;
import com.devils.hr.constants.Status;
import com.devils.hr.pojo.roles.Manager;
import com.devils.hr.responses.RespFactory;
import com.devils.hr.responses.RespWrapper;
import com.devils.hr.service.ManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AndyL on 2017/4/4.
 */
@RestController
@RequestMapping("/api/v1/manager")
public class ManagerApiCtrl {

    @Autowired
    private ManagerService managerService;

    /**
     * 创建管理员
     * @param username  用户名
     * @param password  密码
     * @param name      昵称
     * */
    @ApiOperation(value = "创建管理员", notes="创建管理员")
    @RequestMapping(method = RequestMethod.POST)
    public RespWrapper createManager(@RequestParam(required = true) String username,
                                     @RequestParam(required = true) String password,
                                     @RequestParam(required = true) String name){
        if(StringUtils.isEmpty(username)){
            return RespFactory.getInstance().createRespParamsIsNull("username");
        }else if (StringUtils.isEmpty(password)){
            return RespFactory.getInstance().createRespParamsIsNull("password");
        }else if (StringUtils.isEmpty(name)){
            return RespFactory.getInstance().createRespParamsIsNull("name");
        }

        Manager existManager = managerService.findByUserName(username);
        if(existManager != null && !StringUtils.isEmpty(existManager.getId())){
            return RespFactory.getInstance().createRespErrorWithCustomeMsg("用户名已被注册");
        }

        Manager manager = new Manager();
        manager.setUsername(username);
        manager.setPassword(password);
        manager.setName(name);
        manager.setRole(Constants.MANAGER_ROLE_READ_WRITE);
        manager.setStatus(Status.MANAGER_ACTIVATED);

        long currentTime = Clock.systemDefaultZone().millis();
        manager.setUpdateTime(currentTime);
        manager.setCreateTime(currentTime);

        Manager newManager = managerService.save(manager);

        Map<String, Object> result = new HashMap<>();
        result.put("id", newManager.getId());
        result.put("name", newManager.getName());

        return RespFactory.getInstance().createRespSuccess(result);
    }

    /**
     * 管理员登陆
     * @param username  用户名
     * @param password  密码
     * */
    @ApiOperation(value = "管理员登陆", notes = "管理员登陆")
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public RespWrapper login(@RequestParam(required = true) String username,
                             @RequestParam(required = true) String password){
        if(StringUtils.isEmpty(username)){
            return RespFactory.getInstance().createRespParamsIsNull("username");
        }else if (StringUtils.isEmpty(password)){
            return RespFactory.getInstance().createRespParamsIsNull("password");
        }

        Manager existManager = managerService.findByUserName(username);

        if(existManager == null || StringUtils.isEmpty(existManager.getId())){
            return RespFactory.getInstance().createRespErrorWithCustomeMsg("用户名或密码错误");
        }

        if(!password.equals(existManager.getPassword())){
            return RespFactory.getInstance().createRespErrorWithCustomeMsg("用户名或密码错误");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", existManager.getId());
        result.put("name", existManager.getName());
        result.put("role", existManager.getRole());
        result.put("lastLoginTime", String.valueOf(existManager.getLastLoginTime()));

        return RespFactory.getInstance().createRespSuccess(result);
    }

}
