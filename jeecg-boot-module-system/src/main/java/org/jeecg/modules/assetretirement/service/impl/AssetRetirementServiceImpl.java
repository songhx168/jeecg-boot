package org.jeecg.modules.assetretirement.service.impl;

import org.jeecg.modules.assetretirement.entity.AssetRetirement;
import org.jeecg.modules.assetretirement.entity.AssetRetirementDetail;
import org.jeecg.modules.assetretirement.mapper.AssetRetirementDetailMapper;
import org.jeecg.modules.assetretirement.mapper.AssetRetirementMapper;
import org.jeecg.modules.assetretirement.service.IAssetRetirementService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 资产清理表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Service
public class AssetRetirementServiceImpl extends ServiceImpl<AssetRetirementMapper, AssetRetirement> implements IAssetRetirementService {

	@Autowired
	private AssetRetirementMapper assetRetirementMapper;
	@Autowired
	private AssetRetirementDetailMapper assetRetirementDetailMapper;
	
	@Override
	@Transactional
	public void saveMain(AssetRetirement assetRetirement, List<AssetRetirementDetail> assetRetirementDetailList) {
		assetRetirementMapper.insert(assetRetirement);
		if(assetRetirementDetailList!=null && assetRetirementDetailList.size()>0) {
			for(AssetRetirementDetail entity:assetRetirementDetailList) {
				//外键设置
				entity.setAssetRetirementId(assetRetirement.getId());
				assetRetirementDetailMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(AssetRetirement assetRetirement,List<AssetRetirementDetail> assetRetirementDetailList) {
		assetRetirementMapper.updateById(assetRetirement);
		
		//1.先删除子表数据
		assetRetirementDetailMapper.deleteByMainId(assetRetirement.getId());
		
		//2.子表数据重新插入
		if(assetRetirementDetailList!=null && assetRetirementDetailList.size()>0) {
			for(AssetRetirementDetail entity:assetRetirementDetailList) {
				//外键设置
				entity.setAssetRetirementId(assetRetirement.getId());
				assetRetirementDetailMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		assetRetirementDetailMapper.deleteByMainId(id);
		assetRetirementMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			assetRetirementDetailMapper.deleteByMainId(id.toString());
			assetRetirementMapper.deleteById(id);
		}
	}
	
}
