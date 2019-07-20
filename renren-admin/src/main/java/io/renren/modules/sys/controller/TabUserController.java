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

import io.renren.modules.sys.entity.TabUserEntity;
import io.renren.modules.sys.service.TabUserService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 普通用户表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-19 16:13:47
 */
@RestController
@RequestMapping("sys/tabuser")
public class TabUserController {
    @Autowired
    private TabUserService tabUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:tabuser:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tabUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:tabuser:info")
    public R info(@PathVariable("id") Integer id){
        TabUserEntity tabUser = tabUserService.getById(id);

        return R.ok().put("tabUser", tabUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:tabuser:save")
    public R save(@RequestBody TabUserEntity tabUser){
        tabUserService.save(tabUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:tabuser:update")
    public R update(@RequestBody TabUserEntity tabUser){
        ValidatorUtils.validateEntity(tabUser);
        tabUserService.updateById(tabUser);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:tabuser:delete")
    public R delete(@RequestBody Integer[] ids){
        tabUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
