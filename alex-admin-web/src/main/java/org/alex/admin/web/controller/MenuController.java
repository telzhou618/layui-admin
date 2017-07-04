package org.alex.admin.web.controller;

import javax.validation.Valid;

import org.alex.admin.core.bean.Rest;
import org.alex.admin.core.controller.PageController;
import org.alex.admin.web.entity.SysMenu;
import org.alex.admin.web.service.ISysMenuService;
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
@RequestMapping("/menu")
public class MenuController extends PageController<SysMenu, ISysMenuService>{  
	
	@Autowired private ISysMenuService sysMenuService;
	
	@ResponseBody
	@RequestMapping("/page")
	public Rest page(
		@RequestParam (required = true,defaultValue="1") Integer page,
    	@RequestParam (defaultValue="15")Integer size,String keyword,Model model){
		EntityWrapper<SysMenu> ew = new EntityWrapper<SysMenu>();
		ew.orderBy("code", true);
		if(StringUtils.isNotBlank(keyword)){
			ew.like("menuName", keyword);
		}
		Page<SysMenu> pageData = sysMenuService.selectPage(new Page<SysMenu>(page, size),ew);
		return Rest.okData(pageData);	
		
	}
	
	/**
	 * 执行新增菜单
	 * @param menu
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doAdd")
	public Rest doAdd(@Valid SysMenu menu,BindingResult result){
		
		if(result.hasErrors()){
			String firstError = result.getFieldErrors().get(0).getDefaultMessage();
			return Rest.failure(firstError);
		}
		menu.setPid("0");
		menu.setDeep(1);
		sysMenuService.insert(menu);
		return Rest.ok("添加成功!");
	}
	/**
	 * 执行编辑用户
	 * @param menu
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doEdit")
	public Rest doEdit(@Valid SysMenu menu,BindingResult result){
		if(result.hasErrors()){
			String firstError = result.getFieldErrors().get(0).getDefaultMessage();
			return Rest.failure(firstError);
		}
		if(menu == null || StringUtils.isBlank(menu.getId())){
			throw new RuntimeException("参数{id}不能为空");
		}
		sysMenuService.updateById(menu);
		return Rest.ok("编辑成功!");
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "menu";
	}

	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "menu";
	}

	/**
	 * 新增菜单
	 */
	@Override
	public String add(Model model) {
		// TODO Auto-generated method stub
		return super.add(model);
	}

	@Override
	public String edit(String id, Model model) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank((String)id)){
			throw new RuntimeException("参数{id}不能为空");
		}
		SysMenu sysMenu= sysMenuService.selectById(id);
		if(sysMenu == null){
			throw new RuntimeException("未查询到要编辑的菜单");
		}
		model.addAttribute("menu", sysMenu);
		if(sysMenu.getDeep() == 1){
			return "menu/edit";
		}else{
			model.addAttribute("pmenu",sysMenuService.selectById(sysMenu.getPid()));
			return "menu/edit_item";
		}
	}
	
	/**
	 * 添加子菜单
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/addItem")
	public String addItem(String id, Model model) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank((String)id)){
			throw new RuntimeException("参数{id}不能为空");
		}
		SysMenu sysMenu= sysMenuService.selectById(id);
		if(sysMenu == null){
			throw new RuntimeException("未查询到要操作的菜单");
		}
		model.addAttribute("menu", sysMenu);
		return "menu/add_item";
	}
	
	/**
	 * 执行添加子菜单
	 * @param menu
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doAddItem")
	public Rest doAddItem(@Valid SysMenu menu,BindingResult result){
		
		if(result.hasErrors()){
			String firstError = result.getFieldErrors().get(0).getDefaultMessage();
			return Rest.failure(firstError);
		}
		SysMenu pmenu = sysMenuService.selectById(menu.getPid());
		menu.setDeep(pmenu.getDeep() + 1);
		sysMenuService.insert(menu);
		return Rest.ok("添加成功!");
	}
}
