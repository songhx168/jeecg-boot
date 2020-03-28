package org.jeecg.modules.assetsoutMain.service.impl;

import org.jeecg.modules.assetsoutMain.entity.AssetsInfoDetail;
import org.jeecg.modules.assetsoutMain.mapper.AssetsInfoDetailMapper;
import org.jeecg.modules.assetsoutMain.service.IAssetsInfoDetailService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 资产信息附表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Service
public class AssetsInfoDetailServiceImpl extends ServiceImpl<AssetsInfoDetailMapper, AssetsInfoDetail> implements IAssetsInfoDetailService {
	
	@Autowired
	private AssetsInfoDetailMapper assetsInfoDetailMapper;
	
	@Override
	public List<AssetsInfoDetail> selectByMainId(String mainId) {
		return assetsInfoDetailMapper.selectByMainId(mainId);
	}
}
