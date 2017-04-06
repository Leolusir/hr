package com.devils.hr.controllers.apis;

import com.devils.hr.configs.AppConfig;
import com.devils.hr.constants.Constants;
import com.devils.hr.constants.Status;
import com.devils.hr.pojo.roles.Manager;
import com.devils.hr.responses.RespFactory;
import com.devils.hr.responses.RespWrapper;
import com.devils.hr.responses.modules.ManagerResp;
import com.devils.hr.responses.modules.Page;
import com.devils.hr.service.ManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public RespWrapper createManager(@RequestParam(required = true)  String username,
                                     @RequestParam(required = true)  String password,
                                     @RequestParam(required = true)  String name,
                                     @RequestParam(required = false) String desc){
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
        manager.setDesc(desc);
        manager.setRole(Constants.MANAGER_ROLE_READ_WRITE);
        manager.setStatus(Status.MANAGER_ACTIVATED);

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
        result.put("desc", existManager.getDesc());
        result.put("lastLoginTime", existManager.getLastLoginTime());

        existManager.setLastLoginTime(Clock.systemDefaultZone().millis());
        managerService.update(existManager);

        return RespFactory.getInstance().createRespSuccess(result);
    }

    /**
     * 获取某个管理员信息
     * @param id    管理员 id
     * */
    @ApiOperation(value = "获取单个管理员信息", notes = "根据 id 获取单个管理员信息")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public RespWrapper findById(@PathVariable String id){
        Manager manager = managerService.findOneById(id);
        if(manager == null || StringUtils.isEmpty(manager.getId())){
            return RespFactory.getInstance().createRespErrorWithCustomeMsg("未找到该管理员");
        }
        if(manager.getRole().equals(Constants.MANAGER_ROLE_ROOT)){
            return RespFactory.getInstance().createRespErrorWithCustomeMsg("没有权限查看该管理员信息");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", manager.getId());
        result.put("name", manager.getName());
        result.put("desc", manager.getDesc());
        result.put("role", manager.getRole());
        result.put("lastLoginTime", manager.getLastLoginTime());

        return RespFactory.getInstance().createRespSuccess(result);
    }

    /**
     * 按 updateTime 字段分页查询
     * @param cursor
     * @param skip
     * @param count
     * */
    @ApiOperation(value = "分页查询所有管理员", notes = "cursor第一次请求传 0, 每次会返回新的 cursor 供前端请求下一页数据")
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public RespWrapper findByPageInUpdateTime(@RequestParam(required = true,  defaultValue = "0") long cursor,
                                              @RequestParam(required = false, defaultValue = "0") int  skip,
                                              @RequestParam(required = false, defaultValue = "0") int  count){
        if(cursor == 0) cursor = Clock.systemDefaultZone().millis();
        if(count == 0) count = AppConfig.dataQueryCount;

        Page page = new Page(cursor, skip, count);
        List<Manager> managers = managerService.findByPageInUpdateTime(page);
        Boolean isEnd = managers == null || managers.size() < count;

        Map<String, Object> result = new HashMap<>();
        if(managers == null || managers.size() < 1){
            result.put("isEnd", true);
            result.put("cursor", 0);
            result.put("count", 0);
            return RespFactory.getInstance().createRespSuccess(result);
        }

        List<ManagerResp> managerRespList = new ArrayList<>();
        managers.forEach(manager -> managerRespList.add(new ManagerResp(manager)));

        result.put("isEnd", isEnd);
        result.put("cursor", isEnd ? 0 : managers.get(managers.size() - 1).getUpdateTime());
        result.put("count", managerRespList.size());
        result.put("managers", managerRespList);

        return RespFactory.getInstance().createRespSuccess(result);
    }

    /**
     * 删除管理员
     * @param id    管理员 id
     * */
    @ApiOperation(value = "删除某个管理员", notes = "根据 id 删除管理员")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public RespWrapper deleteById(@PathVariable String id){
        Manager manager = managerService.findOneById(id);
        if(manager == null || StringUtils.isEmpty(manager.getId())){
            return RespFactory.getInstance().createRespErrorWithCustomeMsg("未找到该管理员");
        }
        if(manager.getRole().equals(Constants.MANAGER_ROLE_ROOT)
                || manager.getRole().equals(Constants.MANAGER_ROLE_AMIN)){
            return RespFactory.getInstance().createRespErrorWithCustomeMsg("无法删除系统管理员");
        }

        managerService.deleteById(id);

        return RespFactory.getInstance().createRespSuccess();
    }

}
