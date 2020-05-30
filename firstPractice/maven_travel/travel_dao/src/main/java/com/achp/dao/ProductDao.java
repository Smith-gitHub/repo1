package com.achp.dao;

import com.achp.domain.Product;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductDao {
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    @Insert(value = "insert into product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(REPLACE(UUID(),\"-\",\"\"),#{pro.productNum},#{pro.productName},#{pro.cityName},#{pro.departureTime},#{pro.productPrice},#{pro.productDesc},#{pro.productStatus})")
    void save(@Param("pro") Product product) throws Exception;

    @Select("select * from product where id = #{id}")
    Product findById(String id) throws Exception;

    @Delete("delete from product where id = #{id}")
    void delProduct(String id) throws Exception;

    @Update("update product set productNum = #{pro.productNum} ,productName = #{pro.productName} , cityName = #{pro.cityName},departureTime = #{pro.departureTime},productPrice = #{pro.productPrice},productDesc = #{pro.productDesc},productStatus = #{pro.productStatus} where id = #{pro.id}")
    void update(@Param("pro") Product product) throws Exception;
}
