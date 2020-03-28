package org.jeecg.modules.assetmanagement.service;

import org.jeecg.modules.assetmanagement.entity.AssetClasses;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.exception.JeecgBootException;

/**
 * @Description: 资产类别表
 * @Author: jeecg-boot
 * @Date:   2020-02-29
 * @Version: V1.0
 */
public interface IAssetClassesService extends IService<AssetClasses> {

	/**根节点父ID的值*/
	public static final String ROOT_PID_VALUE = "0";
	
	/**树节点有子节点状态值*/
	public static final String HASCHILD = "1";
	
	/**树节点无子节点状态值*/
	public static final String NOCHILD = "0";

	/**新增节点*/
	void addAssetClasses(AssetClasses assetClasses);
	
	/**修改节点*/
	void updateAssetClasses(AssetClasses assetClasses) throws JeecgBootException;
	
	/**删除节点*/
	void deleteAssetClasses(String id) throws JeecgBootException;

}
