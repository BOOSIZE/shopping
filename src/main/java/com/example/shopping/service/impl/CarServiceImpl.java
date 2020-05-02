package com.example.shopping.service.impl;

import com.example.shopping.dao.*;
import com.example.shopping.entity.Shopinfo;
import com.example.shopping.entity.TableModel;
import com.example.shopping.entity.Userinfo;
import com.example.shopping.service.CarService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CarServiceImpl implements CarService
{
	@Autowired(required = false)
	private CarMapper carMapper;

	@Autowired(required = false)
	private UserMapper userMapper;

	@Autowired(required = false)
	private GoodsMapper goodsMapper;

	@Autowired(required = false)
	private MoneyMapper moneyMapper;

	@Autowired(required = false)
	private OrderMapper orderMapper;

	@Override
	public String getList(HttpServletRequest request, String cname, Integer limit, Integer page)
	{
		Userinfo userinfo=(Userinfo) request.getSession().getAttribute("user");
		TableModel tableModel=new TableModel();
		tableModel.setCount(carMapper.getSum(userinfo.getUaccount(),cname));
		tableModel.setData(carMapper.getList(userinfo.getUaccount(),cname,limit,limit*(page-1)));

		return new Gson().toJson(tableModel);
	}

	@Override
	public String deleteCar(Long cid)
	{
		String str="no";
		int n=carMapper.deleteCar(cid);
		if(n>0)
		{
			str="yes";
		}
		return str;
	}

	@Override
	public String buy(HttpServletRequest request, Long sid, String cname, Integer ccount, Integer cmoney)
	{
		String str="no";
		Shopinfo shopinfo=goodsMapper.querySingleGoods(sid+"");
		if(shopinfo!=null)
		{
			str="money";
			int oldMoney=cmoney/ccount;
			int newMoney=Integer.valueOf(shopinfo.getSmoney());
			if(newMoney==oldMoney)
			{
				str="time";
				String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				if(today.compareTo(shopinfo.getSendtime())>0)
				{
					str="count";
					int count=Integer.valueOf(shopinfo.getScount());
					if(count>=ccount)
					{
						str="nomoney";
						Userinfo userinfo=(Userinfo) request.getSession().getAttribute("user");
						if(Integer.valueOf(userinfo.getUmoney())>=cmoney)
						{
							str="yes";

						}
					}
				}

			}
		}
		return str;
	}
}
