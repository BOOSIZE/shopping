package com.example.shopping.service;

import com.example.shopping.entity.Shopinfo;
import com.example.shopping.entity.TableModel;

import java.util.List;
import java.util.Map;

public interface GoodsService
{
	
	/**
	 * @Description:获取列表
	 * @Param [sname]
	 * @return com.example.shopping.entity.TableModel
	 **/
	String getList(String sname,Integer page,Integer limit);

	/**
	 * 新增商品信息
	 * @param
	 * @return
	 */
	public String insertGoods(String sname,String smoney,String scount,String sstarttime,String sendtime);

	/**
	 * 前端展示4条新闻资讯
	 * @param type
	 * @return
	 */
	public List<Shopinfo> queryAllNews(String type);

	/**
	 * 查询显示前端网页
	 * @param map
	 * @return
	 */
	public TableModel queryAllNewsWithLimit(Map<String, String> map);

	/**
	 * 查询单个新闻对象
	 * @param jid
	 * @return
	 */
	public Shopinfo querySingleNews(int jid);

	/**
	 * 带条件查询新闻资讯
	 * @param map
	 * @return
	 */
	public TableModel queryNewsWithParam(Map<String, String> map);

	/**
	 * 修改文章状态
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


}
