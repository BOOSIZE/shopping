package com.example.shopping.service;

import javax.servlet.http.HttpServletRequest;

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
	String getList(String uaccount,String ttype,Integer page,Integer limit,HttpServletRequest request);


	/**
	 * 充值
	 * @param
	 * @return
	 */
	int chargeMoney(String tmoney,String ttype, HttpServletRequest request);
}
