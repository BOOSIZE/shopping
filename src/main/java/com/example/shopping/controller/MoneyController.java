package com.example.shopping.controller;

import com.example.shopping.entity.Userinfo;
import com.example.shopping.service.GoodsService;
import com.example.shopping.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author StephenGAO
 * @version 1.0
 * @date 2020/5/1 11:34
 * @discription 资金管理控制层
 */

@Controller
@RequestMapping("/money/")
public class MoneyController
{

	@Autowired
	private MoneyService moneyServiceImpl;

	/**
	 * @Description: 获取列表
	 * @Param [sname, page, limit, request]
	 * @return java.lang.String
	 **/
	@RequestMapping("getList")
	@ResponseBody
	public String getList(String ttype, Integer page, Integer limit, HttpServletRequest request)
	{
		Userinfo user = (Userinfo) request.getSession().getAttribute("user");
		String uaccount = user.getUaccount();
		return moneyServiceImpl.getList(uaccount,ttype,page,limit,request);
	}

	/**
	 * @Description: 充值
	 * @Param [reqMap]
	 * @return java.lang.String
	 **/
	@RequestMapping("chargeMoney")
	@ResponseBody
	public int insertGoods(String tmoney,String ttype,HttpServletRequest request){

		//返回充值消息
		return moneyServiceImpl.chargeMoney(tmoney,ttype,request);
	}


	@RequestMapping("returnMoney")
	@ResponseBody
	public int returnMoney(HttpServletRequest request){

		//返回账号余额
		return moneyServiceImpl.returnMoney(request);
	}

}
