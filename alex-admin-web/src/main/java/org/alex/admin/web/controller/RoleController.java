package org.alex.admin.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.alex.admin.core.anno.Log;
import org.alex.admin.core.anno.Resource;
import org.alex.admin.core.bean.Rest;
import org.alex.admin.core.controller.PageController;
import org.alex.admin.web.entity.SysRole;
import org.alex.admin.web.entity.SysRoleMenu;
import org.alex.admin.web.service.ISysMenuService;
import org.alex.admin.web.service.ISysRoleMenuService;
import org.alex.admin.web.service.ISysRoleService;
import org.alex.admin.web.util.BaseUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
/**
 * 标准的Rest接口,实例控制器
 * Created by Gaojun.Zhou 2017年6月8日
 */
@Controller
@RequestMapping("/role")
public class RoleController extends PageController<SysRole, ISysRoleService>{  
	
	@Autowired private ISysRoleService sysRoleService;
	
	@Autowired private ISysMenuService sysMenuService;
	
	@Autowired private ISysRoleMenuService sysRoleMenuService;
	
	@Resource("listRole")
	@ResponseBody
	@RequestMapping("/page")
	public Rest page(
		@RequestParam (required = true,defaultValue="1") Integer page,
    	@RequestParam (defaultValue="10")Integer size,String keyword,Model model){
		
		EntityWrapper<SysRole> ew = new EntityWrapper<SysRole>();
		if(StringUtils.isNotBlank(keyword)){
			ew.like("roleName", keyword);
		}
		Page<SysRole> pageData = sysRoleService.selectPage(new Page<SysRole>(page, size),ew);
		
		return Rest.okData(pageData);	
		
	}
	
	@Log("新增角色")
	@Resource("addRole")
	@ResponseBody
	@RequestMapping("/doAdd")
	public Rest doAdd(@Valid SysRole role,BindingResult result){
		
		if(result.hasErrors()){
			String firstError = result.getFieldErrors().get(0).getDefaultMessage();
			return Rest.failure(firstError);
		}
		role.setCreateTime(new Date());
		sysRoleService.insert(role);
		return Rest.ok("添加成功!");
	}
	
	@Log("更新角色状态")
	@Resource("updateStateRole")
	@ResponseBody
	@RequestMapping("/roleState")
	public Rest roleState(String id,@RequestParam(value="roleState") Boolean bool){
		
		SysRole role = sysRoleService.selectById(id);
		role.setRoleState(bool?1:-1);
		sysRoleService.updateById(role);
		return Rest.ok();
	}
	
	/**
	 * 执行编辑角色
	 * @param role
	 * @param result
	 * @return
	 */
	@Log("编辑角色")
	@Resource("editRole")
	@ResponseBody
	@RequestMapping("/doEdit")
	public Rest doEdit(@Valid SysRole role,BindingResult result){
		
		if(result.hasErrors()){
			String firstError = result.getFieldErrors().get(0).getDefaultMessage();
			return Rest.failure(firstError);
		}
		sysRoleService.updateById(role);
		return Rest.ok("编辑成功!");
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "role";
	}

	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "role";
	}
	
	/**
	 * 分配权限
	 * @param id
	 * @param model
	 * @return
	 */
	@Resource("authRole")
	@RequestMapping("/auth")
	public String auth(String id,Model model){
		
		List<Map<String, Object>> menuList =  sysRoleMenuService.selectAuthByRoleId(id);
		/*List<MenuTree> menuTreeList = BaseUtil.trans2Tree(menuList);
		model.addAttribute("menuList", BaseUtil.toJson(menuTreeList));*/
		model.addAttribute("menuList", BaseUtil.toJson(menuList));
		model.addAttribute("role",sysRoleService.selectById(id));
		return "role/auth";
	}
	

	/**
	 * 执行角色授权
	 * @param role
	 * @param result
	 * @return
	 */
	@Log("角色授权")
	@Resource("authRole")
	@ResponseBody
	@RequestMapping("/doAuth")
	public Rest doAuth(String roleId,String menuIds){
		sysMenuService.updateAuth(roleId,menuIds);
		return Rest.ok("权限分配成功!");
	}

	@Log("删除角色")
	@Resource("deleteRole")
	@Override
	public Rest delete(String[] id) {
		// TODO Auto-generated method stub
		Rest rest = super.delete(id);
		if(rest.getStatus() == 200){
			for(String d : id){
				sysRoleMenuService.delete(new EntityWrapper<SysRoleMenu>().eq("roleId",d));
			}
		}
		return rest;
	}
	
	
}
