package com.enuocms.boot.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhanxiaoping on 2020/3/8.
 * zhanxp@me.com
 */

@RestController
@RequestMapping("/api")
@Api(value = "UserApiController", tags = "UserApiController")
public class UserApiController {
}
