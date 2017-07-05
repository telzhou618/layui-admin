package org.alex.admin.web.service;

import org.alex.admin.web.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
public interface ISysUserService extends IService<SysUser> {

	/**
	 * 创建用户
	 * @param user
	 * @param roleId
	 */
	void addUser(SysUser user, String[] roleIds);
	
	/**
	 * 更新用户
	 * @param user
	 * @param roleIds
	 */
	void updateUser(SysUser user, String[] roleIds);

	/**
	 * 删除用户
	 * @param id
	 */
	void deleteUser(String[] id);
	
}
