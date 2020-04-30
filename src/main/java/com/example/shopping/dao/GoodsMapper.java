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
	public int queryNewsWithParamTotalNum(String sname);

	/**
	 * @Description:
	 * @Param [sname]
	 * @return java.util.List<com.example.shopping.entity.Shopinfo>
	 **/
	public List<Shopinfo> queryNewsWithParam(String sname,int statrNum,int limit);


	/**
	 * 更新状态
	 * @param map
	 * @return
	 */
	public int updateNewsStatue(Map<String, String> map);

	/**
	 * 更新文章内容
	 * @param map
	 * @return
	 */
	public int updateNewsContent(Map<String, String> map);

	/**
	 * 新增文章
	 * @param map
	 * @return
	 */
	public int insertNewsContent(Map<String, String> map);
}
