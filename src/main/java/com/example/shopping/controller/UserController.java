package com.example.shopping.controller;

import com.example.shopping.entity.Userinfo;
import com.example.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user/")
public class UserController
{

	@Autowired
	private UserService userServiceImpl;


	@RequestMapping("log")
	@ResponseBody
	public String log(String account, String password, HttpServletRequest request)
	{
		return userServiceImpl.log(account, password, request);
	}

	@RequestMapping("reg")
	@ResponseBody
	public String reg(Userinfo userinfo)
	{
		return userServiceImpl.reg(userinfo);
	}

	@RequestMapping("updateUserType")
	@ResponseBody
	public String updateUserType(String utype,String uaccount)
	{
		return userServiceImpl.updateUserType(utype,uaccount);
	}

	@RequestMapping("getList")
	@ResponseBody
	public String getList(Integer page,Integer limit,String uname)
	{
		return userServiceImpl.getList(page, limit, uname);
	}

	@RequestMapping("checkAccount")
	@ResponseBody
	public String checkAccount(String account)
	{
		return userServiceImpl.checkAccount(account);
	}
}