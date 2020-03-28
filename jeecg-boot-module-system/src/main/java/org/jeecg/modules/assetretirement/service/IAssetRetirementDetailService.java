package org.jeecg.modules.assetretirement.service;

import org.jeecg.modules.assetretirement.entity.AssetRetirementDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 资产清理明细表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
public interface IAssetRetirementDetailService extends IService<AssetRetirementDetail> {

	public List<AssetRetirementDetail> selectByMainId(String mainId);
}
