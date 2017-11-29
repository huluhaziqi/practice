package com.lin.test.base;

import com.lin.test.common.util.PropertiesFileUtil;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.session.InvalidSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController {

    private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 统一异常处理
     *
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler
    public String exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        logger.error("统一异常处理: {}", e);
        request.setAttribute("ex", e);
        if (null != request.getHeader("X-Requested-With") && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
            request.setAttribute("requestHeader", "ajax");
        }
        if (e instanceof UnauthenticatedException) {
            return "/403.jsp";
        }
        if (e instanceof InvalidSessionException) {
            return "/error.jsp";
        }
        return "/error.jsp";
    }

    /**
     * 返回jsp试图
     * @param path
     * @return
     */
    public static String jsp(String path) {
        return path.concat(".jsp");
    }

    /**
     * 返回thymeleaf视图
     * @param path
     * @return
     */
    public static String thymeleaf(String path) {
        String folder = PropertiesFileUtil.getInstance().get("app.name");
        return "/".concat(folder).concat(path).concat(".html");
    }
}
