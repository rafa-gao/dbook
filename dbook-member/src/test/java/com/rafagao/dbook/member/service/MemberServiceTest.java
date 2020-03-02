package com.rafagao.dbook.member.service;

import com.rafagao.dbook.member.dto.param.MemberRegisterParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void register() {
        MemberRegisterParam memberRegisterParam = new MemberRegisterParam();
        memberRegisterParam.setUsername("cao");
        memberRegisterParam.setPassword("ahsfhasfhjaj");
        memberService.register(memberRegisterParam);
    }
}