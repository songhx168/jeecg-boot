package org.jeecg.modules.testbuy.mapper;

import java.util.List;
import org.jeecg.modules.testbuy.entity.TestBuyDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 采购明细
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
public interface TestBuyDetailMapper extends BaseMapper<TestBuyDetail> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<TestBuyDetail> selectByMainId(@Param("mainId") String mainId);
}
