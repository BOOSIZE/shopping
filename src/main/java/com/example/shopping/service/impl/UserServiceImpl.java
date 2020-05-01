package com.example.shopping.service.impl;

import com.example.shopping.dao.MenuMapper;
import com.example.shopping.dao.UserMapper;
import com.example.shopping.entity.Menuinfo;
import com.example.shopping.entity.Userinfo;
import com.example.shopping.entity.TableModel;
import com.example.shopping.service.UserService;
import com.google.gson.Gson;
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
	private UserMapper userMapper;

	@Autowired(required = false)
	private MenuMapper menuMapper;

	@Override
	public String updateUserType(String utype,String uaccount)
	{
		String str="no";
		int n=userMapper.updateUserType(utype, uaccount);
		if(n>0)
		{
			str="yes";
		}
		return str;
	}

	@Override
	public String getList(Integer page, Integer limit, String uname,String urole)
	{
		if(urole!=null && urole.equals("全部"))
		{
			urole=null;
		}
		TableModel tableModel=new TableModel();
		tableModel.setCount(userMapper.getSum(uname,urole));
		tableModel.setData(userMapper.getList(limit,limit*(page-1),uname,urole));
		Gson gson=new Gson();
		return gson.toJson(tableModel);
	}



	@Override
	public String addAdmin(Userinfo userinfo)
	{
		String str="no";
		Userinfo userinfo2=userMapper.getUser(userinfo.getUaccount());
		if(userinfo2==null)
		{
			userinfo.setUrole(1+"");
			userinfo.setUregtime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			int n=userMapper.reg(userinfo);
			if(n>0)
			{
				str="yes";
			}
		}
		return str;
	}

	@Override
	public String reg(Userinfo userinfo)
	{
		String str="no";
		userinfo.setUregtime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		userinfo.setUrole(2+"");
		int n=userMapper.reg(userinfo);
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
		Userinfo userinfo=userMapper.getUser(account);
		if(userinfo!=null)
		{
			str="no";
			if(userinfo.getUpassword().equals(password))
			{
				str="disable";
				if(userinfo.getUtype().equals("启用"))
				{
					List<Menuinfo> fathers= menuMapper.getFathers(userinfo.getUrole());
					HashMap<String,List<Menuinfo>> menus=new HashMap<>();
					for (Menuinfo menu : fathers)
					{
						List<Menuinfo> sons=menuMapper.getSons(menu.getMnum());
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
		Userinfo userinfo=userMapper.getUser(account);
		if(userinfo==null)
		{
			str="yes";
		}
		return str;
	}
}