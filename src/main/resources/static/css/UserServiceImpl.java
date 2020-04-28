package com.szp.htmlboot.service.impl;

import com.google.gson.Gson;
import com.szp.htmlboot.dao.MenuDao;
import com.szp.htmlboot.dao.UserDao;
import com.szp.htmlboot.entity.Menuinfo;
import com.szp.htmlboot.entity.TableModel;
import com.szp.htmlboot.entity.Userinfo;
import com.szp.htmlboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired(required = false)
	private UserDao userDao;

	@Override
	public String updateUserType(String utype,String uaccount)
	{
		String str="no";
		int n=userDao.updateUserType(utype, uaccount);
		if(n>0)
		{
			str="yes";
		}
		return str;
	}

	@Override
	public String getList(Integer page, Integer limit, String uname)
	{
		TableModel tableModel=new TableModel();
		tableModel.setCode(0);
		tableModel.setMsg("");
		tableModel.setCount(userDao.getSum(uname));
		tableModel.setData(userDao.getList(limit,limit*(page-1),uname));
		Gson gson=new Gson();
		return gson.toJson(tableModel);
	}

	@Autowired(required = false)
	private MenuDao menuDao;

	@Override
	public String reg(Userinfo userinfo)
	{
		String str="no";
		userinfo.setUregtime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		userinfo.setUrole(2+"");
		int n=userDao.reg(userinfo);
		if(n>0)
		{
			str="yes";
		}
		return str;
	}

	@Override
	public String log(String account, String password, HttpServletRequest request)
	{
		String str="noBody";
		Userinfo userinfo=userDao.getUser(account);
		if(userinfo!=null)
		{
			str="no";
			if(userinfo.getUpassword().equals(password))
			{
				str="disable";
				if(userinfo.getUtype().equals("启用"))
				{
					List<Menuinfo> fathers= menuDao.getFathers(userinfo.getUrole());
					HashMap<String,List<Menuinfo>> menus=new HashMap<>();
					for (Menuinfo menu : fathers)
					{
						List<Menuinfo> sons=menuDao.getSons(menu.getMnum());
						menus.put(menu.getMname(),sons);
					}
					request.getSession().setAttribute("user",userinfo);
					request.getSession().setAttribute("menus",menus);
					str="yes";
				}
			}
		}
		return str;
	}

	@Override
	public String checkAccount(String account)
	{
		String str="no";
		Userinfo userinfo=userDao.getUser(account);
		if(userinfo==null)
		{
			str="yes";
		}
		return str;
	}
}