package com.rafagao.dbook.service;

import com.rafagao.dbook.domain.DbookAdmin;
import com.rafagao.dbook.dto.AdminParam;
import org.springframework.security.authentication.BadCredentialsException;

/**
 * 管理员
 * @author rafa gao
 */


public interface AdminService {

    /**
     * 返回生成的Token
     * 当密码错误的时候，返回null
     * @param userName 用户名
     * @return passWord 密码
     * @throws BadCredentialsException
     */
    String login(String userName,String passWord) throws BadCredentialsException;


    DbookAdmin getAdminByUsername(String userName);

    DbookAdmin register(AdminParam adminParam);
}
