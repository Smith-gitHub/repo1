package com.achp.service;

import com.achp.domain.Product;

import java.util.List;
public interface ProductService {
    List<Product> findAll(int page,int size) throws Exception;

    void save(Product product) throws Exception;

    void delProduct(String[] ids) throws Exception;

    Product findById(String id) throws Exception;

    void update(Product product) throws Exception;
}
