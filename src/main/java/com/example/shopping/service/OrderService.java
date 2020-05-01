package com.example.shopping.service;

import javax.servlet.http.HttpSession;

public interface OrderService {
    String getList(Integer page, Integer limit,String ostatus, HttpSession session);
}
