package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.UserDeviceDao;
import io.renren.modules.sys.entity.UserDeviceEntity;
import io.renren.modules.sys.service.UserDeviceService;


@Service("userDeviceService")
public class UserDeviceServiceImpl extends ServiceImpl<UserDeviceDao, UserDeviceEntity> implements UserDeviceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserDeviceEntity> page = this.page(
                new Query<UserDeviceEntity>().getPage(params),
                new QueryWrapper<UserDeviceEntity>()
        );

        return new PageUtils(page);
    }

}
