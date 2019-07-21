package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.DbUserDeviceEntity;
import io.renren.modules.sys.entity.WarterDeviceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 设备表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-19 16:30:48
 */
@Mapper
public interface WarterDeviceDao extends BaseMapper<WarterDeviceEntity> {

    List<DbUserDeviceEntity> queryUserDeviceList(Map<String, Object> params);

    // TODO: 2019/7/21 0021 效率不高
    List<WarterDeviceEntity> querynoBindDeviceList(Map<String, Object> params);

   int  queryUserDeviceListCount(Map<String, Object> params);
   int querynoBindDeviceListCount(Map<String, Object> params);

}
