package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.TabUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 普通用户表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-19 16:13:47
 */
@Mapper
public interface TabUserDao extends BaseMapper<TabUserEntity> {
	
}
