package org.jeecg.modules.assetretirement.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.assetretirement.entity.AssetRetirementDetail;
import org.jeecg.modules.assetretirement.entity.AssetRetirement;
import org.jeecg.modules.assetretirement.vo.AssetRetirementPage;
import org.jeecg.modules.assetretirement.service.IAssetRetirementService;
import org.jeecg.modules.assetretirement.service.IAssetRetirementDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 资产清理表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Api(tags="资产清理表")
@RestController
@RequestMapping("/assetretirement/assetRetirement")
@Slf4j
public class AssetRetirementController {
	@Autowired
	private IAssetRetirementService assetRetirementService;
	@Autowired
	private IAssetRetirementDetailService assetRetirementDetailService;
	
	/**
	 * 分页列表查询
	 *
	 * @param assetRetirement
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "资产清理表-分页列表查询")
	@ApiOperation(value="资产清理表-分页列表查询", notes="资产清理表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AssetRetirement assetRetirement,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AssetRetirement> queryWrapper = QueryGenerator.initQueryWrapper(assetRetirement, req.getParameterMap());
		Page<AssetRetirement> page = new Page<AssetRetirement>(pageNo, pageSize);
		IPage<AssetRetirement> pageList = assetRetirementService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param assetRetirementPage
	 * @return
	 */
	@AutoLog(value = "资产清理表-添加")
	@ApiOperation(value="资产清理表-添加", notes="资产清理表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AssetRetirementPage assetRetirementPage) {
		AssetRetirement assetRetirement = new AssetRetirement();
		BeanUtils.copyProperties(assetRetirementPage, assetRetirement);
		assetRetirementService.saveMain(assetRetirement, assetRetirementPage.getAssetRetirementDetailList());
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param assetRetirementPage
	 * @return
	 */
	@AutoLog(value = "资产清理表-编辑")
	@ApiOperation(value="资产清理表-编辑", notes="资产清理表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AssetRetirementPage assetRetirementPage) {
		AssetRetirement assetRetirement = new AssetRetirement();
		BeanUtils.copyProperties(assetRetirementPage, assetRetirement);
		AssetRetirement assetRetirementEntity = assetRetirementService.getById(assetRetirement.getId());
		if(assetRetirementEntity==null) {
			return Result.error("未找到对应数据");
		}
		assetRetirementService.updateMain(assetRetirement, assetRetirementPage.getAssetRetirementDetailList());
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "资产清理表-通过id删除")
	@ApiOperation(value="资产清理表-通过id删除", notes="资产清理表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		assetRetirementService.delMain(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "资产清理表-批量删除")
	@ApiOperation(value="资产清理表-批量删除", notes="资产清理表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.assetRetirementService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "资产清理表-通过id查询")
	@ApiOperation(value="资产清理表-通过id查询", notes="资产清理表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AssetRetirement assetRetirement = assetRetirementService.getById(id);
		if(assetRetirement==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(assetRetirement);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "资产清理明细表集合-通过id查询")
	@ApiOperation(value="资产清理明细表集合-通过id查询", notes="资产清理明细表-通过id查询")
	@GetMapping(value = "/queryAssetRetirementDetailByMainId")
	public Result<?> queryAssetRetirementDetailListByMainId(@RequestParam(name="id",required=true) String id) {
		List<AssetRetirementDetail> assetRetirementDetailList = assetRetirementDetailService.selectByMainId(id);
		return Result.ok(assetRetirementDetailList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param assetRetirement
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AssetRetirement assetRetirement) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<AssetRetirement> queryWrapper = QueryGenerator.initQueryWrapper(assetRetirement, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<AssetRetirement> queryList = assetRetirementService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<AssetRetirement> assetRetirementList = new ArrayList<AssetRetirement>();
      if(oConvertUtils.isEmpty(selections)) {
          assetRetirementList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          assetRetirementList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<AssetRetirementPage> pageList = new ArrayList<AssetRetirementPage>();
      for (AssetRetirement main : assetRetirementList) {
          AssetRetirementPage vo = new AssetRetirementPage();
          BeanUtils.copyProperties(main, vo);
          List<AssetRetirementDetail> assetRetirementDetailList = assetRetirementDetailService.selectByMainId(main.getId());
          vo.setAssetRetirementDetailList(assetRetirementDetailList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "资产清理表列表");
      mv.addObject(NormalExcelConstants.CLASS, AssetRetirementPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("资产清理表数据", "导出人:"+sysUser.getRealname(), "资产清理表"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<AssetRetirementPage> list = ExcelImportUtil.importExcel(file.getInputStream(), AssetRetirementPage.class, params);
              for (AssetRetirementPage page : list) {
                  AssetRetirement po = new AssetRetirement();
                  BeanUtils.copyProperties(page, po);
                  assetRetirementService.saveMain(po, page.getAssetRetirementDetailList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
    }

}
