package com.ego.service;

import com.ego.pojo.TbItem;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.Map;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-28 9:53
 * @Version: 1.0
 */
public interface TbItemSolrService {
    /**
     * @description: solr数据源初始化
     * @Date: 2019-08-28 9:53
     * @return: void
     */
    void init() throws IOException, SolrServerException;

    /**
     * @description: 条件分页查询
     * @param: query
     * @param: page
     * @param: rows
     * @Date: 2019-08-31 10:12
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String,Object> selByQuery(String query, int page, int rows) throws IOException, SolrServerException;

    /**
     * @description: solr同步新增商品
     * @param: tbItem
     * @param: desc
     * @Date: 2019-09-01 11:12
     * @return: int
     */
    int insTbItem(TbItem tbItem, String desc) throws IOException, SolrServerException;
}
