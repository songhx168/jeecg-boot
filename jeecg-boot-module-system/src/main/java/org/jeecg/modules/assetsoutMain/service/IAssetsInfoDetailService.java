package org.jeecg.modules.assetsoutMain.service;

import org.jeecg.modules.assetsoutMain.entity.AssetsInfoDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 资产信息附表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
public interface IAssetsInfoDetailService extends IService<AssetsInfoDetail> {

	public List<AssetsInfoDetail> selectByMainId(String mainId);
}
