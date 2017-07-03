package org.alex.admin.web.controller;

import java.util.Date;

import javax.validation.Valid;

import org.alex.admin.core.bean.Rest;
import org.alex.admin.web.entity.SysUser;
import org.alex.admin.web.service.ISysUserService;
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
/**
 * 标准的Rest接口,实例控制器
 * Created by Gaojun.Zhou 2017年6月8日
 */
@Controller
@RequestMapping("/user")
public class UserController{  
	
	@Autowired private ISysUserService sysUserService;
	
	
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
	
	@RequestMapping("/list")
	public String list(){
		return "user/list";
	}
	
	@RequestMapping("/add")
	public String add(){
		return "user/add";
	}
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public Rest doAdd(@Valid SysUser user,BindingResult result){
		
		if(result.hasErrors()){
			String firstError = result.getFieldErrors().get(0).getDefaultMessage();
			return Rest.failure(firstError);
		}
		user.setCreateTime(new Date());
		sysUserService.insert(user);
		return Rest.ok("添加成功!");
	}
	
	
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
	@ResponseBody
	@RequestMapping("/delete")
	public Rest delete(String[] id){
		
		if(ArrayUtils.isEmpty(id)){
			return Rest.failure("客户端传入参数不能为空");
		}
		boolean b = true;
		for(String d : id){
			if(!sysUserService.deleteById(d)){
				b = false;
			}
		}
		return b ? Rest.ok("删除成功") : Rest.failure("删除失败");
	}
	
	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(String id,Model model){
		
		SysUser user = sysUserService.selectById(id);
		model.addAttribute("user",user);
		return "user/edit";
	}
	
	/**
	 * 执行编辑用户
	 * @param user
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doEdit")
	public Rest doEdit(@Valid SysUser user,BindingResult result){
		
		if(result.hasErrors()){
			String firstError = result.getFieldErrors().get(0).getDefaultMessage();
			return Rest.failure(firstError);
		}
		sysUserService.updateById(user);
		return Rest.ok("编辑成功!");
	}
}
