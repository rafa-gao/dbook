package com.rafagao.dbook.member.controller;

import com.rafagao.dbook.common.api.CommonResult;
import com.rafagao.dbook.common.exception.DBookUserException;
import com.rafagao.dbook.domain.DbookMember;
import com.rafagao.dbook.member.dto.MemberInfo;
import com.rafagao.dbook.member.dto.param.MemberRegisterParam;
import com.rafagao.dbook.member.dto.param.UserLoginParam;
import com.rafagao.dbook.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rafa gao
 */

@RestController
@RequestMapping("/user")
public class MemberController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private MemberService memberService;


    /**
     * 用户登录接口
     *
     * @return
     */
    @PostMapping("/login")
    public CommonResult login(@RequestBody UserLoginParam userLoginParam){
        try {
            // 拿到数据
            DbookMember member = memberService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
            Map<String,String> dataMap=new HashMap<>();
            dataMap.put("username",member.getUsername());
            dataMap.put("icon", member.getIcon());
            dataMap.put("personalizedSignature",member.getPersonalizedSignature());
            return CommonResult.success(dataMap);
        }catch (DBookUserException e){
            return new CommonResult(null,e.getMessage(),e.getCode());
        }
    }


    /**
     * 用户注册接口
     *
     * @param
     * @return
     */
    @PostMapping("/register")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public CommonResult register(@RequestBody MemberRegisterParam memberRegisterParam){
        MemberInfo register = memberService.register(memberRegisterParam);
        return CommonResult.success(register.getUsername());
    }


}
