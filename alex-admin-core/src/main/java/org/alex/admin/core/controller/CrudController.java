package org.alex.admin.core.controller;

import java.io.Serializable;
import java.util.Arrays;

import org.alex.admin.core.bean.Rest;
import org.alex.admin.core.ex.NotFindDataException;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.service.IService;

/**
 * Rest CRUD 超级控制器,目的在于所有继承该控制器的子控制器自带标准的5个Rest接口,包含CRUD
 * Created by Gaojun.Zhou 2017年6月8日
 * 
 * T 表示要操作的实体,实现序列化接口Serializable
 * S 表示调用对象的服务层接口,一班情况下要求存在
 * 
 */
public abstract class CrudController<T extends Serializable,S extends IService<T>> {
	/**
	 * 注入服务层
	 */
	@Autowired(required = false) private S s;
	
	public S getS() {
		return s;
	}

	/**
	 * 请求跳转到当前页面业务的list页面
	 * @return
	 */
    @GetMapping({"","/","list"})
    public  String list(){
    	return getViewName() + "/list" ;
    }
    
    /**
     * 增加
     * @return
     */
    @GetMapping("/add")
    public  String add(Model model){
    	return getViewName() + "/add" ;
    }
	
    /**
     * 编辑
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit")  
    public  String edit(String id,Model model){
		
    	if(StringUtils.isBlank((String)id)){
			throw new RuntimeException("参数{id}不能为空");
		}
		T t = s.selectById(id);
		if(t != null){
			model.addAttribute(getModelName(), t);
			return getViewName() + "/edit" ;
		}
		throw new NotFindDataException(String.format("id为[%s]的对象不存在",id));
    }
    
	/**
	 * 删除对象,可批量删除
	 * @param id
	 * @return
	 */
    @ResponseBody
	@RequestMapping("/delete")
	public Rest delete( String[] id){
		
    	boolean b = true;
    	
		if(ArrayUtils.isEmpty(id)){
			throw new RuntimeException("参数{id}不能为空");
		}else{
			b = s.deleteBatchIds(Arrays.asList(id));
		}
		return b ? Rest.ok("删除成功") : Rest.failure("糟糕,删除失败");
	}
    
    /**
     * 获取模板名称
     * @return
     */
     public abstract String getViewName();
     /**
      * 获取模型名称
      * @return
      */
     public abstract String getModelName();
}
