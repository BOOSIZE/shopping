package com.example.shopping.controller;

import com.example.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("getList")
    @ResponseBody
    public String getList(Integer page, Integer limit,String ostatus, HttpSession session)
    {
        return orderService.getList(page,limit,ostatus,session);
    }

}
