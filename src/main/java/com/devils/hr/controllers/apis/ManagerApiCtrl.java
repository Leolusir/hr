package com.devils.hr.controllers.apis;

import com.devils.hr.authorization.annotations.Authorization;
import com.devils.hr.configs.AppConfig;
import com.devils.hr.pojo.roles.Manager;
import com.devils.hr.querys.ListQueryResult;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.responses.RespWrapper;
import com.devils.hr.responses.modules.ManagerResp;
import com.devils.hr.service.ManagerService;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * Created by AndyL on 2017/4/4.
 */
@RestController
@Validated
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
    public RespWrapper createManager(@NotEmpty @RequestParam(required = true)  String username,
                                     @NotEmpty @RequestParam(required = true)  String password,
                                     @NotEmpty @RequestParam(required = true)  String name,
                                     @RequestParam(required = false) String desc){

        Manager existManager = managerService.findByUserName(username).getOne();
        if(existManager != null && !StringUtils.isEmpty(existManager.getId())){
            return RespWrapper.builder().error("用户名已被注册").build();
        }

        Manager manager = new Manager();
        manager.setUsername(username);
        manager.setPassword(password);
        manager.setName(name);
        manager.setDesc(desc);
        manager.setRole(Manager.ROLE_READ_WRITE);
        manager.setStatus(Manager.STATUS_ACTIVATED);

        SingleQueryResult<Manager> singleQueryResult = managerService.save(manager);

        return RespWrapper.builder()
                .success()
                .addCustomParam("manager", singleQueryResult.convertToResp(ManagerResp.class))
                .build();
    }

    /**
     * 管理员登陆
     * @param username  用户名
     * @param password  密码
     * */
    @ApiOperation(value = "管理员登陆", notes = "管理员登陆")
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public RespWrapper login(@NotEmpty @RequestParam(required = true) String username,
                             @NotEmpty @RequestParam(required = true) String password){

        SingleQueryResult<Manager> singleQueryResult = managerService.findByUserName(username);
        Manager existManager = singleQueryResult.getOne();

        if(existManager == null || StringUtils.isEmpty(existManager.getId())){
            return RespWrapper.builder().error("用户名或密码错误").build();
        }

        if(!password.equals(existManager.getPassword())){
            return RespWrapper.builder().error("用户名或密码错误").build();
        }

        existManager.setLastLoginTime(System.currentTimeMillis());
        managerService.update(existManager);

        return RespWrapper.builder()
                .success()
                .addCustomParam("manager", singleQueryResult.convertToResp(ManagerResp.class))
                .build();
    }

    /**
     * 获取某个管理员信息
     * @param id    管理员 id
     * */
    @ApiOperation(value = "获取单个管理员信息", notes = "根据 id 获取单个管理员信息")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public RespWrapper findById(@NotEmpty @PathVariable String id){
        SingleQueryResult<Manager> singleQueryResult = managerService.findOneById(id);
        if(singleQueryResult.getOne() == null ||
                StringUtils.isEmpty(singleQueryResult.getOne().getId())){
            return RespWrapper.builder().notFound("未找到该管理员").build();
        }
        if(singleQueryResult.getOne().getRole().equals(Manager.ROLE_ROOT)){
            return RespWrapper.builder().error("没有权限查看该管理员信息").build();
        }

        return RespWrapper.builder()
                .success()
                .addCustomParam("manager", singleQueryResult.convertToResp(ManagerResp.class))
                .build();
    }

    /**
     * 按 updateTime 字段分页查询
     * @param cursor
     * @param skip
     * @param count
     * */
    @Authorization
    @ApiOperation(value = "分页查询所有管理员", notes = "cursor第一次请求传 0, 每次会返回新的 cursor 供前端请求下一页数据")
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public RespWrapper findByPageInUpdateTime(@Min(value = 0) @RequestParam(required = false, defaultValue = "0") long cursor,
                                              @Min(value = 0) @RequestParam(required = false, defaultValue = "0") int  skip,
                                              @Min(value = 0) @RequestParam(required = false, defaultValue = "0") int  count){
        if(cursor == 0) cursor = System.currentTimeMillis();
        if(count == 0) count = AppConfig.defaultDataQueryCount;

        ListQueryResult<Manager> managers = managerService.findByPageInUpdateTime(cursor, count, skip);

        return RespWrapper.builder()
                .success()
                .addIsEnd(managers.isEnd())
                .addCount(managers.getCount())
                .addTotalCount(managers.getTotalCount())
                .addCursor(cursor)
                .addCustomParam("managers", managers.convertToResp(ManagerResp.class))
                .build();
    }

    /**
     * 删除管理员
     * @param id    管理员 id
     * */
    @ApiOperation(value = "删除某个管理员", notes = "根据 id 删除管理员")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public RespWrapper deleteById(@NotEmpty @PathVariable String id){
        SingleQueryResult<Manager> singleQueryResult = managerService.findOneById(id);
        Manager manager = singleQueryResult.getOne();

        if(manager == null || StringUtils.isEmpty(manager.getId())){
            return RespWrapper.builder().notFound("未找到该管理员").build();
        }
        if(manager.getRole().equals(Manager.ROLE_ROOT)
                || manager.getRole().equals(Manager.ROLE_ADMIN)){
            return RespWrapper.builder().error("无法删除系统管理员").build();
        }

        managerService.deleteById(id);

        return RespWrapper.builder()
                .success()
                .build();
    }

}
