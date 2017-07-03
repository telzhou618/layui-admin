package org.alex.admin.core.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 禁止访问方法注解,标注在Controller上
 * Created by Gaojun.Zhou 2017年6月9日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ForbidMethod {

	String[] value() default {};
	
}
