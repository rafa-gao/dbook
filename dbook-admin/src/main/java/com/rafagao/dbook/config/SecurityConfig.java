package com.rafagao.dbook.config;

import com.rafagao.dbook.bo.AdminUserDetails;
import com.rafagao.dbook.component.JWTDeniedHandler;
import com.rafagao.dbook.component.RestAuthenticationEntryPoint;
import com.rafagao.dbook.component.filter.JWTTokenFilter;
import com.rafagao.dbook.domain.DbookAdmin;
import com.rafagao.dbook.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * 自定义的安全配置
 *
 * @author rafa gao
// */
//@Configuration()
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminService adminService;


    /**
     * 用户名不存在，或者密码错误时，抛出异常
     *
     * @param
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            DbookAdmin dbookadmin = adminService.getAdminByUsername(username);
            if (dbookadmin!=null){
                return new AdminUserDetails(dbookadmin);
            }
            throw new UsernameNotFoundException("用户名或密码不存在");
        };
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable() //关闭csrf攻击
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //关闭Session
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js") //允许访问静态资源
                .permitAll()
                .antMatchers(HttpMethod.POST,"/admin/login","/admin/register").permitAll() //对登录和注册允许直接访问

                .antMatchers("/**")
                .permitAll() //测试时要全部运行

                .anyRequest()
                .authenticated();  //其余的请求都要经过安全认证
        //禁用缓存
        http.headers().cacheControl(); //原因未知

        //对请求添加Filter(在默认的授权登录窗口之前)
        http.addFilterBefore(new JWTTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        //自定义的授权结果返回
        http.exceptionHandling()
                .accessDeniedHandler(new JWTDeniedHandler())
                .authenticationEntryPoint(new RestAuthenticationEntryPoint());
    }





}
