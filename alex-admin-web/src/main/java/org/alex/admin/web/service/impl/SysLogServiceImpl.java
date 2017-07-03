package org.alex.admin.web.service.impl;

import org.alex.admin.web.entity.SysLog;
import org.alex.admin.web.mapper.SysLogMapper;
import org.alex.admin.web.service.ISysLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
	
}
