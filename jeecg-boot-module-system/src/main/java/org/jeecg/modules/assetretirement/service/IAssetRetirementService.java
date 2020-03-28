package org.jeecg.modules.assetretirement.service;

import org.jeecg.modules.assetretirement.entity.AssetRetirementDetail;
import org.jeecg.modules.assetretirement.entity.AssetRetirement;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 资产清理表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
public interface IAssetRetirementService extends IService<AssetRetirement> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(AssetRetirement assetRetirement,List<AssetRetirementDetail> assetRetirementDetailList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(AssetRetirement assetRetirement,List<AssetRetirementDetail> assetRetirementDetailList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
