package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.DbUserDeviceEntity;
import io.renren.modules.sys.entity.WarterDeviceEntity;
import io.renren.modules.sys.entity.request.BindEntity;

import java.util.List;
import java.util.Map;

/**
 * 设备表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-19 16:30:48
 */
public interface WarterDeviceService extends IService<WarterDeviceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R bind(BindEntity bindEntity);
    R clean(String id);
    PageUtils querynoBindDeviceList(Map<String, Object> params);

    PageUtils queryUserDeviceList(Map<String, Object> params);
}

