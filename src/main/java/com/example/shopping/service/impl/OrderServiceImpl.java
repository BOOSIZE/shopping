package com.example.shopping.service.impl;

import com.example.shopping.dao.GoodsMapper;
import com.example.shopping.dao.MoneyMapper;
import com.example.shopping.dao.OrderMapper;
import com.example.shopping.dao.UserMapper;
import com.example.shopping.entity.*;
import com.example.shopping.service.GoodsService;
import com.example.shopping.service.OrderService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OrderServiceImpl  implements OrderService {

    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private GoodsMapper goodsMapper;

    @Autowired(required = false)
    private MoneyMapper moneyMapper;

    /**
     * 获取订单表格数据
     * @param page
     * @param limit
     * @param ostatus
     * @param session
     * @return
     */
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

    @Override
    public String changestatus(Orderinfo orderinfo) {
        String result = "";
        //修改商品数量
        int changeShop =  -1;
        Shopinfo shopinfo = goodsMapper.querySingleGoods(orderinfo.getSid()+"");
        if (orderinfo.getMsg().equals("yes")){
            orderinfo.setOstatus("已完成");
            //修改订单状态
            int changeOrder = orderMapper.changestatus(orderinfo);

            if (shopinfo != null){
                shopinfo.setSsole((Integer.valueOf(shopinfo.getSsole()) + Integer.valueOf(orderinfo.getOcount())) + "");
                changeShop = goodsMapper.updateSole(shopinfo.getSid()+"",shopinfo.getSsole());
//                changeShop = goodsMapper.updateSole(orderinfo.getSid()+"",shopinfo.getSsole(),shopinfo.getScount());
            }
            if (changeOrder > 0 && changeShop > 0 || changeShop == -1){
                result = "true";
            }

        }else if (orderinfo.getMsg().equals("no") || orderinfo.getMsg().equals("cancel")){
            orderinfo.setOstatus("已退款");
            //修改订单状态
            int changeOrder = orderMapper.changestatus(orderinfo);
            //修改用户金额
            Userinfo userinfo = userMapper.getUser(orderinfo.getUaccount());
            int money = Integer.valueOf(userinfo.getUmoney()) + Integer.valueOf(orderinfo.getOmoney());
            int changeUser = userMapper.updateMoney(orderinfo.getUaccount(),money+"");
            if (shopinfo != null){
                shopinfo.setScount((Integer.valueOf(shopinfo.getScount()) + Integer.valueOf(orderinfo.getOcount())) + "");
//                changeShop = goodsMapper.updateSole(orderinfo.getSid()+"",shopinfo.getSsole(),shopinfo.getScount());
                changeShop = goodsMapper.updateCount(shopinfo.getSid()+"",shopinfo.getScount());
            }
            //创建交易记录
            //获取当前时间
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            String ttime = sdf.format(new Date());

            int addTran = moneyMapper.chargeMoney(orderinfo.getUaccount(),orderinfo.getOmoney(),ttime,"退款");
            if (changeOrder > 0 && changeUser > 0 && changeShop > 0 || changeShop == -1 && addTran > 0 ){
                result = "true";
            }

        }
        return result;
    }

    @Override
    public String changeOrder(Orderinfo orderinfo) {
        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String ttime = sdf.format(new Date());
        String result = "";
        orderinfo.setOstatus("已评价");
        orderinfo.setOevtime(ttime);
        if (orderMapper.changeOrder(orderinfo) > 0){
            result = "true";
        }
        return result;
    }
}
