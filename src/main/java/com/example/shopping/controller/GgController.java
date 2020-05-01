package com.example.shopping.controller;

import com.example.shopping.service.GgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/gg/")
public class GgController
{
	@Autowired
	private GgService ggServiceImpl;

	@RequestMapping("getList")
	@ResponseBody
	public String getList(Integer limit,Integer page)
	{
		return ggServiceImpl.getList(limit, page);
	}

	@RequestMapping("deleteGg")
	@ResponseBody
	public String deleteGg(Long gid)
	{
		return ggServiceImpl.deleteGg(gid);
	}
	@RequestMapping("addGg")
	@ResponseBody
	public String addGg(String text, HttpServletRequest request)
	{
		return ggServiceImpl.addGg(text, request);
	}
}