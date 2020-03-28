package org.jeecg.modules.assetsoutMain.service.impl;

import org.jeecg.modules.assetsoutMain.entity.AssetsOutMain;
import org.jeecg.modules.assetsoutMain.entity.AssetsInfoDetail;
import org.jeecg.modules.assetsoutMain.mapper.AssetsInfoDetailMapper;
import org.jeecg.modules.assetsoutMain.mapper.AssetsOutMainMapper;
import org.jeecg.modules.assetsoutMain.service.IAssetsOutMainService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 资产出库主表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Service
public class AssetsOutMainServiceImpl extends ServiceImpl<AssetsOutMainMapper, AssetsOutMain> implements IAssetsOutMainService {

	@Autowired
	private AssetsOutMainMapper assetsOutMainMapper;
	@Autowired
	private AssetsInfoDetailMapper assetsInfoDetailMapper;
	
	@Override
	@Transactional
	public void saveMain(AssetsOutMain assetsOutMain, List<AssetsInfoDetail> assetsInfoDetailList) {
		assetsOutMainMapper.insert(assetsOutMain);
		if(assetsInfoDetailList!=null && assetsInfoDetailList.size()>0) {
			for(AssetsInfoDetail entity:assetsInfoDetailList) {
				//外键设置
				entity.setAssetOutId(assetsOutMain.getId());
				assetsInfoDetailMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(AssetsOutMain assetsOutMain,List<AssetsInfoDetail> assetsInfoDetailList) {
		assetsOutMainMapper.updateById(assetsOutMain);
		
		//1.先删除子表数据
		assetsInfoDetailMapper.deleteByMainId(assetsOutMain.getId());
		
		//2.子表数据重新插入
		if(assetsInfoDetailList!=null && assetsInfoDetailList.size()>0) {
			for(AssetsInfoDetail entity:assetsInfoDetailList) {
				//外键设置
				entity.setAssetOutId(assetsOutMain.getId());
				assetsInfoDetailMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		assetsInfoDetailMapper.deleteByMainId(id);
		assetsOutMainMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			assetsInfoDetailMapper.deleteByMainId(id.toString());
			assetsOutMainMapper.deleteById(id);
		}
	}
	
}
