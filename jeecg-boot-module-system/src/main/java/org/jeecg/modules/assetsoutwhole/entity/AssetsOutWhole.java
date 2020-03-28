package org.jeecg.modules.assetsoutwhole.entity;

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
 * @Description: 资产出库功能表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Data
@TableName("assets_out_whole")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="assets_out_whole对象", description="资产出库功能表")
public class AssetsOutWhole implements Serializable {
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
	/**资产编码*/
	@Excel(name = "资产编码", width = 15)
    @ApiModelProperty(value = "资产编码")
    private java.lang.String assetcode;
	/**资产名称*/
	@Excel(name = "资产名称", width = 15)
    @ApiModelProperty(value = "资产名称")
    private java.lang.String assetname;
	/**资产所属公司*/
	@Excel(name = "资产所属公司", width = 15)
    @ApiModelProperty(value = "资产所属公司")
    private java.lang.String company;
	/**保存的资产类编编码*/
	@Excel(name = "保存的资产类编编码", width = 15)
    @ApiModelProperty(value = "保存的资产类编编码")
    private java.lang.String saveid;
	/**资产类别*/
	@Excel(name = "资产类别", width = 15)
    @ApiModelProperty(value = "资产类别")
    private java.lang.String assettypename;
	/**入库时间*/
	@Excel(name = "入库时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "入库时间")
    private java.util.Date indate;
}
