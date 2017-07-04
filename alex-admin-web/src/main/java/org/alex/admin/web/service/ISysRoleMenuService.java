package org.alex.admin.web.service;

import java.util.List;
import java.util.Map;

import org.alex.admin.web.entity.SysRoleMenu;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

	/**
	 * 获取当前角色权限
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> selectAuthByRoleId(String id);
	
}
