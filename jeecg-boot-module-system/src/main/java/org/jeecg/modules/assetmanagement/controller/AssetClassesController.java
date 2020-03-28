package org.jeecg.modules.assetmanagement.controller;

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
import org.jeecg.modules.assetmanagement.entity.AssetClasses;
import org.jeecg.modules.assetmanagement.service.IAssetClassesService;

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

 /**
 * @Description: 资产类别表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@RestController
@RequestMapping("/assetmanagement/assetClasses")
@Slf4j
public class AssetClassesController extends JeecgController<AssetClasses, IAssetClassesService>{
	@Autowired
	private IAssetClassesService assetClassesService;
	
	/**
	 * 分页列表查询
	 *
	 * @param assetClasses
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/rootList")
	public Result<?> queryPageList(AssetClasses assetClasses,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String parentId = assetClasses.getPid();
		if (oConvertUtils.isEmpty(parentId)) {
			parentId = "0";
		}
		assetClasses.setPid(null);
		QueryWrapper<AssetClasses> queryWrapper = QueryGenerator.initQueryWrapper(assetClasses, req.getParameterMap());
		// 使用 eq 防止模糊查询
		queryWrapper.eq("pid", parentId);
		Page<AssetClasses> page = new Page<AssetClasses>(pageNo, pageSize);
		IPage<AssetClasses> pageList = assetClassesService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
      * 获取子数据
      * @param testTree
      * @param req
      * @return
      */
	@GetMapping(value = "/childList")
	public Result<?> queryPageList(AssetClasses assetClasses,HttpServletRequest req) {
		QueryWrapper<AssetClasses> queryWrapper = QueryGenerator.initQueryWrapper(assetClasses, req.getParameterMap());
		List<AssetClasses> list = assetClassesService.list(queryWrapper);
		return Result.ok(list);
	}
	
	
	/**
	 *   添加
	 *
	 * @param assetClasses
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AssetClasses assetClasses) {
		assetClassesService.addAssetClasses(assetClasses);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param assetClasses
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AssetClasses assetClasses) {
		assetClassesService.updateAssetClasses(assetClasses);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		assetClassesService.deleteAssetClasses(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.assetClassesService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AssetClasses assetClasses = assetClassesService.getById(id);
		if(assetClasses==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(assetClasses);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param assetClasses
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AssetClasses assetClasses) {
		return super.exportXls(request, assetClasses, AssetClasses.class, "资产类别表");
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
		return super.importExcel(request, response, AssetClasses.class);
    }

}
