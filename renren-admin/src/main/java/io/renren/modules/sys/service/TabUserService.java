package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.TabUserEntity;
import io.renren.modules.sys.entity.request.LoginEntity;

import java.util.Map;

/**
 * 普通用户表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-19 16:13:47
 */
public interface TabUserService extends IService<TabUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R login(LoginEntity params);
}

