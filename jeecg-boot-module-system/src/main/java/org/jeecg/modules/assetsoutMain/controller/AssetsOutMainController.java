package org.jeecg.modules.assetsoutMain.controller;

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
import org.jeecg.modules.assetsoutMain.entity.AssetsInfoDetail;
import org.jeecg.modules.assetsoutMain.entity.AssetsOutMain;
import org.jeecg.modules.assetsoutMain.vo.AssetsOutMainPage;
import org.jeecg.modules.assetsoutMain.service.IAssetsOutMainService;
import org.jeecg.modules.assetsoutMain.service.IAssetsInfoDetailService;
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
 * @Description: 资产出库主表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Api(tags="资产出库主表")
@RestController
@RequestMapping("/assetsoutMain/assetsOutMain")
@Slf4j
public class AssetsOutMainController {
	@Autowired
	private IAssetsOutMainService assetsOutMainService;
	@Autowired
	private IAssetsInfoDetailService assetsInfoDetailService;
	
	/**
	 * 分页列表查询
	 *
	 * @param assetsOutMain
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "资产出库主表-分页列表查询")
	@ApiOperation(value="资产出库主表-分页列表查询", notes="资产出库主表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AssetsOutMain assetsOutMain,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AssetsOutMain> queryWrapper = QueryGenerator.initQueryWrapper(assetsOutMain, req.getParameterMap());
		Page<AssetsOutMain> page = new Page<AssetsOutMain>(pageNo, pageSize);
		IPage<AssetsOutMain> pageList = assetsOutMainService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param assetsOutMainPage
	 * @return
	 */
	@AutoLog(value = "资产出库主表-添加")
	@ApiOperation(value="资产出库主表-添加", notes="资产出库主表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AssetsOutMainPage assetsOutMainPage) {
		AssetsOutMain assetsOutMain = new AssetsOutMain();
		BeanUtils.copyProperties(assetsOutMainPage, assetsOutMain);
		assetsOutMainService.saveMain(assetsOutMain, assetsOutMainPage.getAssetsInfoDetailList());
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param assetsOutMainPage
	 * @return
	 */
	@AutoLog(value = "资产出库主表-编辑")
	@ApiOperation(value="资产出库主表-编辑", notes="资产出库主表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AssetsOutMainPage assetsOutMainPage) {
		AssetsOutMain assetsOutMain = new AssetsOutMain();
		BeanUtils.copyProperties(assetsOutMainPage, assetsOutMain);
		AssetsOutMain assetsOutMainEntity = assetsOutMainService.getById(assetsOutMain.getId());
		if(assetsOutMainEntity==null) {
			return Result.error("未找到对应数据");
		}
		assetsOutMainService.updateMain(assetsOutMain, assetsOutMainPage.getAssetsInfoDetailList());
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "资产出库主表-通过id删除")
	@ApiOperation(value="资产出库主表-通过id删除", notes="资产出库主表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		assetsOutMainService.delMain(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "资产出库主表-批量删除")
	@ApiOperation(value="资产出库主表-批量删除", notes="资产出库主表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.assetsOutMainService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "资产出库主表-通过id查询")
	@ApiOperation(value="资产出库主表-通过id查询", notes="资产出库主表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AssetsOutMain assetsOutMain = assetsOutMainService.getById(id);
		if(assetsOutMain==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(assetsOutMain);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "资产信息附表集合-通过id查询")
	@ApiOperation(value="资产信息附表集合-通过id查询", notes="资产信息附表-通过id查询")
	@GetMapping(value = "/queryAssetsInfoDetailByMainId")
	public Result<?> queryAssetsInfoDetailListByMainId(@RequestParam(name="id",required=true) String id) {
		List<AssetsInfoDetail> assetsInfoDetailList = assetsInfoDetailService.selectByMainId(id);
		return Result.ok(assetsInfoDetailList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param assetsOutMain
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AssetsOutMain assetsOutMain) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<AssetsOutMain> queryWrapper = QueryGenerator.initQueryWrapper(assetsOutMain, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<AssetsOutMain> queryList = assetsOutMainService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<AssetsOutMain> assetsOutMainList = new ArrayList<AssetsOutMain>();
      if(oConvertUtils.isEmpty(selections)) {
          assetsOutMainList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          assetsOutMainList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<AssetsOutMainPage> pageList = new ArrayList<AssetsOutMainPage>();
      for (AssetsOutMain main : assetsOutMainList) {
          AssetsOutMainPage vo = new AssetsOutMainPage();
          BeanUtils.copyProperties(main, vo);
          List<AssetsInfoDetail> assetsInfoDetailList = assetsInfoDetailService.selectByMainId(main.getId());
          vo.setAssetsInfoDetailList(assetsInfoDetailList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "资产出库主表列表");
      mv.addObject(NormalExcelConstants.CLASS, AssetsOutMainPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("资产出库主表数据", "导出人:"+sysUser.getRealname(), "资产出库主表"));
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
              List<AssetsOutMainPage> list = ExcelImportUtil.importExcel(file.getInputStream(), AssetsOutMainPage.class, params);
              for (AssetsOutMainPage page : list) {
                  AssetsOutMain po = new AssetsOutMain();
                  BeanUtils.copyProperties(page, po);
                  assetsOutMainService.saveMain(po, page.getAssetsInfoDetailList());
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
