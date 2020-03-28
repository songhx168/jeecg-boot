package org.jeecg.modules.assetsoutMain.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 资产信息附表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@ApiModel(value="assets_out_main对象", description="资产出库主表")
@Data
@TableName("assets_info_detail")
public class AssetsInfoDetail implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "主键")
	private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private java.util.Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
	@ApiModelProperty(value = "所属部门")
	private java.lang.String sysOrgCode;
	/**资产编码*/
	@Excel(name = "资产编码", width = 15)
	@ApiModelProperty(value = "资产编码")
	private java.lang.String assetCode;
	/**资产名称*/
	@Excel(name = "资产名称", width = 15)
	@ApiModelProperty(value = "资产名称")
	private java.lang.String assetName;
	/**所属公司*/
	@Excel(name = "所属公司", width = 15)
	@ApiModelProperty(value = "所属公司")
	private java.lang.String company;
	/**保存的资产类编编码*/
	@Excel(name = "保存的资产类编编码", width = 15)
	@ApiModelProperty(value = "保存的资产类编编码")
	private java.lang.String saveId;
	/**资产类别*/
	@Excel(name = "资产类别", width = 15)
	@ApiModelProperty(value = "资产类别")
	private java.lang.String assetTypeName;
	/**入库时间*/
	@Excel(name = "入库时间", width = 15)
	@ApiModelProperty(value = "入库时间")
	private java.lang.String inDate;
	/**资产出库主键ID*/
	@ApiModelProperty(value = "资产出库主键ID")
	private java.lang.String assetOutId;
}
