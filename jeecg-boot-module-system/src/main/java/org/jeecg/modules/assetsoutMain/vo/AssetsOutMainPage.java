package org.jeecg.modules.assetsoutMain.vo;

import java.util.List;
import org.jeecg.modules.assetsoutMain.entity.AssetsOutMain;
import org.jeecg.modules.assetsoutMain.entity.AssetsInfoDetail;
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
 * @Description: 资产出库主表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Data
@ApiModel(value="assets_out_mainPage对象", description="资产出库主表")
public class AssetsOutMainPage {
	
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
	/**领用后使用公司*/
	@Excel(name = "领用后使用公司", width = 15)
	@ApiModelProperty(value = "领用后使用公司")
	private java.lang.String assetsCollarCompany;
	/**领用后使用部门*/
	@Excel(name = "领用后使用部门", width = 15)
	@ApiModelProperty(value = "领用后使用部门")
	private java.lang.String assetsCollarDepart;
	/**领用时间*/
	@Excel(name = "领用时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "领用时间")
	private java.util.Date assetsCollarDate;
	/**领用后使用人*/
	@Excel(name = "领用后使用人", width = 15)
	@ApiModelProperty(value = "领用后使用人")
	private java.lang.String assetsCollarUser;
	/**领用备注*/
	@Excel(name = "领用备注", width = 15)
	@ApiModelProperty(value = "领用备注")
	private java.lang.String assetsCollarRemark;
	
	@ExcelCollection(name="资产信息附表")
	@ApiModelProperty(value = "资产信息附表")
	private List<AssetsInfoDetail> assetsInfoDetailList;
	
}
