package com.rafagao.dbook.admin.controller;

import com.rafagao.dbook.admin.dto.AdminLoginParam;
import com.rafagao.dbook.admin.service.AdminService;
import com.rafagao.dbook.common.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rafa gao
 */

@RestController
@RequestMapping("/admin/user")
public class AdminAuthController {

    @Autowired
    private AdminService adminService;

    /**
     * @param adminLoginParam 登陆的参数
     * @return 通用CommonResult
     */
    @PostMapping("login")
    public CommonResult login(@RequestParam AdminLoginParam adminLoginParam) {
        String token;
        //参数为null
        if (adminLoginParam==null){
            return CommonResult.validatedFailed(null,"用户名和密码不能为空");
        }
        try {
            token = adminService.login(adminLoginParam.getUserName(), adminLoginParam.getPassWord());
        } catch (BadCredentialsException e) {
            //密码验验证错误
            return CommonResult.validatedFailed(null,"密码或用户名错误");
        }
        return CommonResult.success(token);
    }

}
