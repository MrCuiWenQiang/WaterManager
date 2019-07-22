package io.renren.modules.sys.controller;

import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.request.LoginEntity;
import io.renren.modules.sys.service.TabUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private TabUserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public R login(@RequestBody LoginEntity params){
        ValidatorUtils.validateEntity(params);
        R r  = userService.login(params);
        return r;
    }
}
