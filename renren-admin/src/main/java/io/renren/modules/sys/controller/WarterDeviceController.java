package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.entity.request.BindEntity;
import io.renren.modules.sys.entity.request.CleanEntity;
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

    //查询未绑定的数据
    @RequestMapping("/noBindlist")
    public R noBindlist(@RequestParam Map<String, Object> params){
        PageUtils page = warterDeviceService.querynoBindDeviceList(params);
        return R.ok().put("page", page);
    }

    /**
     * 查询某用户下的设备
     */
    @RequestMapping("/userList")
    public R userList(@RequestParam Map<String, Object> params){
        PageUtils page = warterDeviceService.queryUserDeviceList(params);
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
        ValidatorUtils.validateEntity(warterDevice);
        R r = warterDeviceService.saveorUpdate(warterDevice);

        return r;
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:warterdevice:update")
    public R update(@RequestBody WarterDeviceEntity warterDevice){
        ValidatorUtils.validateEntity(warterDevice);
        R r = warterDeviceService.saveorUpdate(warterDevice);
        
        return r;
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

    /**
     * 用户和设备绑定
     * @return
     */
    @RequestMapping("/bind")
    public R bind(@RequestBody BindEntity bindEntity){
       R r =  warterDeviceService.bind(bindEntity);
       return r;
    }

    @RequestMapping("/clean")
    public R clean(@RequestBody CleanEntity entity){
        R r =  warterDeviceService.clean(entity.getNo());
        return r;
    }
}
