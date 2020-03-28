package org.jeecg.modules.assetsoutMain.service;

import org.jeecg.modules.assetsoutMain.entity.AssetsInfoDetail;
import org.jeecg.modules.assetsoutMain.entity.AssetsOutMain;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 资产出库主表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
public interface IAssetsOutMainService extends IService<AssetsOutMain> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(AssetsOutMain assetsOutMain,List<AssetsInfoDetail> assetsInfoDetailList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(AssetsOutMain assetsOutMain,List<AssetsInfoDetail> assetsInfoDetailList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
