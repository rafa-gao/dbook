package com.rafagao.dbook.dao;

import com.rafagao.dbook.domain.DbookMember;


/**
 * 详见
 */
public interface DbookMemberMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DbookMember record);

    int insertSelective(DbookMember record);

    DbookMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DbookMember record);

    int updateByPrimaryKey(DbookMember record);

    DbookMember selectByUserName(String userName);
}