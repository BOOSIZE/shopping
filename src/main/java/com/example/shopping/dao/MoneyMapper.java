package com.example.shopping.dao;

import com.example.shopping.entity.Shopinfo;
import com.example.shopping.entity.Traninfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MoneyMapper
{

	/**
	 * @Description: 查询单个账户资金总条数
	 * @Param [sname]
	 * @return int
	 **/
	int queryMoneyWithParamTotalNum(String uaccount, String ttype);


	/**
	 * @Description: 获取单个账户列表
	 * @Param [sname]
	 * @return java.util.List<com.example.shopping.entity.Shopinfo>
	 **/
	List<Traninfo> queryMoneyWithParam(String uaccount, String ttype, int statrNum, int limit);


	/**
	 * 更新状态
	 * @param map
	 * @return
	 */
	public int updateNewsStatue(Map<String, String> map);

	/**
	 * 充值
	 * @param
	 * @return
	 */
	int chargeMoney(String uaccount, String tmoney, String ttime);

	/**
	 * @Description: 删除商品
	 * @Param [id]
	 * @return int
	 **/
	int deleteGoods(String sid);


	/**
	 * @Description: 更新价格
	 * @Param [sid, price]
	 * @return int
	 **/
	int updatePrice(String sid, String smoney);
}
