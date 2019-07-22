package io.renren.modules.sys.service.impl;

import io.renren.common.utils.Constant;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.DbUserDeviceEntity;
import io.renren.modules.sys.entity.TabUserEntity;
import io.renren.modules.sys.entity.UserDeviceEntity;
import io.renren.modules.sys.entity.request.BaseApiEntity;
import io.renren.modules.sys.entity.request.BindEntity;
import io.renren.modules.sys.service.TabUserService;
import io.renren.modules.sys.service.UserDeviceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.WarterDeviceDao;
import io.renren.modules.sys.entity.WarterDeviceEntity;
import io.renren.modules.sys.service.WarterDeviceService;
import org.springframework.transaction.annotation.Transactional;


@Service("warterDeviceService")
public class WarterDeviceServiceImpl extends ServiceImpl<WarterDeviceDao, WarterDeviceEntity> implements WarterDeviceService {

    @Autowired
    private TabUserService tabUserService;
    @Autowired
    private UserDeviceService userDeviceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String) params.get("no");
        String status = (String) params.get("status");
        IPage<WarterDeviceEntity> page = this.page(
                new Query<WarterDeviceEntity>().getPage(params),
                new QueryWrapper<WarterDeviceEntity>()
                        .like(StringUtils.isNotBlank(name), "no", name)
                        .eq(StringUtils.isNotBlank(status), "status", status)
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryUserDeviceList(Map<String, Object> params) {


        int curPage = 1;
        int limit = 10;

        if (params.get(Constant.PAGE) != null) {
            curPage = Integer.valueOf((String) params.get(Constant.PAGE));
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Integer.valueOf((String) params.get(Constant.LIMIT));
        }
        int oneV = (curPage - 1) * limit;
        int twoV = curPage * limit;
        params.put("oneV", oneV);
        params.put("twoV", twoV);
        List<DbUserDeviceEntity> datas = baseMapper.queryUserDeviceList(params);
        Integer totalCount = baseMapper.queryUserDeviceListCount(params);

        return new PageUtils(datas, totalCount, limit, curPage);
    }

    @Override
    public R saveorUpdate(WarterDeviceEntity params) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("no",params.getNo());
        int count = baseMapper.selectCount(queryWrapper);
        if (count>0){
            return R.error("设备编号已存在");
        }
        if (params.getId()!=-1){
            baseMapper.updateById(params);
        }else {
            baseMapper.insert(params);
        }
        return R.ok("操作完成");
    }

    @Override
    public List<WarterDeviceEntity> queryUserDv(BaseApiEntity params) {
        return baseMapper.queryByUserId(params.getUserId());
    }

    @Override
    @Transactional
    public R bind(BindEntity bindEntity) {
        if (StringUtils.isEmpty(bindEntity.getDeviceNO())) {
            return R.error("请输入设备编号");
        }
        QueryWrapper userWrapper = new QueryWrapper<>();
        userWrapper.eq("user_id", bindEntity.getUserId());
        TabUserEntity tabUserEntity = tabUserService.getBaseMapper().selectOne(userWrapper);
        if (tabUserEntity == null) {
            return R.error("未查询到该用户");
        }
        if (tabUserEntity.getStatus() != 1) {
            return R.error("用户状态异常");
        }
        QueryWrapper deviceWrapper = new QueryWrapper<>();
        deviceWrapper.eq("no", bindEntity.getDeviceNO());
        WarterDeviceEntity deviceEntity = baseMapper.selectOne(deviceWrapper);
        if (deviceEntity == null) {
            return R.error("未查询到该设备");
        }

        QueryWrapper bindWrapper = new QueryWrapper();
        bindWrapper.eq("no", bindEntity.getDeviceNO());
        int bindCount = userDeviceService.getBaseMapper().selectCount(bindWrapper);
        if (bindCount>0) {
            return R.error("设备已被他人绑定");
        }

        deviceEntity.setStatus(1);
        baseMapper.updateById(deviceEntity);

        UserDeviceEntity userDeviceEntity = new UserDeviceEntity();
        userDeviceEntity.setNo(deviceEntity.getNo());
        userDeviceEntity.setUserId(tabUserEntity.getUserId());
        userDeviceEntity.setStatus(1);
        int count = userDeviceService.getBaseMapper().insert(userDeviceEntity);
        if (count > 0) {
            return R.ok("绑定成功");
        }
        return R.error("绑定失败");
    }

    @Override
    public R clean(String no) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("no", no);
        int count = userDeviceService.getBaseMapper().delete(wrapper);
        if (count > 0) {
            return R.ok("解除成功");
        } else {
            return R.error("解除失败");
        }
    }

    @Override
    public PageUtils querynoBindDeviceList(Map<String, Object> params) {
        int curPage = 1;
        int limit = 10;

        if (params.get(Constant.PAGE) != null) {
            curPage = Integer.valueOf((String) params.get(Constant.PAGE));
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Integer.valueOf((String) params.get(Constant.LIMIT));
        }
        int oneV = (curPage - 1) * limit;
        int twoV = curPage * limit;
        params.put("oneV", oneV);
        params.put("twoV", twoV);
        List<WarterDeviceEntity> datas = baseMapper.querynoBindDeviceList(params);
        Integer totalCount = baseMapper.querynoBindDeviceListCount(params);

        return new PageUtils(datas, totalCount, limit, curPage);
    }


}
