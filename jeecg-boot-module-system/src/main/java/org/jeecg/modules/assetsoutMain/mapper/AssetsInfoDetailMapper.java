package org.jeecg.modules.assetsoutMain.mapper;

import java.util.List;
import org.jeecg.modules.assetsoutMain.entity.AssetsInfoDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 资产信息附表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
public interface AssetsInfoDetailMapper extends BaseMapper<AssetsInfoDetail> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<AssetsInfoDetail> selectByMainId(@Param("mainId") String mainId);
}
