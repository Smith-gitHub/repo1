package com.achp.dao;

import com.achp.domain.Member;
import com.achp.domain.Orders;
import com.achp.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productID",javaType = Product.class,one = @One(select = "com.achp.dao.ProductDao.findById")),
    })
    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "travellers",column = "id",javaType = List.class,many = @Many(select = "com.achp.dao.TravellerDao.findByOrdersId")),
            @Result(property = "member",column = "memberID",javaType = Member.class,one = @One(select = "com.achp.dao.MemberDao.findById")),
            @Result(property = "product",column = "productID",javaType = Product.class,one = @One(select = "com.achp.dao.ProductDao.findById")),
    })
    Orders findById(String id) throws Exception;
}
