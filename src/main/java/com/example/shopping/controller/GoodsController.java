package com.example.shopping.controller;

import com.example.shopping.entity.Userinfo;
import com.example.shopping.service.GoodsService;
import com.example.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author StephenGAO
 * @version 1.0
 * @date 2020/4/29 14:18
 * @discription
 */
@Controller
@RequestMapping("/goods/")
public class GoodsController
{

	@Autowired
	private GoodsService goodsServiceImpl;

	
	/**
	 * @Description: 获取列表
	 * @Param [sname, page, limit, request]
	 * @return java.lang.String
	 **/
	@RequestMapping("getList")
	@ResponseBody
	public String getList(String sname,Integer page,Integer limit,HttpServletRequest request)
	{
		Userinfo user = (Userinfo) request.getSession().getAttribute("user");
		String list = "";

		//判断角色
		if(user.getUrole().equals("1")){
			list = goodsServiceImpl.getList(sname, page, limit);
		}else if(user.getUrole().equals("2")){
			list = goodsServiceImpl.getBuyList(sname,page,limit);
		}
		return list;
	}


	/**
	 * @Description: 新增商品
	 * @Param [reqMap]
	 * @return java.lang.String
	 **/
	@RequestMapping("insertGoods")
	@ResponseBody
	public String insertGoods(String sname,String smoney,String scount,String sendtime){
		return goodsServiceImpl.insertGoods(sname,smoney,scount,sendtime);
	}

	/**
	 * @Description: 下架商品
	 * @Param [sid]
	 * @return java.lang.String
	 **/
	@RequestMapping("deleteGoods")
	@ResponseBody
	public int updateGoodsStatue(String sid){
		return goodsServiceImpl.deleteGoods(sid);
	}


	/**
	 * @Description: 更新价格
	 * @Param [sid, price]
	 * @return int
	 **/
	@RequestMapping("updatePrice")
	@ResponseBody
	public int updatePrice(String sid, String smoney){
		System.out.println(sid+" "+smoney);
		return goodsServiceImpl.updatePrice(sid,smoney);
	}
}
