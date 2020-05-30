package com.achp.service;

import com.achp.domain.Orders;

import java.util.List;

public interface OrderService {
    List<Orders> findAll(int page,int size) throws Exception;

    Orders findById(String id) throws Exception;
}
