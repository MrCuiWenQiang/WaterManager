package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.WarterDeviceDao;
import io.renren.modules.sys.entity.WarterDeviceEntity;
import io.renren.modules.sys.service.WarterDeviceService;


@Service("warterDeviceService")
public class WarterDeviceServiceImpl extends ServiceImpl<WarterDeviceDao, WarterDeviceEntity> implements WarterDeviceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WarterDeviceEntity> page = this.page(
                new Query<WarterDeviceEntity>().getPage(params),
                new QueryWrapper<WarterDeviceEntity>()
        );

        return new PageUtils(page);
    }

}
