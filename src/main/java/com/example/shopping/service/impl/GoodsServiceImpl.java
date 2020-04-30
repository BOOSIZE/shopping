package com.example.shopping.service.impl;

import com.example.shopping.dao.GoodsMapper;
import com.example.shopping.entity.Shopinfo;
import com.example.shopping.entity.TableModel;
import com.example.shopping.service.GoodsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 新闻资讯业务实现类
 * @author Stephen
 */
@Service()
public class GoodsServiceImpl implements GoodsService
{

	@Autowired(required = false)
	private GoodsMapper goodsMapper;


	@Override
	public String getList(String sname,Integer page,Integer limit)
	{

		//设置起始和限制条数
		int statrdNum = ((page - 1) * limit);

		//设置回传的表格参数
		TableModel tableModel=new TableModel();
		tableModel.setCount(goodsMapper.queryNewsWithParamTotalNum(sname));
		tableModel.setData(goodsMapper.queryNewsWithParam(sname,statrdNum,limit));
		return new Gson().toJson(tableModel);
	}

	@Override
	public String insertGoods(String sname,String smoney,String scount,String sstarttime,String sendtime)
	{
		return new Gson().toJson(goodsMapper.insertGoods(sname,smoney,scount,sstarttime,sendtime));
	}

	@Override
	public List<Shopinfo> queryAllNews(String type)
	{
		return null;
	}

	@Override
	public TableModel queryAllNewsWithLimit(Map<String, String> map)
	{
		return null;
	}

	@Override
	public Shopinfo querySingleNews(int jid)
	{
		return null;
	}

	@Override
	public TableModel queryNewsWithParam(Map<String, String> map)
	{
		return null;
	}

	@Override
	public int updateNewsStatue(Map<String, String> map)
	{
		return 0;
	}

	@Override
	public int updateNewsContent(Map<String, String> map)
	{
		return 0;
	}


}
