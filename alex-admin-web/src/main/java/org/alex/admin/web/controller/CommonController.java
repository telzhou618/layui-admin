package org.alex.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * common模板控制器
 * Created by Gaojun.Zhou 2017年6月23日
 */
@Controller
public class CommonController{   
	
	@RequestMapping("/common/{c}")
	public String common(@PathVariable String c){
		return "common/" + c;
	}
	
	@RequestMapping("/pages/{p}")
	public String page(@PathVariable String p){
		return "pages/" + p;
	}
	
	@RequestMapping("/pages/{p}/{p2}")
	public String page(@PathVariable String p,@PathVariable String p2){
		return "pages/" + p + "/" + p2;
	}
}
