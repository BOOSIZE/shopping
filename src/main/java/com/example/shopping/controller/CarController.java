package com.example.shopping.controller;

import com.example.shopping.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/car/")
public class CarController
{
	@Autowired
	private CarService carServiceImpl;

	@RequestMapping("getList")
	@ResponseBody
	public String getList(HttpServletRequest request,String cname,Integer limit,Integer page)
	{
		return carServiceImpl.getList(request, cname, limit, page);
	}

	@RequestMapping("buy")
	@ResponseBody
	public String buy(HttpServletRequest request, Long sid, Long cid,String cname, Integer ccount, Integer cmoney)
	{
		return carServiceImpl.buy(request, sid,cid, cname, ccount, cmoney);
	}

	@RequestMapping("deleteCar")
	@ResponseBody
	public String deleteCar(Long cid)
	{
		return carServiceImpl.deleteCar(cid);
	}
}
