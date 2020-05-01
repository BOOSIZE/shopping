package com.example.shopping.dao;


import com.example.shopping.entity.Orderinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    @Insert("inser into orderinfo(sid,oname,ocount,omoney,otime,uaccount) values (#{sid},#{oname},#{ocount},#{omoney},#{otime},#{uaccount})")
    int addOrder(Orderinfo orderinfo);
}
