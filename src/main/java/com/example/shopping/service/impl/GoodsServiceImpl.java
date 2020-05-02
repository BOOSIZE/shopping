package com.example.shopping.service.impl;

import com.example.shopping.dao.*;
import com.example.shopping.entity.*;
import com.example.shopping.service.GoodsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 新闻资讯业务实现类
 *
 * @author Stephen
 */
@Service
public class GoodsServiceImpl implements GoodsService
{

	@Autowired(required = false)
	private GoodsMapper goodsMapper;

	@Autowired(required = false)
	private UserMapper userMapper;

	@Autowired(required = false)
	private OrderMapper orderMapper;

	@Autowired(required = false)
	private MoneyMapper moneyMapper;

	@Autowired(required = false)
	private CarMapper carMapper;

	@Override
	public String getList(String sname, Integer page, Integer limit)
	{

		//设置起始和限制条数
		int statrdNum = ((page - 1) * limit);
		List<Shopinfo> shopinfos = goodsMapper.queryNewsWithParam(sname, statrdNum, limit);

		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String now = sdf.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		String error = sdf.format(calendar.getTime());
		for (Shopinfo shopinfo : shopinfos)
		{
			shopinfo.setTips("未过期");
			if (shopinfo.getSendtime().compareTo(now) <= 0)
			{
				shopinfo.setTips("已过期");
			} else
			{
				if (shopinfo.getSendtime().compareTo(error) <= 0)
				{
					shopinfo.setTips("即将过期");
				}
			}
		}

		//设置回传的表格参数
		TableModel tableModel = new TableModel();
		tableModel.setCount(goodsMapper.queryNewsWithParamTotalNum(sname));
		tableModel.setData(shopinfos);
		return new Gson().toJson(tableModel);
	}

	/**
	 * @Description: 获取客户端列表
	 * @Param [sname, page, limit]
	 * @return java.lang.String
	 **/
	@Override
	public String getBuyList(String sname, Integer page, Integer limit)
	{
		//设置起始和限制条数
		int statrdNum = ((page - 1) * limit);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String now = sdf.format(new Date());

		//获取表格参数
		TableModel tableModel = new TableModel();
		tableModel.setCount(goodsMapper.queryBuyGoodsListNum(sname,now));
		tableModel.setData(goodsMapper.queryBuyGoodsList(sname,now,statrdNum,limit));
		return new Gson().toJson(tableModel);
	}

	/**
	 * @Description: 新增商品
	 * @Param [sname, smoney, scount, sendtime]
	 * @return java.lang.String
	 **/
	@Override
	public String insertGoods(String sname, String smoney, String scount,String sendtime)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String sstarttime = sdf.format(new Date());
		return new Gson().toJson(goodsMapper.insertGoods(sname, smoney, scount, sstarttime, sendtime));
	}


	@Override
	public int deleteGoods(String sid)
	{
		return goodsMapper.deleteGoods(sid);
	}

	@Override
	public int updatePrice(String sid, String smoney)
	{
		return goodsMapper.updatePrice(sid, smoney);
	}

	/**
	 * @Description: 购买商品
	 * @Param [sid, oname, total, price, num, request]
	 * @return int
	 **/
	@Override
	public int buyGoods(String sid,String oname, String total, String num, HttpServletRequest request)
	{
		//获取当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String ttime = sdf.format(new Date());

		//获取当前用户数据
		Userinfo user = (Userinfo) request.getSession().getAttribute("user");
		String uaccount = user.getUaccount();
		int userMoney = Integer.valueOf(user.getUmoney());
		String umoney = userMoney -Integer.valueOf(total)+"";

		//判断余额是否充足
		if(userMoney>=Integer.valueOf(total)){

			//插入订单
			Orderinfo orderinfo = new Orderinfo();
			orderinfo.setSid(Long.valueOf(sid));
			orderinfo.setOname(oname);
			orderinfo.setOcount(num);
			orderinfo.setOmoney(total);
			orderinfo.setOtime(ttime);
			orderinfo.setUaccount(uaccount);
			int i = orderMapper.addOrder(orderinfo);

			//更新用户余额,更新session域
			int j = userMapper.updateMoney(uaccount, umoney);
			Userinfo user1 = userMapper.getUser(uaccount);
			request.getSession().setAttribute("user",user1);

			//添加交易记录
			moneyMapper.chargeMoney(uaccount,total,ttime,"购买商品");

			//更新该产品销量
			Shopinfo shopinfo = goodsMapper.querySingleGoods(sid);
			String ssole = Integer.valueOf(shopinfo.getSsole())+Integer.valueOf(num)+"";
			String scount = Integer.valueOf(shopinfo.getScount())-Integer.valueOf(num)+"";
			int k = goodsMapper.updateSole(sid, scount);
			return 1;

		}else {
			return 0;
		}
	}

	/**
	 * @Description: 插入购物车
	 * @Param [sid, oname, total, num, request]
	 * @return int
	 **/
	@Override
	public int addCar(String sid, String oname, String total, String num, HttpServletRequest request)
	{
		//获取当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String ttime = sdf.format(new Date());

		//获取当前用户数据
		Userinfo user = (Userinfo) request.getSession().getAttribute("user");
		String uaccount = user.getUaccount();

		//插入购物车操作
		Carinfo carinfo = new Carinfo();
		carinfo.setSid(Long.valueOf(sid));
		carinfo.setCcount(num);
		carinfo.setCmoney(total);
		carinfo.setCname(oname);
		carinfo.setCtime(ttime);
		carinfo.setUaccount(uaccount);
		return carMapper.addCar(carinfo);
	}
}
