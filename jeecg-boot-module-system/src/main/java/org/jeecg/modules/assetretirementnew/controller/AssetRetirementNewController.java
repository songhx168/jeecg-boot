package org.jeecg.modules.assetretirementnew.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.assetretirementnew.entity.AssetRetirementNew;
import org.jeecg.modules.assetretirementnew.service.IAssetRetirementNewService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 资产清理表单
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Api(tags="资产清理表单")
@RestController
@RequestMapping("/assetretirementnew/assetRetirementNew")
@Slf4j
public class AssetRetirementNewController extends JeecgController<AssetRetirementNew, IAssetRetirementNewService> {
	@Autowired
	private IAssetRetirementNewService assetRetirementNewService;
	
	/**
	 * 分页列表查询
	 *
	 * @param assetRetirementNew
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "资产清理表单-分页列表查询")
	@ApiOperation(value="资产清理表单-分页列表查询", notes="资产清理表单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AssetRetirementNew assetRetirementNew,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AssetRetirementNew> queryWrapper = QueryGenerator.initQueryWrapper(assetRetirementNew, req.getParameterMap());
		Page<AssetRetirementNew> page = new Page<AssetRetirementNew>(pageNo, pageSize);
		IPage<AssetRetirementNew> pageList = assetRetirementNewService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param assetRetirementNew
	 * @return
	 */
	@AutoLog(value = "资产清理表单-添加")
	@ApiOperation(value="资产清理表单-添加", notes="资产清理表单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AssetRetirementNew assetRetirementNew) {
		assetRetirementNewService.save(assetRetirementNew);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param assetRetirementNew
	 * @return
	 */
	@AutoLog(value = "资产清理表单-编辑")
	@ApiOperation(value="资产清理表单-编辑", notes="资产清理表单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AssetRetirementNew assetRetirementNew) {
		assetRetirementNewService.updateById(assetRetirementNew);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "资产清理表单-通过id删除")
	@ApiOperation(value="资产清理表单-通过id删除", notes="资产清理表单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		assetRetirementNewService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "资产清理表单-批量删除")
	@ApiOperation(value="资产清理表单-批量删除", notes="资产清理表单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.assetRetirementNewService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "资产清理表单-通过id查询")
	@ApiOperation(value="资产清理表单-通过id查询", notes="资产清理表单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AssetRetirementNew assetRetirementNew = assetRetirementNewService.getById(id);
		if(assetRetirementNew==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(assetRetirementNew);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param assetRetirementNew
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AssetRetirementNew assetRetirementNew) {
        return super.exportXls(request, assetRetirementNew, AssetRetirementNew.class, "资产清理表单");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AssetRetirementNew.class);
    }

}
