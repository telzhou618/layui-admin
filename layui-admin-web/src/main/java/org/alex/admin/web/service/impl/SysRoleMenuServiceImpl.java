package org.alex.admin.web.service.impl;

import java.util.List;
import java.util.Map;

import org.alex.admin.web.entity.SysRoleMenu;
import org.alex.admin.web.mapper.SysRoleMenuMapper;
import org.alex.admin.web.service.ISysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

	@Autowired private SysRoleMenuMapper sysRoleMenuMapper;
	 
	@Override
	public List<Map<String, Object>> selectAuthByRoleId(String id) {
		// TODO Auto-generated method stub
		return sysRoleMenuMapper.selectAuthByRoleId(id);
	}
	
}
