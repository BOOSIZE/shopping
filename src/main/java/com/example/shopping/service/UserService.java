package com.example.shopping.service;

import com.example.shopping.entity.Userinfo;

import javax.servlet.http.HttpServletRequest;

public interface UserService
{
	public abstract String reg(Userinfo userinfo);

	public abstract String log(String account, String password, HttpServletRequest request);

	public abstract String checkAccount(String account);

	public abstract String getList(Integer page, Integer limit, String uname);

	public abstract String updateUserType(String utype, String uaccount);
}