package org.jeecg.modules.testbuy.service.impl;

import org.jeecg.modules.testbuy.entity.TestBuy;
import org.jeecg.modules.testbuy.entity.TestBuyDetail;
import org.jeecg.modules.testbuy.mapper.TestBuyDetailMapper;
import org.jeecg.modules.testbuy.mapper.TestBuyMapper;
import org.jeecg.modules.testbuy.service.ITestBuyService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 采购单
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Service
public class TestBuyServiceImpl extends ServiceImpl<TestBuyMapper, TestBuy> implements ITestBuyService {

	@Autowired
	private TestBuyMapper testBuyMapper;
	@Autowired
	private TestBuyDetailMapper testBuyDetailMapper;
	
	@Override
	@Transactional
	public void saveMain(TestBuy testBuy, List<TestBuyDetail> testBuyDetailList) {
		testBuyMapper.insert(testBuy);
		if(testBuyDetailList!=null && testBuyDetailList.size()>0) {
			for(TestBuyDetail entity:testBuyDetailList) {
				//外键设置
				entity.setBuyId(testBuy.getId());
				testBuyDetailMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(TestBuy testBuy,List<TestBuyDetail> testBuyDetailList) {
		testBuyMapper.updateById(testBuy);
		
		//1.先删除子表数据
		testBuyDetailMapper.deleteByMainId(testBuy.getId());
		
		//2.子表数据重新插入
		if(testBuyDetailList!=null && testBuyDetailList.size()>0) {
			for(TestBuyDetail entity:testBuyDetailList) {
				//外键设置
				entity.setBuyId(testBuy.getId());
				testBuyDetailMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		testBuyDetailMapper.deleteByMainId(id);
		testBuyMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			testBuyDetailMapper.deleteByMainId(id.toString());
			testBuyMapper.deleteById(id);
		}
	}
	
}
