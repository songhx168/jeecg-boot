package org.jeecg.modules.testbuy.service.impl;

import org.jeecg.modules.testbuy.entity.TestBuyDetail;
import org.jeecg.modules.testbuy.mapper.TestBuyDetailMapper;
import org.jeecg.modules.testbuy.service.ITestBuyDetailService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 采购明细
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Service
public class TestBuyDetailServiceImpl extends ServiceImpl<TestBuyDetailMapper, TestBuyDetail> implements ITestBuyDetailService {
	
	@Autowired
	private TestBuyDetailMapper testBuyDetailMapper;
	
	@Override
	public List<TestBuyDetail> selectByMainId(String mainId) {
		return testBuyDetailMapper.selectByMainId(mainId);
	}
}
