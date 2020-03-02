package com.rafagao.dbook.service.impl;

import com.rafagao.dbook.dto.AdminParam;
import com.rafagao.dbook.service.AdminService;
import com.rafagao.dbook.util.JWTUtil;
import com.rafagao.dbook.dao.DbookAdminMapper;
import com.rafagao.dbook.domain.DbookAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 * @author rafa gao
 */

@Service
public class AdminServiceImpl implements AdminService {


    private static final Logger LOGGER= LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private DbookAdminMapper dbookAdminMapper;


//    @Autowired
    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

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
        String token=null;
        DbookAdmin dbookAdmin = dbookAdminMapper.selectByUserName(userName);
        if (dbookAdmin!=null){
            //编码过的密码
            String encodedPassword= dbookAdmin.getPassword();
            //暂时不实现权限管理
            if(!passwordEncoder.matches(passWord,encodedPassword)){
                throw new BadCredentialsException("密码不正确");
            }else {
                String id = dbookAdmin.getId().toString();
                token= JWTUtil.generateToken(id);
            }
        }


        return token;
    }

    /**
     * 当用户名为空的时候，返回null
     * @param userName 用户名
     * @return
     */
    @Override
    public DbookAdmin getAdminByUsername(String userName){
        if (userName==null){
            return null;
        }
        DbookAdmin dbookAdmin=dbookAdminMapper.selectByUserName(userName);
       return dbookAdmin;
    }

    @Override
    public DbookAdmin register(AdminParam adminParam) {
        DbookAdmin dbookAdmin=new DbookAdmin();
        BeanUtils.copyProperties(adminParam,dbookAdmin);

        dbookAdmin.setAddTime(new Date());
        dbookAdmin.setDeleted(false);
        dbookAdmin.setUpdateTime(new Date());
//        相同用户名不能添加
        DbookAdmin dbookAdmin1 = dbookAdminMapper.selectByUserName(adminParam.getUsername());
        if (dbookAdmin1!=null){
            throw new RuntimeException("用户名已经存在");
        }else {
            //对密码加密
            String encodePassword = passwordEncoder.encode(adminParam.getPassword());
            dbookAdmin.setPassword(encodePassword);
            int insert = dbookAdminMapper.insert(dbookAdmin);
            if(insert<=0){
                throw new RuntimeException("添加失败");
            }
        }
        return dbookAdmin;
    }


}
