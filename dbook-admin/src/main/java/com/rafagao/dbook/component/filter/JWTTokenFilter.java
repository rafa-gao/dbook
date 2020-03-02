package com.rafagao.dbook.component.filter;

import com.rafagao.dbook.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT  过滤器  用来进行身份验证
 * @author rafa gao
 */

public class JWTTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER=LoggerFactory.getLogger(JWTTokenFilter.class);

    @Autowired
    private UserDetailsService userDetailsService;

//    @Value("${jwt.JWTHeader}")
    private  String JWTHeader="Authorization";

    /**
     * 自定义的Filter处理
     * @param
     * @return
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //拿到请求头，并且做验证
        String token = request.getHeader(JWTHeader);
        if (token!=null){
            //拿到用户名
            String username = JWTUtil.getUsernameFromToken(token);
            LOGGER.info("用户"+"{"+username+"}"+"尝试登录");

            if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //信息有效
                if (JWTUtil.validateToken(token,userDetails)) {
                    //给一个用户名密码认证
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                    LOGGER.info("用户"+"{"+username+"}"+"登陆成功");

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }


        filterChain.doFilter(request,response);

    }
}
