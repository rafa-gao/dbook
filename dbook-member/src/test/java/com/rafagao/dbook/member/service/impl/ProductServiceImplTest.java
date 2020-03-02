package com.rafagao.dbook.member.service.impl;

import com.rafagao.dbook.domain.DbookProduct;
import com.rafagao.dbook.member.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void getDbookProductList() {

        List<DbookProduct> dbookProductList = productService.getDbookProductList();
    }
}