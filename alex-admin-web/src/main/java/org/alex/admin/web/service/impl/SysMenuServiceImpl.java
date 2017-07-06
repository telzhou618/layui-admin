package org.alex.admin.web.service.impl;

import java.util.List;
import java.util.Map;

import org.alex.admin.web.entity.SysMenu;
import org.alex.admin.web.entity.SysRoleMenu;
import org.alex.admin.web.mapper.SysMenuMapper;
import org.alex.admin.web.mapper.SysRoleMenuMapper;
import org.alex.admin.web.service.ISysMenuService;
import org.alex.admin.web.util.BaseUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Splitter;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private SysMenuMapper sysMenuMapper;

	/**
	 * 分配权限
	 */
	@Override
	public void updateAuth(String roleId, String menuIds) {
		// TODO Auto-generated method stub

		sysRoleMenuMapper.delete(new EntityWrapper<SysRoleMenu>().eq("roleId", roleId));
		if (StringUtils.isNotBlank(menuIds)) {
			List<String> menuIdList = Splitter.on(",").splitToList(menuIds);
			for (String menuId : menuIdList) {
				SysRoleMenu roleMenu = new SysRoleMenu();
				roleMenu.setId(BaseUtil.uuid());
				roleMenu.setMenuId(menuId);
				roleMenu.setRoleId(roleId);
				sysRoleMenuMapper.insert(roleMenu);
			}
		}

	}

	@Override
	public List<Map<String, Object>> selectMenuByUid(String uid, String pid) {
		// TODO Auto-generated method stub
		return sysMenuMapper.selectMenuByUid(uid, pid);
	}

	@Override
	public List<String> selectResourceByUid(String uid) {
		// TODO Auto-generated method stub
		return sysMenuMapper.selectResourceByUid(uid);
	}

}
