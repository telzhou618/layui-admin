package org.alex.admin.web.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.alex.admin.core.anno.Log;
import org.alex.admin.core.anno.Resource;
import org.alex.admin.core.bean.Rest;
import org.alex.admin.core.controller.CrudController;
import org.alex.admin.web.entity.SysRole;
import org.alex.admin.web.entity.SysUser;
import org.alex.admin.web.entity.SysUserRole;
import org.alex.admin.web.service.ISysRoleService;
import org.alex.admin.web.service.ISysUserRoleService;
import org.alex.admin.web.service.ISysUserService;
import org.alex.admin.web.util.BaseUtil;
import org.apache.commons.lang.ArrayUtils;
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
import com.google.common.collect.Lists;
/**
 * 标准的Rest接口,实例控制器
 * Created by Gaojun.Zhou 2017年6月8日
 */
@Controller
@RequestMapping("/user")
public class UserController extends CrudController<SysUser, ISysUserService>{  
	
	@Autowired private ISysUserService sysUserService;
	@Autowired private ISysRoleService sysRoleService;
	@Autowired private ISysUserRoleService sysUserRoleService;
	
	
	@Resource("listUser")
	@ResponseBody
	@RequestMapping("/page")
	public Rest page(
		@RequestParam (required = true,defaultValue="1") Integer page,
    	@RequestParam (defaultValue="10")Integer size,String keyword,Model model){
		
		EntityWrapper<SysUser> ew = new EntityWrapper<SysUser>();
		if(StringUtils.isNotBlank(keyword)){
			ew.like("userName", keyword);
		}
		Page<SysUser> pageData = sysUserService.selectPage(new Page<SysUser>(page, size),ew);
		
		return Rest.okData(pageData);	
		
	}
	
	@Override
	public String add(Model model){
		List<SysRole> roleList = sysRoleService.selectList(new EntityWrapper<SysRole>().eq("roleState",1).orderBy("createTime"));
		model.addAttribute("roleList",roleList);
		return "user/add";
	}
	
	@Log("新增用户")
	@Resource("addUser")
	@ResponseBody
	@RequestMapping("/doAdd")
	public Rest doAdd(@Valid SysUser user,String password2,@RequestParam(value = "roleIds[]",required=false)String[] roleIds,BindingResult result){
		
		if(result.hasErrors()){
			String firstError = result.getFieldErrors().get(0).getDefaultMessage();
			return Rest.failure(firstError);
		}
		if(StringUtils.isBlank(user.getPassword()) 
				|| StringUtils.isBlank(password2)){
			throw new RuntimeException("密码和确认密码不能为空");
		}
		if(!user.getPassword().equals(password2)){
			throw new RuntimeException("两次输入的密码不一致");
		}
		user.setPassword(BaseUtil.MD5(user.getPassword()));
		user.setCreateTime(new Date());
		sysUserService.addUser(user,roleIds);
		return Rest.ok("添加成功!");
	}
	
	@Log("更新用户状态")
	@Resource("updateStateUser")
	@ResponseBody
	@RequestMapping("/userState")
	public Rest userState(String id,@RequestParam(value="userState") Boolean bool){
		
		SysUser user = sysUserService.selectById(id);
		user.setUserState(bool?1:-1);
		sysUserService.updateById(user);
		return Rest.ok();
	}
	
	/**
	 * 批量删除
	 * @param id
	 * @return
	 */
	@Log("删除用户")
	@Resource("deleteUser")
	@ResponseBody
	@RequestMapping("/delete")
	public Rest delete(String[] id){
		
		if(ArrayUtils.isEmpty(id)){
			return Rest.failure("客户端传入参数不能为空");
		}
		sysUserService.deleteUser(id);
		return Rest.ok("删除成功");
	}
	
	/**
	 * 编辑
	 * @return
	 */
	@Override
	public String edit(String id,Model model){
		
		model.addAttribute("user",sysUserService.selectById(id));
		model.addAttribute("roleList",sysRoleService.selectList(new EntityWrapper<SysRole>().eq("roleState",1).orderBy("createTime")));
		model.addAttribute("userRoleList", Lists.transform(sysUserRoleService.selectList(new EntityWrapper<SysUserRole>().eq("userId",id)),ur->ur.getRoleId()).toArray());
		return "user/edit";
	}
	
	/**
	 * 执行编辑用户
	 * @param user
	 * @param result
	 * @return
	 */
	@Log("编辑用户")
	@Resource("editUser")
	@ResponseBody
	@RequestMapping("/doEdit")
	public Rest doEdit(SysUser user,String password2,@RequestParam(value = "roleIds[]",required=false) String[] roleIds,BindingResult result){
		
		if(StringUtils.isBlank(user.getPassword()) && StringUtils.isBlank(password2)){
			user.setPassword(null);
		}else{
			if(!user.getPassword().equals(password2)){
				throw new RuntimeException("两次输入的密码不相等");
			}else{
				user.setPassword(BaseUtil.MD5(user.getPassword()));
			}
		}
		sysUserService.updateUser(user,roleIds);
		return Rest.ok("编辑成功!");
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "user";
	}

	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "user";
	}
}
