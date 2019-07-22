package io.renren.modules.sys.service.impl;

import io.renren.common.utils.R;
import io.renren.common.utils.UUIDUtil;
import io.renren.modules.sys.entity.request.LoginEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

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

    @Override
    public R login(LoginEntity params) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("open_id",params.getOpenid());
        TabUserEntity tabUserEntity = baseMapper.selectOne(wrapper);
        if (tabUserEntity!=null){
            if (tabUserEntity.getStatus()==0){
                return R.error("账户已停用,详细情况请咨询客服");
            }
            tabUserEntity.setLoginTimer(new Date());
            baseMapper.updateById(tabUserEntity);
        }else {
             tabUserEntity =new TabUserEntity();
            String uuid = UUIDUtil.getUUID();
            tabUserEntity.setUserId(uuid);
            tabUserEntity.setOpenId(params.getOpenid());
            tabUserEntity.setStatus(1);
            tabUserEntity.setNickname(params.getNickname());
            tabUserEntity.setHeadimgurl(params.getHeadimgurl());
            tabUserEntity.setCreateTimer(new Date());
            baseMapper.insert(tabUserEntity);
        }
        tabUserEntity.setPassword(null);

        return R.ok().put("data",tabUserEntity);
    }

}
