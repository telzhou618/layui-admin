package org.alex.admin.core.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 记录业务日志
 * @author Administrator
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
	/**
	 * 日志标题
	 * @return
	 */
	String title() default "";
	/**
	 * 日志内容
	 * @return
	 */
	String value() default "";
}
