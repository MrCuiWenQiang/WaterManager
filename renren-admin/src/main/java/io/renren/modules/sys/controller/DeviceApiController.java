package io.renren.modules.sys.controller;

import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.WarterDeviceEntity;
import io.renren.modules.sys.entity.request.BaseApiEntity;
import io.renren.modules.sys.entity.request.BindEntity;
import io.renren.modules.sys.entity.request.LoginEntity;
import io.renren.modules.sys.service.TabUserService;
import io.renren.modules.sys.service.UserDeviceService;
import io.renren.modules.sys.service.WarterDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/device")
public class DeviceApiController {
    @Autowired
    private WarterDeviceService warterDeviceService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public R list(@RequestBody BaseApiEntity params){
        ValidatorUtils.validateEntity(params);
        List<WarterDeviceEntity> data = warterDeviceService.queryUserDv(params);
        R r = R.ok().put("data",data);
        return r;
    }

    @RequestMapping(value = "/bind",method = RequestMethod.POST)
    public R bind(@RequestBody BindEntity params){
        ValidatorUtils.validateEntity(params);
        return warterDeviceService.bind(params);
    }
}
