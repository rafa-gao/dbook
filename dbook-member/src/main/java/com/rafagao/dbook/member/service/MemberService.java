package com.rafagao.dbook.member.service;

import com.rafagao.dbook.domain.DbookMember;
import com.rafagao.dbook.member.dto.MemberInfo;
import com.rafagao.dbook.member.dto.param.MemberRegisterParam;

/**
 * @author rafa gao
 */

public interface MemberService {

    MemberInfo register(MemberRegisterParam memberRegisterParam);

    DbookMember getByUsername(String username);

    DbookMember login(String username,String password);
}
