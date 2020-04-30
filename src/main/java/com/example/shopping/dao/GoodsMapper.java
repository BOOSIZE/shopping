package com.example.shopping.dao;

import com.example.shopping.entity.Shopinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsMapper
{

	/**
	 * 查询所有的news总数
	 * @return
	 */
	@Select("select count(*) from journalism")
	public int queryNewsTotalNum();

	/**
	 * 查询单个
	 * @param jid
	 * @return
	 */
	public Shopinfo querySingleNews(int jid);

	/**
	 * @Description:
	 * @Param [sname]
	 * @return int
	 **/
	int queryNewsWithParamTotalNum(String sname);

	/**
	 * @Description: 查询客户购买列表
	 * @Param [sname, now]
	 * @return int
	 **/
	int queryBuyGoodsListNum(String sname,String now);

	/**
	 * @Description: 获取管理员端列表
	 * @Param [sname]
	 * @return java.util.List<com.example.shopping.entity.Shopinfo>
	 **/
	List<Shopinfo> queryNewsWithParam(String sname,int statrNum,int limit);


	/**
	 * @Description: 获取客户端列表
	 * @Param [sname, now, statrNum, limit]
	 * @return java.util.List<com.example.shopping.entity.Shopinfo>
	 **/
	List<Shopinfo> queryBuyGoodsList(String sname,String now,int statrNum,int limit);

	/**
	 * 更新状态
	 * @param map
	 * @return
	 */
	public int updateNewsStatue(Map<String, String> map);

	/**
	 * 新增商品
	 * @param
	 * @return
	 */
	int insertGoods(String sname,String smoney,String scount,String sstarttime,String sendtime);

	/**
	 * @Description: 删除商品
	 * @Param [id]
	 * @return int
	 **/
	int deleteGoods(String sid);


	/**
	 * @Description: 更新价格
	 * @Param [sid, price]
	 * @return int
	 **/
	int updatePrice(String sid,String smoney);
}
