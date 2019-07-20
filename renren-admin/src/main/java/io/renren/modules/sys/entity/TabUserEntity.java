package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 普通用户表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-19 16:13:47
 */
@Data
@TableName("tab_user")
public class TabUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 微信用户唯一标识
	 */
	private String openId;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 盐
	 */
	private String salt;
	/**
	 * 用户状态:0:停用 1:正常
	 */
	private Integer status;
	/**
	 * 普通用户性别，1为男性，2为女性
	 */
	private Integer sex;
	/**
	 * 用户昵称
	 */
	private String nickname;
	/**
	 * 头像
	 */
	private String headimgurl;
	/**
	 * 部门ID
	 */
	private Long deptId;
	/**
	 * 上次登录时间
	 */
	private Date loginTimer;
	/**
	 * 创造时间
	 */
	private Date createTimer;

}
