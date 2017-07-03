package org.alex.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 首页控制器
 * Created by Gaojun.Zhou 2017年6月23日
 */
@Controller

public class IndexController{   
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	@RequestMapping("/welcome")
	public String welcome(){
		return "welcome";
	}
	
}
