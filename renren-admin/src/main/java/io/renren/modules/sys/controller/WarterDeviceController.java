package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.service.SysDictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.WarterDeviceEntity;
import io.renren.modules.sys.service.WarterDeviceService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 设备表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-19 16:30:48
 */
@RestController
@RequestMapping("sys/warterdevice")
public class WarterDeviceController {
    @Autowired
    private WarterDeviceService warterDeviceService;
    @Autowired
    private SysDictService sysDictService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:warterdevice:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = warterDeviceService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/status")
    public R status(){
        List<SysDictEntity> status = sysDictService.queryByType("devstatus");
        return R.ok().put("status", status);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:warterdevice:info")
    public R info(@PathVariable("id") Integer id){
        WarterDeviceEntity warterDevice = warterDeviceService.getById(id);

        return R.ok().put("warterDevice", warterDevice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:warterdevice:save")
    public R save(@RequestBody WarterDeviceEntity warterDevice){
        warterDeviceService.save(warterDevice);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:warterdevice:update")
    public R update(@RequestBody WarterDeviceEntity warterDevice){
        ValidatorUtils.validateEntity(warterDevice);
        warterDeviceService.updateById(warterDevice);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:warterdevice:delete")
    public R delete(@RequestBody Integer[] ids){
        warterDeviceService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
