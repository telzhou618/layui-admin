package org.alex.admin.web.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alex.admin.core.anno.PassLogin;
import org.alex.admin.web.util.BaseUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 登录拦截器
 * Created by Gaojun.Zhou 2017年7月6日
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub

		if (handler instanceof HandlerMethod) {
			/**
			 * 登录验证
			 */
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			
			PassLogin passLogin = method.getAnnotation(PassLogin.class);
			if (passLogin != null) {
				//跳过登录拦截
				return true;
			}else{
				//登录验证
				if(request.getSession().getAttribute("session_user") == null){
					if (BaseUtil.isAjax(request)) {
						throw new RuntimeException("您的登录已失效,请重新登录");
					} else {
						BaseUtil.clearRedirectLogin(request, response);
						return false;
					}
				}
				
			}
		}

		/**
		 * 通过拦截
		 */
		return true;
	}

}
