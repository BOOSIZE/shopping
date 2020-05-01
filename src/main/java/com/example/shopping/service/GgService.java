package com.example.shopping.service;

import com.example.shopping.entity.Gginfo;

import javax.servlet.http.HttpServletRequest;

public interface GgService
{
	public abstract String getList(Integer limit,Integer page);

	public abstract String deleteGg(Long gid);

	public abstract String addGg(String text, HttpServletRequest request);
}