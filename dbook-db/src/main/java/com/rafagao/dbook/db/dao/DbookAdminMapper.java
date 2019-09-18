package com.rafagao.dbook.db.dao;

import com.rafagao.dbook.db.domain.DbookAdmin;

public interface DbookAdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DbookAdmin record);

    int insertSelective(DbookAdmin record);

    DbookAdmin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DbookAdmin record);

    int updateByPrimaryKey(DbookAdmin record);
}