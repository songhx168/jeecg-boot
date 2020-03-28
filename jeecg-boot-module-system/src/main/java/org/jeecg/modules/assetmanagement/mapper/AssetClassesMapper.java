package org.jeecg.modules.assetmanagement.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.assetmanagement.entity.AssetClasses;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 资产类别表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
public interface AssetClassesMapper extends BaseMapper<AssetClasses> {

	/**
	 * 编辑节点状态
	 * @param id
	 * @param status
	 */
	void updateTreeNodeStatus(@Param("id") String id,@Param("status") String status);

}
