package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.UserDeviceEntity;
import io.renren.modules.sys.service.UserDeviceService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户与水表绑定关系表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-19 16:13:47
 */
@RestController
@RequestMapping("sys/userdevice")
public class UserDeviceController {
    @Autowired
    private UserDeviceService userDeviceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:userdevice:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userDeviceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:userdevice:info")
    public R info(@PathVariable("userId") String userId){
        UserDeviceEntity userDevice = userDeviceService.getById(userId);

        return R.ok().put("userDevice", userDevice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:userdevice:save")
    public R save(@RequestBody UserDeviceEntity userDevice){
        userDeviceService.save(userDevice);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:userdevice:update")
    public R update(@RequestBody UserDeviceEntity userDevice){
        ValidatorUtils.validateEntity(userDevice);
        userDeviceService.updateById(userDevice);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:userdevice:delete")
    public R delete(@RequestBody String[] userIds){
        userDeviceService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
