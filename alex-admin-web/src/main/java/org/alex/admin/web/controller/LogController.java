package org.alex.admin.web.controller;

import org.alex.admin.core.anno.Resource;
import org.alex.admin.core.bean.Rest;
import org.alex.admin.core.controller.PageController;
import org.alex.admin.web.entity.SysLog;
import org.alex.admin.web.service.ISysLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/log")
public class LogController extends PageController<SysLog, ISysLogService>{  
	
	@Autowired private ISysLogService sysLogService;
	
	
	/**
	 * 分页查询日志
	 * @param page
	 * @param size
	 * @param keyword
	 * @param model
	 * @return
	 */
	@Resource("listLog")
	@ResponseBody
	@RequestMapping("/page")
	public Rest page(
		@RequestParam (required = true,defaultValue="1") Integer page,
    	@RequestParam (defaultValue="10")Integer size,String keyword,Model model){
		
		EntityWrapper<SysLog> ew = new EntityWrapper<SysLog>();
		if(StringUtils.isNotBlank(keyword)){
			ew.like("logTitle", keyword).or().like("userName",keyword).or().like("logContent",keyword);
		}
		Page<SysLog> pageData = sysLogService.selectPage(new Page<SysLog>(page, size),ew);
		
		return Rest.okData(pageData);	
		
	}
	
	/**
	 * 查看参数
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/params")
	public String params(String id){
		return sysLogService.selectById(id).getRequestParams();
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "log";
	}



	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "log";
	}
}
