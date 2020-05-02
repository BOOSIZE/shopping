package com.example.shopping.service;

import com.example.shopping.entity.Orderinfo;

import javax.servlet.http.HttpSession;

public interface OrderService {
    String getList(Integer page, Integer limit,String ostatus, HttpSession session);

    String changestatus(Orderinfo orderinfo);

    String changeOrder(Orderinfo orderinfo);
}
