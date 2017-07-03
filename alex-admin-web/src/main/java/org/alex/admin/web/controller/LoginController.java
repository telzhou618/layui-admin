package org.alex.admin.web.controller;

import org.alex.admin.core.bean.Rest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 标准的Rest接口,实例控制器
 * Created by Gaojun.Zhou 2017年6月8日
 */
@RestController
@RequestMapping("/login")
public class LoginController{   
	
	/**
	 * 登录
	 * @return
	 */
	@PostMapping("/login")
	public Rest login(){
		
		return Rest.ok();
	}
	
	/**
	 * 注销
	 * @return
	 */
	@GetMapping("/lougout")
    public Rest logout(){
    	
    	return Rest.ok();
    }
}
