package com.example.shopping.service.impl;

import com.example.shopping.dao.GoodsMapper;
import com.example.shopping.dao.UserMapper;
import com.example.shopping.entity.Shopinfo;
import com.example.shopping.entity.TableModel;
import com.example.shopping.entity.Userinfo;
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

	@Override
	public int buyGoods(String sid, String total, String price, String num, HttpServletRequest request)
	{
		//获取当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String ttime = sdf.format(new Date());

		//获取当前用户数据
		Userinfo user = (Userinfo) request.getSession().getAttribute("user");
		String uaccount = user.getUaccount();
		String umoney = Integer.valueOf(user.getUmoney())-Integer.valueOf(total)+"";
		userMapper.updateMoney(uaccount,umoney);



		return 0;
	}

}
