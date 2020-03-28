package org.jeecg.modules.assetretirement.mapper;

import java.util.List;
import org.jeecg.modules.assetretirement.entity.AssetRetirementDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 资产清理明细表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
public interface AssetRetirementDetailMapper extends BaseMapper<AssetRetirementDetail> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<AssetRetirementDetail> selectByMainId(@Param("mainId") String mainId);
}
