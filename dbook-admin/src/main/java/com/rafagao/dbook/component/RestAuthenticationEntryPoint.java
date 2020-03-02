package com.rafagao.dbook.component;

import com.rafagao.dbook.common.api.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当接口没有权限的时候 返回的结果
 * 可以将其跳转到登录界面
 * @author rafa gao
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        if (isAjax(request)){
//            //是Ajax请求
//
//        }else {
//            //不是Ajax请求
//
//        }
        //直接返回没有认证
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(CommonResult.unauthorized(authException.getMessage()));
        response.getWriter().flush();

    }

    //判断是否为Ajax请求
    private boolean isAjax(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        return header!=null&&header.equals("XMLHttpRequest");
    }
}
