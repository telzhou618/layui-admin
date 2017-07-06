package org.alex.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 错误页面
 * Created by Gaojun.Zhou 2017年7月6日
 */
@Controller
@RequestMapping("/error")
public class ErrorController{  
	
	@RequestMapping("/{page}")
	public String errorPage(@PathVariable String page) {
		// TODO Auto-generated method stub
		return "error/"+page;
	}
}
