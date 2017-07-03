package org.alex.admin.core.controller;

import java.io.Serializable;

import com.baomidou.mybatisplus.service.IService;

/**
 * Rest 风格之外的公共扩展接口
 * Created by Gaojun.Zhou 2017年6月8日
 */
public abstract class PageController<T extends Serializable,S extends IService<T>> extends CrudController<T,S>{

	/**
	 * 分页查询对象
	 * @param page
	 * @param size
	 * @return
	 */
	/*@ResponseBody
    @GetMapping("/page")  
    public  Rest page(
    	@RequestParam (required = true,defaultValue="1") Integer p,
    	@RequestParam (defaultValue="10")Integer size,
    	String kname,
		String kvalue){
		
		EntityWrapper<T> ew = new EntityWrapper<T>();
		if(StringUtils.isNotBlank(kname) && StringUtils.isNotBlank(kvalue)){
			ew.like(kname, kvalue);
		}
		Page<T> pageData = getS().selectPage(new Page<T>(p, size),ew);
		return Rest.okData(pageData);	
    }
	*/
	
}
