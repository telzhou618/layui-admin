package org.alex.admin.web.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.alex.admin.core.log.LogApi;
import org.alex.admin.core.log.LogBean;
import org.alex.admin.web.entity.SysLog;
import org.alex.admin.web.entity.SysUser;
import org.alex.admin.web.mapper.SysLogMapper;
import org.alex.admin.web.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService,LogApi {

	@Autowired private HttpServletRequest request;
	
	@Override
	public void log(LogBean log) {
		// TODO Auto-generated method stub
		
		SysLog sysLog = new SysLog();
		sysLog.setClientIp(log.getClientIp());
		sysLog.setLogContent(log.getLogContent());
		sysLog.setLogTime(log.getLogTime());
		sysLog.setLogTitle(log.getLogTitle());
		sysLog.setOther(log.getOther());
		sysLog.setRequestMethod(log.getRequestMethod());
		sysLog.setRequestParams(log.getRequestParams());
		sysLog.setRequestUrl(log.getRequestUrl());
		sysLog.setUserName(((SysUser)request.getSession().getAttribute("session_user")).getUserName());
		this.insert(sysLog);
	}
	
}
