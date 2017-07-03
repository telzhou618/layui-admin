package org.alex.admin.web.component;

import org.alex.admin.core.advice.ExceptionAdvice;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * Created by Gaojun.Zhou 2017年6月20日
 */
@ControllerAdvice
@ResponseBody
public class RestExceptionAdvice extends ExceptionAdvice{

}
