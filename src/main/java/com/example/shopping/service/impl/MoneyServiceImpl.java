package com.example.shopping.service.impl;

import com.example.shopping.dao.MoneyMapper;
import com.example.shopping.dao.UserMapper;
import com.example.shopping.entity.TableModel;
import com.example.shopping.entity.Userinfo;
import com.example.shopping.service.MoneyService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author StephenGAO
 * @version 1.0
 * @date 2020/5/1 11:40
 * @discription
 */
@Service
public class MoneyServiceImpl implements MoneyService
{

	@Autowired(required = false)
	private MoneyMapper moneyMapper;

	@Autowired(required = false)
	private UserMapper userMapper;

	/**
	 * @Description: 获取资金列表
	 * @Param [uaccount, ttype, page, limit]
	 * @return java.lang.String
	 **/
	@Override
	public String getList(String uaccount, String ttype, Integer page, Integer limit,HttpServletRequest request)
	{
		//设置起始和限制条数
		int statrdNum = ((page - 1) * limit);


		//设置回传的表格参数
		TableModel tableModel = new TableModel();
		tableModel.setCount(moneyMapper.queryMoneyWithParamTotalNum(uaccount,ttype));
		tableModel.setData(moneyMapper.queryMoneyWithParam(uaccount,ttype,statrdNum,limit));

//		//刷新session域
//		Userinfo user = userMapper.getUser(uaccount);
//		request.getSession().setAttribute("user",user);

		return new Gson().toJson(tableModel);
	}

	@Override
	public int chargeMoney(String tmoney, String ttype, HttpServletRequest request)
	{
		//获取当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String ttime = sdf.format(new Date());

		//获取当前用户
		Userinfo user = (Userinfo) request.getSession().getAttribute("user");
		String uaccount = user.getUaccount();

		//当前用户充值完之后的余额，并更新session
		String umoney = Integer.valueOf(user.getUmoney())+Integer.valueOf(tmoney)+"";
		userMapper.updateMoney(uaccount, umoney);
//		Userinfo user1 = userMapper.getUser(uaccount);
//		request.getSession().setAttribute("user",user1);

		return moneyMapper.chargeMoney(uaccount,tmoney,ttime,ttype);
	}

	/**
	 * @Description: 查询余额
	 * @Param [request]
	 * @return int
	 **/
	@Override
	public int returnMoney(HttpServletRequest request)
	{
		//获取当前用户
		Userinfo user = (Userinfo) request.getSession().getAttribute("user");
		String uaccount = user.getUaccount();
		int umoney = Integer.valueOf(userMapper.getUser(uaccount).getUmoney());
		return umoney;
	}
}
