package com.rafagao.dbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 全局的跨域配置
 * @author rafa gao
 */

@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter orsFilter(){


        CorsConfiguration corsConfiguration = new CorsConfiguration();

        //允许所有的跨域调用
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);

        //允许所有的请求方法
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        //允许所有的请求头部
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        //允许Cookies
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);

        return  new CorsFilter(urlBasedCorsConfigurationSource);

    }
}
