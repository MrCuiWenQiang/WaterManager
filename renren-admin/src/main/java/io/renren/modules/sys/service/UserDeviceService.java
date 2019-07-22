package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.UserDeviceEntity;

import java.util.Map;

/**
 * 用户与水表绑定关系表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-19 16:13:47
 */
public interface UserDeviceService extends IService<UserDeviceEntity> {

    PageUtils queryPage(Map<String, Object> params);


}

