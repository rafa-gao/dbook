package com.rafagao.dbook.controller;

import com.rafagao.dbook.domain.DbookAdmin;
import com.rafagao.dbook.dto.AdminLoginParam;
import com.rafagao.dbook.dto.AdminParam;
import com.rafagao.dbook.service.AdminService;
import com.rafagao.dbook.common.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rafa gao
 */

@RestController
@RequestMapping("/admin")
public class AdminAuthController {

    @Autowired
    private AdminService adminService;


    /**
     * 管理员注册
     * @param
     * @return
     */
    @PostMapping("/register")
    public CommonResult register(@RequestBody AdminParam adminParam){
        DbookAdmin dbookAdmin = null;
        try {
            dbookAdmin = adminService.register(adminParam);
        } catch (Exception e) {
            return CommonResult.failed(null,e.getMessage());
        }
        return CommonResult.success(dbookAdmin);
    }

    /**
     * @param adminLoginParam 登陆的参数
     * @return 通用CommonResult
     */
    @PostMapping("/login")
    public CommonResult login(@RequestBody AdminLoginParam adminLoginParam) {
        String token;
        //参数为null
        if (adminLoginParam==null){
            return CommonResult.validatedFailed(null,"用户名和密码不能为空");
        }
//        System.out.println(adminLoginParam.getPassWord());

        try {
            token = adminService.login(adminLoginParam.getUserName(), adminLoginParam.getPassWord());

            if (token==null){
                return CommonResult.validatedFailed(null,"密码或用户名错误");
            }
        } catch (BadCredentialsException e) {
            //密码验验证错误
            return CommonResult.validatedFailed(null,"密码或用户名错误");
        }
        return CommonResult.success(token);
    }


    @GetMapping("/info")
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal){

        String name =principal.getName();
        DbookAdmin dbookAdmin = adminService.getAdminByUsername(name);
        String avatar = dbookAdmin.getAvatar();
        Map<String,String> map=new HashMap<>();
        map.put("userName",name);
        map.put("avatar",avatar);
        return CommonResult.success(map);
    }



}
