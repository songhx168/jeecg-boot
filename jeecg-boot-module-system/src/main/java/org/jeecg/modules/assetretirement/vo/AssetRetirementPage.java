package org.jeecg.modules.assetretirement.vo;

import java.util.List;
import org.jeecg.modules.assetretirement.entity.AssetRetirement;
import org.jeecg.modules.assetretirement.entity.AssetRetirementDetail;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 资产清理表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Data
@ApiModel(value="asset_retirementPage对象", description="资产清理表")
public class AssetRetirementPage {
	
	/**主键*/
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
	/**清理处理人*/
	@Excel(name = "清理处理人", width = 15)
	@ApiModelProperty(value = "清理处理人")
	private java.lang.String assetRetirementUser;
	/**清理时间*/
	@Excel(name = "清理时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "清理时间")
	private java.util.Date assetRetirementDate;
	/**清理原因*/
	@Excel(name = "清理原因", width = 15)
	@ApiModelProperty(value = "清理原因")
	private java.lang.String assetRetirementReason;
	/**清理备注*/
	@Excel(name = "清理备注", width = 15)
	@ApiModelProperty(value = "清理备注")
	private java.lang.String assetRetirementRemark;
	
	@ExcelCollection(name="资产清理明细表")
	@ApiModelProperty(value = "资产清理明细表")
	private List<AssetRetirementDetail> assetRetirementDetailList;
	
}
