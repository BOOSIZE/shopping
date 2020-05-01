package com.example.shopping.service;

/**
 * @author StephenGAO
 * @version 1.0
 * @date 2020/5/1 11:38
 * @discription
 */
public interface MoneyService
{
	/**
	 * @Description: 获取资金列表
	 * @Param [uaccount, ttype, page, limit]
	 * @return java.lang.String
	 **/
	String getList(String uaccount,String ttype,Integer page,Integer limit);


	/**
	 * 充值
	 * @param
	 * @return
	 */
	int chargeMoney(String uaccount,String tmoney);
}