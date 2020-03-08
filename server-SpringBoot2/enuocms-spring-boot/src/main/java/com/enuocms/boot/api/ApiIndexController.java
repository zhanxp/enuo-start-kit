package com.enuocms.boot.api;

import com.enuocms.boot.vo.AccountVo;
import com.enuocms.core.model.ServiceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhanxiaoping on 2020/3/8.
 * zhanxp@me.com
 */

@RestController
@RequestMapping("/api")
@Api(value = "ApiIndexController", tags = "ApiIndexController")
public class ApiIndexController {

    @ApiOperation(value = "账户登录",notes = "")
    @PostMapping("/login")
    public ServiceResult login(@RequestBody AccountVo adminVo){
        return  new ServiceResult(false);
    }
}
