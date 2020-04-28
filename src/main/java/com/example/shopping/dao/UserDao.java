package com.example.shopping.dao;

import com.example.shopping.entity.Userinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserDao
{
	@Update("UPDATE USERINFO SET UTYPE=#{utype} WHERE UACCOUNT=#{uaccount}")
	public abstract int updateUserType(String utype, String uaccount);

	@Insert("INSERT INTO USERINFO (UACCOUNT,UPASSWORD,UNAME,USEX,UADDRESS,UREGTIME,UMONEY,UROLE,UTYPE) VALUES (#{uaccount},#{upassword},#{uname},#{usex},#{uaddress},#{uregtime},'0',#{urole},'启用')")
	public abstract int reg(Userinfo userinfo);

	@Select("SELECT * FROM USERINFO WHERE UACCOUNT=#{uaccount}")
	public abstract Userinfo getUser(String uaccount);

	@Select("<script> SELECT * FROM USERINFO WHERE UROLE='2' " +
			"<when test='uname!=null'> AND UNAME LIKE CONCAT('%',#{uname},'%')</when>" +
			"ORDER BY UREGTIME LIMIT #{limit} OFFSET #{end}  </script>")
	public abstract List<Userinfo> getList(Integer limit, Integer end, String uname);

	@Select("<script> SELECT COUNT(*) FROM USERINFO WHERE UROLE='2'" +
			"<when test='uname!=null'> AND UNAME LIKE CONCAT ('%',#{uname},'%')</when> </script>")
	public abstract int getSum(String uname);
}