package org.jeecg.modules.testbuy.service;

import org.jeecg.modules.testbuy.entity.TestBuyDetail;
import org.jeecg.modules.testbuy.entity.TestBuy;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 采购单
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
public interface ITestBuyService extends IService<TestBuy> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(TestBuy testBuy,List<TestBuyDetail> testBuyDetailList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TestBuy testBuy,List<TestBuyDetail> testBuyDetailList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
