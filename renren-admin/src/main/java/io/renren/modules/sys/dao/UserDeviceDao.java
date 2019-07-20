package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.UserDeviceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与水表绑定关系表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-19 16:13:47
 */
@Mapper
public interface UserDeviceDao extends BaseMapper<UserDeviceEntity> {
	
}
