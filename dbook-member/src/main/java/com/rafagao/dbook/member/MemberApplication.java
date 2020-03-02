package com.rafagao.dbook.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author rafa gao
 */

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("com.rafagao.dbook.dao")
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class);
    }
}
