package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.TabUserDao;
import io.renren.modules.sys.entity.TabUserEntity;
import io.renren.modules.sys.service.TabUserService;


@Service("tabUserService")
public class TabUserServiceImpl extends ServiceImpl<TabUserDao, TabUserEntity> implements TabUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TabUserEntity> page = this.page(
                new Query<TabUserEntity>().getPage(params),
                new QueryWrapper<TabUserEntity>()
        );

        return new PageUtils(page);
    }

}
