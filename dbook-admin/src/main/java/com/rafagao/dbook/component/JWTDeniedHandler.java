package com.rafagao.dbook.component;

import com.alibaba.fastjson.JSON;
import com.rafagao.dbook.common.api.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * 对于没有权限的请求的处理
 *
 * @author rafa gao
 */

public class JWTDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        if (!response.isCommitted()) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(JSON.toJSONString(CommonResult.forbidden(accessDeniedException.getMessage())));
            response.getWriter().flush();
        }
    }
}
