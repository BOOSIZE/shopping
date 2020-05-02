package com.example.shopping.dao;


import com.example.shopping.entity.Orderinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("insert into orderinfo(sid,oname,ocount,omoney,otime,uaccount) values (#{sid},#{oname},#{ocount},#{omoney},#{otime},#{uaccount})")
    int addOrder(Orderinfo orderinfo);

    @Select("<script> SELECT COUNT(*) FROM orderinfo WHERE 1=1" +
            "<when test='ostatus!=null'> AND ostatus =#{ostatus}</when> " +
            "<when test='uaccount!=null'> AND uaccount =#{uaccount}</when></script>")
    int getSum(String ostatus, String uaccount);

    @Select("<script> SELECT * FROM orderinfo WHERE 1=1 " +
            "<when test='ostatus!=null'> AND ostatus = #{ostatus}</when>" +
            "<when test='uaccount!=null'> AND uaccount =#{uaccount}</when>"+
            "ORDER BY otime desc LIMIT #{limit} OFFSET #{end}  </script>")
    List<Orderinfo> getList(Integer limit, int end, String ostatus, String uaccount);

    @Update("update orderinfo set ostatus = #{ostatus} where oid = #{oid}")
    int changestatus(Orderinfo orderinfo);

    @Update("update orderinfo set oevaluation = #{oevaluation},oevtime = #{oevtime},ostatus = #{ostatus} where oid = #{oid}")
    int changeOrder(Orderinfo orderinfo);
}
