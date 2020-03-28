package org.jeecg.modules.testdemo1.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.testdemo1.entity.Demo0206;
import org.jeecg.modules.testdemo1.service.IDemo0206Service;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 测试用例
 * @Author: jeecg-boot
 * @Date:   2020-02-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="测试用例")
@RestController
@RequestMapping("/testdemo1/demo0206")
public class Demo0206Controller extends JeecgController<Demo0206, IDemo0206Service> {
	@Autowired
	private IDemo0206Service demo0206Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param demo0206
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "测试用例-分页列表查询")
	@ApiOperation(value="测试用例-分页列表查询", notes="测试用例-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Demo0206 demo0206,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Demo0206> queryWrapper = QueryGenerator.initQueryWrapper(demo0206, req.getParameterMap());
		Page<Demo0206> page = new Page<Demo0206>(pageNo, pageSize);
		IPage<Demo0206> pageList = demo0206Service.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param demo0206
	 * @return
	 */
	@AutoLog(value = "测试用例-添加")
	@ApiOperation(value="测试用例-添加", notes="测试用例-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Demo0206 demo0206) {
		demo0206Service.save(demo0206);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param demo0206
	 * @return
	 */
	@AutoLog(value = "测试用例-编辑")
	@ApiOperation(value="测试用例-编辑", notes="测试用例-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Demo0206 demo0206) {
		demo0206Service.updateById(demo0206);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试用例-通过id删除")
	@ApiOperation(value="测试用例-通过id删除", notes="测试用例-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		demo0206Service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "测试用例-批量删除")
	@ApiOperation(value="测试用例-批量删除", notes="测试用例-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.demo0206Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试用例-通过id查询")
	@ApiOperation(value="测试用例-通过id查询", notes="测试用例-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Demo0206 demo0206 = demo0206Service.getById(id);
		return Result.ok(demo0206);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param demo0206
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Demo0206 demo0206) {
      return super.exportXls(request, demo0206, Demo0206.class, "测试用例");
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
      return super.importExcel(request, response, Demo0206.class);
  }

}
