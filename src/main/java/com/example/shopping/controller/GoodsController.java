package com.example.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author StephenGAO
 * @version 1.0
 * @date 2020/4/29 14:18
 * @discription
 */
@Controller
@RequestMapping("/goods/")
public class GoodsController
{

	@RequestMapping("getList")
	public String getList(String account, String password, HttpServletRequest request)
	{
		return "goodsmanage";
	}

}
