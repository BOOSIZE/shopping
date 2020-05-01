package com.example.shopping.service.impl;

import com.example.shopping.dao.GoodsMapper;
import com.example.shopping.dao.MoneyMapper;
import com.example.shopping.entity.TableModel;
import com.example.shopping.service.MoneyService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	/**
	 * @Description: 获取资金列表
	 * @Param [uaccount, ttype, page, limit]
	 * @return java.lang.String
	 **/
	@Override
	public String getList(String uaccount, String ttype, Integer page, Integer limit)
	{
		//设置起始和限制条数
		int statrdNum = ((page - 1) * limit);


		//设置回传的表格参数
		TableModel tableModel = new TableModel();
		tableModel.setCount(moneyMapper.queryMoneyWithParamTotalNum(uaccount,ttype));
		tableModel.setData(moneyMapper.queryMoneyWithParam(uaccount,ttype,statrdNum,limit));
		return new Gson().toJson(tableModel);
	}

	@Override
	public int chargeMoney(String uaccount, String tmoney)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String ttime = sdf.format(new Date());
		return moneyMapper.chargeMoney(uaccount,tmoney,ttime);
	}
}
