package org.jeecg.modules.assetretirement.service.impl;

import org.jeecg.modules.assetretirement.entity.AssetRetirementDetail;
import org.jeecg.modules.assetretirement.mapper.AssetRetirementDetailMapper;
import org.jeecg.modules.assetretirement.service.IAssetRetirementDetailService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 资产清理明细表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Service
public class AssetRetirementDetailServiceImpl extends ServiceImpl<AssetRetirementDetailMapper, AssetRetirementDetail> implements IAssetRetirementDetailService {
	
	@Autowired
	private AssetRetirementDetailMapper assetRetirementDetailMapper;
	
	@Override
	public List<AssetRetirementDetail> selectByMainId(String mainId) {
		return assetRetirementDetailMapper.selectByMainId(mainId);
	}
}
