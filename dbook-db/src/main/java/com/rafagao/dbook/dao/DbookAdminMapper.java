package com.rafagao.dbook.dao;

import com.rafagao.dbook.domain.DbookAdmin;



public interface DbookAdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DbookAdmin record);

    int insertSelective(DbookAdmin record);

    DbookAdmin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DbookAdmin record);

    int updateByPrimaryKey(DbookAdmin record);

    DbookAdmin selectByUserName(String userName);
}