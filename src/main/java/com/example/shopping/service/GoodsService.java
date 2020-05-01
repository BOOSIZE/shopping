package com.example.shopping.service;

import com.example.shopping.entity.Shopinfo;
import com.example.shopping.entity.TableModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface GoodsService
{
	
	/**
	 * @Description:获取列表
	 * @Param [sname]
	 * @return com.example.shopping.entity.TableModel
	 **/
	String getList(String sname,Integer page,Integer limit);

	/**
	 * @Description:获取客户端列表
	 * @Param [sname, page, limit]
	 * @return java.lang.String
	 **/
	String getBuyList(String sname,Integer page,Integer limit);


	/**
	 * 新增商品信息
	 * @param
	 * @return
	 */
	String insertGoods(String sname,String smoney,String scount,String sendtime);

	/**
	 * @Description: 删除商品
	 * @Param [id]
	 * @return int
	 **/
	int deleteGoods(String id);

	/**
	 * @Description: 更新价格
	 * @Param [sid, price]
	 * @return int
	 **/
	int updatePrice(String sid,String smoney);


	/**
	 * @Description: 购买商品
	 * @Param [sid, total, price, num, request]
	 * @return int
	 **/
	int buyGoods(String sid, String oname, String total,String num, HttpServletRequest request);

}
