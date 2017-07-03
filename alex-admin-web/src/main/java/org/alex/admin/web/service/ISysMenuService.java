package org.alex.admin.web.service;

import org.alex.admin.web.entity.SysMenu;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
public interface ISysMenuService extends IService<SysMenu> {
	
	/**
	 * 分配权限
	 * @param roleId
	 * @param menuIds
	 */
	void updateAuth(String roleId, String menuIds);
	
}
