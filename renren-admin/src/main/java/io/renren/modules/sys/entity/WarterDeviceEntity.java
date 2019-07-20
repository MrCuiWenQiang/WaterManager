package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-07-19 16:30:48
 */
@Data
@TableName("tb_warter_device")
public class WarterDeviceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 水表编号
	 */
	private String no;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 水表状态 0:欠费停用 1:正常 2:未安装 3:故障 4:电量不足
	 */
	private Integer status;
	/**
	 * 型号
	 */
	private String model;
	/**
	 * 安装日期
	 */
	private Date installDate;
	/**
	 * 安装人员用户id
	 */
	private String installUserWorlId;
	/**
	 * 电池更换期限
	 */
	private Date replaceDate;

}
