package com.example.shopping.service;

import javax.servlet.http.HttpServletRequest;

public interface CarService
{
	public abstract String getList(HttpServletRequest request,String cname,Integer limit,Integer page);

	public abstract String deleteCar(Long cid);

	public abstract String buy(HttpServletRequest request,Long sid,Long cid,String cname,Integer ccount,Integer cmoney);

}