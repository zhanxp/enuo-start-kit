package com.enuocms.boot.api;

import com.alibaba.fastjson.JSONObject;
import com.enuocms.account.model.Admin;
import com.enuocms.account.service.AdminService;
import com.enuocms.boot.vo.AccountVo;
import com.enuocms.boot.vo.ApiUserVo;
import com.enuocms.core.model.ServiceResult;
import com.enuocms.core.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by zhanxiaoping on 2020/3/8.
 * zhanxp@me.com
 */

@RestController
@RequestMapping("/api")
@Api(value = "ApiIndexController", tags = "ApiIndexController")
public class ApiIndexController {

    @Autowired
    AdminService adminService;

    @Autowired
    RedisService redisService;

    @ApiOperation(value = "账户登录",notes = "")
    @PostMapping("/login")
    public ServiceResult login(@RequestBody AccountVo adminVo){
        Admin user = adminService.findLoginUser(adminVo.getPassport(),adminVo.getPassword());
        if(user==null){
            return new ServiceResult(500,"用户名密码错误！",null);
        }

        String ticket = UUID.randomUUID().toString();
        ApiUserVo apiUser = new ApiUserVo();
        apiUser.setId(user.getId());
        apiUser.setName(user.getName());
        apiUser.setTicket(ticket);
        String userStr = JSONObject.toJSONString(apiUser);
        redisService.set(ticket,userStr);
        return new ServiceResult(apiUser);
    }
}
