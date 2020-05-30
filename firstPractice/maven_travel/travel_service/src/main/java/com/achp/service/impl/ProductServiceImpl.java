package com.achp.service.impl;

import com.achp.dao.ProductDao;
import com.achp.domain.Product;
import com.achp.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll(int page, int size) throws Exception {
        //第一页起始，每页五条数据
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }

    @Override
    public void delProduct(String[] ids) throws Exception {
        for (String id : ids) {
            productDao.delProduct(id);
        }
    }

    @Override
    public Product findById(String id) throws Exception {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) throws Exception {
        productDao.update(product);
    }
}
