package org.alex.admin.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.alex.admin.web.bean.MenuTree;
import org.alex.admin.web.entity.SysMenu;

import com.google.gson.Gson;

/**
 * 常用工具了
 * Created by Gaojun.Zhou 2017年7月3日
 */
public class BaseUtil {

	/**
	 * 将数据库菜单对象转换为Ztree对象
	 * @param menuList
	 * @return
	 */
	public static List<MenuTree> trans2Tree(List<SysMenu> menuList) {
		// TODO Auto-generated method stub
		
		List<MenuTree> list = new ArrayList<MenuTree>();
		
		if(menuList != null && !menuList.isEmpty()){
			for(SysMenu menu : menuList){
				list.add(new MenuTree(menu));
			}
		}
		
		return list;
	}

	/**
	 * 转换为JSON字符串
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		// TODO Auto-generated method stub
		if(object!=null){
			return new Gson().toJson(object);
		}
		return "";
	}

	public static String uuid() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString().replaceAll("-","").toLowerCase();
	}
	
	
	public static void main(String[] args) {
		System.out.println(uuid());
	}

}
