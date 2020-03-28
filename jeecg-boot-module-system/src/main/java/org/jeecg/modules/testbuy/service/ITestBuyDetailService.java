package org.jeecg.modules.testbuy.service;

import org.jeecg.modules.testbuy.entity.TestBuyDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 采购明细
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
public interface ITestBuyDetailService extends IService<TestBuyDetail> {

	public List<TestBuyDetail> selectByMainId(String mainId);
}
