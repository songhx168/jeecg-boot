package org.jeecg.modules.assetmanagement.service.impl;

import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.assetmanagement.entity.AssetClasses;
import org.jeecg.modules.assetmanagement.mapper.AssetClassesMapper;
import org.jeecg.modules.assetmanagement.service.IAssetClassesService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 资产类别表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
@Service
public class AssetClassesServiceImpl extends ServiceImpl<AssetClassesMapper, AssetClasses> implements IAssetClassesService {

	@Override
	public void addAssetClasses(AssetClasses assetClasses) {
		if(oConvertUtils.isEmpty(assetClasses.getPid())){
			assetClasses.setPid(IAssetClassesService.ROOT_PID_VALUE);
		}else{
			//如果当前节点父ID不为空 则设置父节点的hasChildren 为1
			AssetClasses parent = baseMapper.selectById(assetClasses.getPid());
			if(parent!=null && !"1".equals(parent.getHasChild())){
				parent.setHasChild("1");
				baseMapper.updateById(parent);
			}
		}
		baseMapper.insert(assetClasses);
	}
	
	@Override
	public void updateAssetClasses(AssetClasses assetClasses) {
		AssetClasses entity = this.getById(assetClasses.getId());
		if(entity==null) {
			throw new JeecgBootException("未找到对应实体");
		}
		String old_pid = entity.getPid();
		String new_pid = assetClasses.getPid();
		if(!old_pid.equals(new_pid)) {
			updateOldParentNode(old_pid);
			if(oConvertUtils.isEmpty(new_pid)){
				assetClasses.setPid(IAssetClassesService.ROOT_PID_VALUE);
			}
			if(!IAssetClassesService.ROOT_PID_VALUE.equals(assetClasses.getPid())) {
				baseMapper.updateTreeNodeStatus(assetClasses.getPid(), IAssetClassesService.HASCHILD);
			}
		}
		baseMapper.updateById(assetClasses);
	}
	
	@Override
	public void deleteAssetClasses(String id) throws JeecgBootException {
		AssetClasses assetClasses = this.getById(id);
		if(assetClasses==null) {
			throw new JeecgBootException("未找到对应实体");
		}
		updateOldParentNode(assetClasses.getPid());
		baseMapper.deleteById(id);
	}
	
	
	
	/**
	 * 根据所传pid查询旧的父级节点的子节点并修改相应状态值
	 * @param pid
	 */
	private void updateOldParentNode(String pid) {
		if(!IAssetClassesService.ROOT_PID_VALUE.equals(pid)) {
			Integer count = baseMapper.selectCount(new QueryWrapper<AssetClasses>().eq("pid", pid));
			if(count==null || count<=1) {
				baseMapper.updateTreeNodeStatus(pid, IAssetClassesService.NOCHILD);
			}
		}
	}

}
