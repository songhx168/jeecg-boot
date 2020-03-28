package org.jeecg.modules.testbuy.controller;

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
import org.jeecg.modules.testbuy.entity.TestBuyDetail;
import org.jeecg.modules.testbuy.entity.TestBuy;
import org.jeecg.modules.testbuy.vo.TestBuyPage;
import org.jeecg.modules.testbuy.service.ITestBuyService;
import org.jeecg.modules.testbuy.service.ITestBuyDetailService;
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
 * @Description: 采购单
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Api(tags="采购单")
@RestController
@RequestMapping("/testbuy/testBuy")
@Slf4j
public class TestBuyController {
	@Autowired
	private ITestBuyService testBuyService;
	@Autowired
	private ITestBuyDetailService testBuyDetailService;
	
	/**
	 * 分页列表查询
	 *
	 * @param testBuy
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "采购单-分页列表查询")
	@ApiOperation(value="采购单-分页列表查询", notes="采购单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TestBuy testBuy,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TestBuy> queryWrapper = QueryGenerator.initQueryWrapper(testBuy, req.getParameterMap());
		Page<TestBuy> page = new Page<TestBuy>(pageNo, pageSize);
		IPage<TestBuy> pageList = testBuyService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param testBuyPage
	 * @return
	 */
	@AutoLog(value = "采购单-添加")
	@ApiOperation(value="采购单-添加", notes="采购单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TestBuyPage testBuyPage) {
		TestBuy testBuy = new TestBuy();
		BeanUtils.copyProperties(testBuyPage, testBuy);
		testBuyService.saveMain(testBuy, testBuyPage.getTestBuyDetailList());
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param testBuyPage
	 * @return
	 */
	@AutoLog(value = "采购单-编辑")
	@ApiOperation(value="采购单-编辑", notes="采购单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TestBuyPage testBuyPage) {
		TestBuy testBuy = new TestBuy();
		BeanUtils.copyProperties(testBuyPage, testBuy);
		TestBuy testBuyEntity = testBuyService.getById(testBuy.getId());
		if(testBuyEntity==null) {
			return Result.error("未找到对应数据");
		}
		testBuyService.updateMain(testBuy, testBuyPage.getTestBuyDetailList());
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "采购单-通过id删除")
	@ApiOperation(value="采购单-通过id删除", notes="采购单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		testBuyService.delMain(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "采购单-批量删除")
	@ApiOperation(value="采购单-批量删除", notes="采购单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.testBuyService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "采购单-通过id查询")
	@ApiOperation(value="采购单-通过id查询", notes="采购单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TestBuy testBuy = testBuyService.getById(id);
		if(testBuy==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(testBuy);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "采购明细集合-通过id查询")
	@ApiOperation(value="采购明细集合-通过id查询", notes="采购明细-通过id查询")
	@GetMapping(value = "/queryTestBuyDetailByMainId")
	public Result<?> queryTestBuyDetailListByMainId(@RequestParam(name="id",required=true) String id) {
		List<TestBuyDetail> testBuyDetailList = testBuyDetailService.selectByMainId(id);
		return Result.ok(testBuyDetailList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param testBuy
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TestBuy testBuy) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<TestBuy> queryWrapper = QueryGenerator.initQueryWrapper(testBuy, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<TestBuy> queryList = testBuyService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<TestBuy> testBuyList = new ArrayList<TestBuy>();
      if(oConvertUtils.isEmpty(selections)) {
          testBuyList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          testBuyList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<TestBuyPage> pageList = new ArrayList<TestBuyPage>();
      for (TestBuy main : testBuyList) {
          TestBuyPage vo = new TestBuyPage();
          BeanUtils.copyProperties(main, vo);
          List<TestBuyDetail> testBuyDetailList = testBuyDetailService.selectByMainId(main.getId());
          vo.setTestBuyDetailList(testBuyDetailList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "采购单列表");
      mv.addObject(NormalExcelConstants.CLASS, TestBuyPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("采购单数据", "导出人:"+sysUser.getRealname(), "采购单"));
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
              List<TestBuyPage> list = ExcelImportUtil.importExcel(file.getInputStream(), TestBuyPage.class, params);
              for (TestBuyPage page : list) {
                  TestBuy po = new TestBuy();
                  BeanUtils.copyProperties(page, po);
                  testBuyService.saveMain(po, page.getTestBuyDetailList());
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
