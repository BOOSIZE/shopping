package com.example.shopping.service.impl;

import com.example.shopping.dao.OrderMapper;
import com.example.shopping.entity.TableModel;
import com.example.shopping.entity.Userinfo;
import com.example.shopping.service.OrderService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class OrderServiceImpl  implements OrderService {

    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Override
    public String getList(Integer page, Integer limit,String ostatus, HttpSession session) {
        if (ostatus == null || ostatus.equals("")){ostatus = null;}
        Userinfo userinfo = (Userinfo) session.getAttribute("user");
        String uaccount = null;
        if ( userinfo.getUrole().equals("2")){
            uaccount = userinfo.getUaccount();
        }
        TableModel tableModel = new TableModel();
        tableModel.setCount(orderMapper.getSum(ostatus,uaccount));
        tableModel.setData(orderMapper.getList(limit,limit*(page-1),ostatus,uaccount));

        return new Gson().toJson(tableModel);
    }
}
