package org.alex.admin.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alex.admin.core.anno.Resource;
import org.alex.admin.web.entity.SysUser;
import org.alex.admin.web.service.ISysMenuService;
import org.alex.admin.web.util.BaseUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 资源拦截器
 * @author Gaojun.Zhou
 * @date 2016年12月15日 下午2:35:27
 */
public class ResourceInterceptor extends HandlerInterceptorAdapter {
	
	private ISysMenuService sysMenuService;
	
	
	
	public ISysMenuService getSysMenuService() {
		return sysMenuService;
	}


	public void setSysMenuService(ISysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}



	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Resource rce =  handlerMethod.getMethodAnnotation(Resource.class);
			if(rce != null){
				
				String uid = ((SysUser)request.getSession().getAttribute("session_user")).getId();
				List<String> resAll = sysMenuService.selectResourceByUid(uid);
				
				if(resAll.contains(rce.value())){
					return true;
				}
				
				if (BaseUtil.isAjax(request)) {
					throw new RuntimeException("illegalAccess，无访问权限");
				} else {
					request.setAttribute("url",request.getRequestURL());
					request.getRequestDispatcher("/error/illegalAccess").forward(request, response);
					return false;
				}
			}
		}
		return true;
	}
}
