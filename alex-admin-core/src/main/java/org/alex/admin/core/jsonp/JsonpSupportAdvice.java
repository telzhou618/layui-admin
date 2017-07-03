package org.alex.admin.core.jsonp;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;
/**
 * JSONP支持
 * @author Administrator
 *
 */
@ControllerAdvice
public class JsonpSupportAdvice extends AbstractJsonpResponseBodyAdvice {
    public JsonpSupportAdvice() {
        //参数包含callback的时候 使用jsonp返回数据
        super("callback");
    }
}