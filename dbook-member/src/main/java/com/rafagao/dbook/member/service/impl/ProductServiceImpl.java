package com.rafagao.dbook.member.service.impl;

import com.rafagao.dbook.dao.DbookProductMapper;
import com.rafagao.dbook.domain.DbookProduct;
import com.rafagao.dbook.member.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rafa gao
 */

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private DbookProductMapper dbookProductMapper;

    @Override
    public List<DbookProduct> getDbookProductList() {
        return dbookProductMapper.selectDbookProductByClassification(0,10,0);
    }
}
