package org.alex.admin.core.log;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.alex.admin.core.anno.Log;
import org.alex.admin.core.util.IpUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
/**
 * 正常业务日志记录
 * @author Administrator
 *
 */
@Aspect
@Component
public class LogAdvice {
	
	public static final Logger logger = Logger.getLogger(LogAdvice.class);
	
	/**
	 * 注入日志记录接口,若存在则记录日志，不存在就忽略
	 */
	@Autowired(required=false) LogApi logApi;
	
	@Pointcut("@annotation(org.alex.admin.core.anno.Log)")
	public void controllerAspect() {
		
	}
	/**
	 * 当方法正常返回是执行
	 * @param joinPoint
	 */
	@AfterReturning("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		Log log =  method.getAnnotation(Log.class);
		if(log != null){
			String logTitle = log.title();
			String logContent = log.value();
			logger.debug("logs:[logTitle:"+logTitle+"][logContent:"+logContent+"]");
			if(logApi != null){
				HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
				LogBean logBean = new LogBean();
				logBean.setLogTitle(logTitle);
				logBean.setLogTime(new Date());
				logBean.setLogContent(logContent);
				logBean.setRequestMethod(request.getMethod());
				logBean.setClientIp(IpUtil.getIpAddr(request));
				logBean.setRequestParams(new Gson().toJson(request.getParameterMap()));
				logBean.setRequestUrl(request.getRequestURI());
				logger.debug("logBean:"+logBean.toString());
				logApi.log(logBean);
			}else{
				logger.warn("LogApi not finish.");
			}
		}
	}
}
