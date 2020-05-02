package com.example.shopping.dao;

import com.example.shopping.entity.Carinfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface CarMapper
{
	@Insert("INSERT INTO CARINFO (SID,CNAME,CCOUNT,CMONEY,CTIME,UACCOUNT) VALUES (#{sid},#{cname},#{ccount},#{cmoney},#{ctime},#{uaccount})")
	public abstract int addCar(Carinfo carinfo);

	@Select("<script> SELECT * FROM CARINFO WHERE UACCOUNT=#{uaccount} " +
			"<when test='cname!=null'> AND CNAME LIKE CONCAT('%',#{cname},'%')</when>" +
			"ORDER BY CTIME DESC LIMIT #{limit} OFFSET #{end}</script>")
	public abstract List<Carinfo> getList(String uaccount,String cname,Integer limit,Integer end);

	@Select("<script> SELECT COUNT(*) FROM CARINFO  WHERE UACCOUNT=#{uaccount} " +
			"<when test='cname!=null'> AND CNAME LIKE CONCAT('%',#{cname},'%')</when></script>")
	public abstract int getSum(String uaccount,String cname);

	@Delete("DELETE FROM CARINFO WHERE CID=#{cid}")
	public abstract int deleteCar(Long cid);
}