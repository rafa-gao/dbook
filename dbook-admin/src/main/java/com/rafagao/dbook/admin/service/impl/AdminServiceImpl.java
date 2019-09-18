package com.rafagao.dbook.admin.service.impl;

import com.rafagao.dbook.admin.service.AdminService;
import com.rafagao.dbook.admin.util.JWTUtil;
import com.rafagao.dbook.db.domain.DbookAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafa gao
 */

@Service
public class AdminServiceImpl implements AdminService {


    private static final Logger LOGGER= LoggerFactory.getLogger(AdminServiceImpl.class);

    private DbookAdmin dbookAdmin=new DbookAdmin();

//    @Autowired
    private PasswordEncoder passwordEncoder=new SCryptPasswordEncoder();

    /**
     * 返回生成的Token
     * 当密码错误的时候，返回null
     * @param userName 用户名
     * @return passWord 密码
     * @throws BadCredentialsException 密码错误的时候抛出该异常
     */
    @Override
    public String login(String userName, String passWord)throws BadCredentialsException {
        //返回的token
        String token;
        //编码过的密码
        String encodedPassword=dbookAdmin.getPassword();

        //暂时不实现权限管理
        if(passwordEncoder.matches(passWord,encodedPassword)){
            throw new BadCredentialsException("密码不正确");
        }else {
            String id = dbookAdmin.getId().toString();
            token= JWTUtil.generateToken(id);
        }

        return token;
    }
}
