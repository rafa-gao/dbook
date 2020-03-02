package com.rafagao.dbook.member.service.impl;

import com.rafagao.dbook.common.api.ResultCode;
import com.rafagao.dbook.common.exception.DBookUserException;
import com.rafagao.dbook.dao.DbookMemberMapper;
import com.rafagao.dbook.domain.DbookMember;
import com.rafagao.dbook.member.dto.MemberInfo;
import com.rafagao.dbook.member.dto.param.MemberRegisterParam;
import com.rafagao.dbook.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rafa gao
 */

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Autowired
    private DbookMemberMapper dbookMemberMapper;

    @Override
    public MemberInfo register(MemberRegisterParam memberRegisterParam) {
        // 查询用户
        DbookMember dbookMember = dbookMemberMapper.selectByUserName(memberRegisterParam.getUsername());
        if (dbookMember!=null){
            throw new DBookUserException(ResultCode.DUPLICATE_USERNAME);
        }
        // 注册用户
        DbookMember newMember = new DbookMember();
        BeanUtils.copyProperties(memberRegisterParam,newMember);
        int insert = dbookMemberMapper.insert(newMember);
        // 相等就注册成功
        if (insert==0){
            log.error("【用户注册】，失败，dbookMember={}",newMember);
            throw new DBookUserException(ResultCode.REGISTER_FAILED);
        }
        MemberInfo memberInfo = new MemberInfo();
        BeanUtils.copyProperties(newMember,memberInfo);
        return memberInfo;
    }

    @Override
    public DbookMember getByUsername(String username) {
        DbookMember dbookMember = dbookMemberMapper.selectByUserName(username);
        return dbookMember;
    }

    /**
     * 当密码不正确是抛出错误
     *
     */
    @Override
    public DbookMember login(String username, String password) {

        DbookMember dbookMember = dbookMemberMapper.selectByUserName(username);
        // 用户不存在
        if (dbookMember==null){
            log.error("【用户登录】失败，username={},password={}",username,password);
            throw new DBookUserException(ResultCode.USER_NOT_EXIT);
        }
        // 验证密码
        if (!dbookMember.getPassword().equals(password)){
            log.error("【用户登录】失败，username={},password={}",username,password);
            throw new DBookUserException(ResultCode.PASSEORD_ERROR);
        }
        return dbookMember;
    }
}
