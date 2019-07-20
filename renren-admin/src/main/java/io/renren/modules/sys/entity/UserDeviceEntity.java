package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户与水表绑定关系表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-19 16:13:47
 */
@Data
@TableName("tb_user_device")
public class UserDeviceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	private String userId;
	/**
	 * 水表编号
	 */
	private String no;
	/**
	 * 绑定状态 0:未绑定 1.正常 2.解除绑定 
	 */
	private Integer status;

}
