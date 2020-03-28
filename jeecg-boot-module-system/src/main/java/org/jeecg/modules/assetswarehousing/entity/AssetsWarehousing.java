package org.jeecg.modules.assetswarehousing.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 资产入库表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Data
@TableName("assets_warehousing")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="assets_warehousing对象", description="资产入库表")
public class AssetsWarehousing implements Serializable {
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
	/**所属公司*/
	@Excel(name = "所属公司", width = 15)
    @ApiModelProperty(value = "所属公司")
    private java.lang.String company;
	/**资产编码*/
	@Excel(name = "资产编码", width = 15)
    @ApiModelProperty(value = "资产编码")
    private java.lang.String assetCode;
	/**资产名称*/
	@Excel(name = "资产名称", width = 15)
    @ApiModelProperty(value = "资产名称")
    private java.lang.String assetName;
	/**资产类别*/
	@Excel(name = "资产类别", width = 15)
    @ApiModelProperty(value = "资产类别")
    private java.lang.String assetTypeName;
	/**入库时间*/
	@Excel(name = "入库时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "入库时间")
    private java.util.Date inDate;
	/**资产型号*/
	@Excel(name = "资产型号", width = 15)
    @ApiModelProperty(value = "资产型号")
    private java.lang.String assetModel;
	/**资产来源*/
	@Excel(name = "资产来源", width = 15, dicCode = "asset_come")
	@Dict(dicCode = "asset_come")
    @ApiModelProperty(value = "资产来源")
    private java.lang.String assetCome;
	/**渠道*/
	@Excel(name = "渠道", width = 15)
    @ApiModelProperty(value = "渠道")
    private java.lang.String channel;
	/**金额*/
	@Excel(name = "金额", width = 15)
    @ApiModelProperty(value = "金额")
    private java.lang.Integer assetPrice;
	/**品牌*/
	@Excel(name = "品牌", width = 15)
    @ApiModelProperty(value = "品牌")
    private java.lang.String assetBrand;
	/**购入时间*/
	@Excel(name = "购入时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "购入时间")
    private java.util.Date buyDate;
	/**计量单位*/
	@Excel(name = "计量单位", width = 15)
    @ApiModelProperty(value = "计量单位")
    private java.lang.Integer assetMeasurement;
	/**使用期限*/
	@Excel(name = "使用期限", width = 15)
    @ApiModelProperty(value = "使用期限")
    private java.lang.String assetUsefulLife;
	/**保修起始*/
	@Excel(name = "保修起始", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "保修起始")
    private java.util.Date guaranteeStart;
	/**过保时间*/
	@Excel(name = "过保时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "过保时间")
    private java.util.Date guaranteeEnd;
	/**存放地*/
	@Excel(name = "存放地", width = 15)
    @ApiModelProperty(value = "存放地")
    private java.lang.String saveArea;
	/**资产状态*/
	@Excel(name = "资产状态", width = 15, dicCode = "asset_state")
	@Dict(dicCode = "asset_state")
    @ApiModelProperty(value = "资产状态")
    private java.lang.String assetState;
	/**使用部门*/
	@Excel(name = "使用部门", width = 15)
    @ApiModelProperty(value = "使用部门")
    private java.lang.String assetsCollarDepart;
	/**使用人*/
	@Excel(name = "使用人", width = 15)
    @ApiModelProperty(value = "使用人")
    private java.lang.String assetsCollarUser;
	/**领用时间*/
	@Excel(name = "领用时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "领用时间")
    private java.util.Date assetsCollarDate;
	/**清理处理人*/
	@Excel(name = "清理处理人", width = 15)
    @ApiModelProperty(value = "清理处理人")
    private java.lang.String assetRetirementUser;
	/**清理原因*/
	@Excel(name = "清理原因", width = 15)
    @ApiModelProperty(value = "清理原因")
    private java.lang.String assetRetirementReason;
	/**清理时间*/
	@Excel(name = "清理时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "清理时间")
    private java.util.Date assetRetirementDate;
}
