package org.jeecg.modules.assetmanagement.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 资产类别表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Data
@TableName("asset_classes")
public class AssetClasses implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
	private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
	private java.lang.String sysOrgCode;
	/**父级节点*/
	@Excel(name = "父级节点", width = 15)
	private java.lang.String pid;
	/**是否有子节点*/
	@Excel(name = "是否有子节点", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
	private java.lang.String hasChild;
	/** 分类编码*/
	@Excel(name = " 分类编码", width = 15)
	private java.lang.String assetTypeCode;
	/**分类名称*/
	@Excel(name = "分类名称", width = 15)
	private java.lang.String assetTypeName;
	/**使用年限*/
	@Excel(name = "使用年限", width = 15)
	private java.lang.String assetUsefulLife;
	/**计量单位*/
	@Excel(name = "计量单位", width = 15)
	private java.lang.Integer assetMeasurement;
	/**所属类型*/
	@Excel(name = "所属类型", width = 15, dicCode = "asset_type")
	@Dict(dicCode = "asset_type")
	private java.lang.String typeOfOwnership;
}
