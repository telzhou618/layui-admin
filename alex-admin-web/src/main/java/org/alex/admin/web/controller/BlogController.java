package org.alex.admin.web.controller;

import org.alex.admin.core.anno.ForbidMethod;
import org.alex.admin.core.anno.Log;
import org.alex.admin.core.bean.Rest;
import org.alex.admin.core.controller.PageController;
import org.alex.admin.web.entity.Blog;
import org.alex.admin.web.service.IBlogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 标准的Rest接口,实例控制器
 * Created by Gaojun.Zhou 2017年6月8日
 */
@RestController
@RequestMapping("/blog")
@ForbidMethod({"delete"}) //禁止客户端调用delete方法,可传入多个参数
public class BlogController extends PageController<Blog,IBlogService>{  
	
    /**
     * 记录日志测试
     * @see @Log 记录日志只在方法执行成功返回后执行，通过 com.restful.api.core.log.LogAdvice AOP实现
     * @see 开发者可实现 LogApi接口的log方法完成记录日志具体的业务,此项目中完善service下的LogServiceImpl.java的log方法即可
     * @return rest
     */
    @GetMapping("/testLog")
    @Log(title="测试日志",value="这是日志内容,哈哈") 
    public Rest testLog(){
    	return Rest.ok();
    }

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "blog";
	}

	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "blog";
	}
    
}
