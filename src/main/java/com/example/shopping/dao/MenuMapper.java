package com.example.shopping.dao;

import com.example.shopping.entity.Menuinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper
{

	@Select("SELECT * FROM MENUINFO WHERE MROLE=#{mrole}")
	public abstract List<Menuinfo> getFathers(String mrole);

	@Select("SELECT * FROM MENUINFO WHERE FNUM=#{fnum}")
	public abstract List<Menuinfo> getSons(String fnum);
}