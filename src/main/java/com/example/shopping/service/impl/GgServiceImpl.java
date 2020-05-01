package com.example.shopping.service.impl;

import com.example.shopping.dao.GgMapper;
import com.example.shopping.entity.Gginfo;
import com.example.shopping.entity.TableModel;
import com.example.shopping.entity.Userinfo;
import com.example.shopping.service.GgService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GgServiceImpl implements GgService
{

	@Autowired(required = false)
	private GgMapper ggMapper;

	@Override
	public String getList(Integer limit,Integer page)
	{
		TableModel tableModel=new TableModel();

		tableModel.setCount(ggMapper.getSum());

		tableModel.setData(ggMapper.getList(limit,limit*(page-1)));

		Gson gson=new Gson();
		return gson.toJson(tableModel);
	}

	@Override
	public String deleteGg(Long gid)
	{
		String str="no";

		int n=ggMapper.deleteGg(gid);
		if (n>0)
		{
			str="yes";
		}
		return str;
	}

	@Override
	public String addGg(String text, HttpServletRequest request)
	{
		String str="no";
		Gginfo gginfo=new Gginfo();
		Userinfo userinfo=(Userinfo) request.getSession().getAttribute("user");
		gginfo.setGtime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		gginfo.setGtext(text);
		gginfo.setUaccount(userinfo.getUaccount());
		int n=ggMapper.addGg(gginfo);
		if(n>0)
		{
			str="yes";
		}
		return str;
	}
}