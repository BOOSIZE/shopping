package com.example.shopping.dao;

import com.example.shopping.entity.Gginfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GgMapper
{
	@Insert("INSERT INTO GGINFO (UACCOUNT,GTEXT,GTIME) VALUES (#{uaccount},#{gtext},#{gtime})")
	public abstract int addGg(Gginfo gginfo);

	@Select("SELECT * FROM GGINFO ORDER BY GTIME DESC LIMIT #{limit} OFFSET #{end}")
	public abstract List<Gginfo> getList(Integer limit,Integer end);

	@Select("SELECT COUNT(*) FROM GGINFO")
	public abstract int getSum();

	@Delete("DELETE FROM GGINFO WHERE GID =#{gid}")
	public abstract int deleteGg(Long gid);

}