package com.rafagao.dbook.dao;

import com.rafagao.dbook.domain.DbookProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DbookProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DbookProduct record);

    int insertSelective(DbookProduct record);

    DbookProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DbookProduct record);

    int updateByPrimaryKeyWithBLOBs(DbookProduct record);

    int updateByPrimaryKey(DbookProduct record);

    List<DbookProduct> selectDbookProductByClassification(@Param("page")Integer page,
                                                          @Param("size") Integer size,
                                                          @Param("classification") Integer classification);
}