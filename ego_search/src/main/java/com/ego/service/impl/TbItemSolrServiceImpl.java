package com.ego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbitemDubboService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemSearch;
import com.ego.service.TbItemSolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-28 10:01
 * @Version: 1.0
 */
@Service
public class TbItemSolrServiceImpl implements TbItemSolrService {
    @Resource
    private CloudSolrClient client;
    @Reference
    private TbitemDubboService tbitemDubboServiceImpl;
    @Reference
    private TbItemCatDubboService tbItemCatDubboServiceImpl;

    @Override
    public void init() throws IOException, SolrServerException {
        List<TbItem> tbItems = tbitemDubboServiceImpl.selAll((byte) 1);
        List<SolrInputDocument> docList = new ArrayList<>();

        if(null == tbItems || 0 == tbItems.size()){
            return ;
        }
        for (TbItem tbItem : tbItems) {
            TbItemCat tbItemCat = tbItemCatDubboServiceImpl.selById(tbItem.getCid());
            TbItemDesc tbItemDesc = tbitemDubboServiceImpl.selById(tbItem.getId());

            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", tbItem.getId());
            document.addField("item_title", tbItem.getTitle());
            document.addField("item_sell_point", tbItem.getSellPoint());
            document.addField("item_price",tbItem.getPrice() );
            document.addField("item_image", tbItem.getImage());
            if(null != tbItemCat){
                document.addField("item_category_name", tbItemCat.getName());
            }
            if(null != tbItemDesc){
                document.addField("item_desc", tbItemDesc.getItemDesc());
            }

            docList.add(document);
        }

        for (SolrInputDocument document : docList) {
            client.add(document);
        }
        client.commit();
    }

    @Override
    public Map<String, Object> selByQuery(String query, int page, int rows) throws IOException, SolrServerException {
        if(null == query){
            return null;
        }
        SolrQuery solrQuery = new SolrQuery();
        // 设置查询query
        solrQuery.setQuery("item_keywords:" + query);
        // 设置分页
        solrQuery.setStart(rows*(page-1));
        solrQuery.setRows(rows);
        // 设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<span style='color:blue;'>");
        solrQuery.setHighlightSimplePost("</span>");

        System.out.println(" 111111111111111111111111111111 ");

        QueryResponse response = client.query(solrQuery);
        SolrDocumentList docs = response.getResults();
        long total = docs.getNumFound();
        Map<String, Map<String, List<String>>> hh = response.getHighlighting();
        List<TbItemSearch> list = new ArrayList<>();

        System.out.println(" 222222222222222222222222222222 ");

        for (SolrDocument doc : docs) {
            TbItemSearch tbItem = new TbItemSearch();

            tbItem.setId(Long.parseLong(doc.getFieldValue("id").toString()));

            List<String> hhList = hh.get(doc.getFieldValue("id")).get("item_title");
            if(null == hhList || 0 == hhList.size()){
                // 未高亮
                tbItem.setTitle(doc.getFieldValue("item_title").toString());
            }else{
                // 高亮
                tbItem.setTitle(hhList.get(0));
            }

            tbItem.setSellPoint(doc.getFieldValue("item_sell_point").toString());
            tbItem.setPrice(Long.parseLong(doc.getFieldValue("item_price").toString()));
            String image = doc.getFieldValue("item_image").toString();
            tbItem.setImages(null == image || "".equals(image) ? new String[1] : image.split(","));

            list.add(tbItem);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("itemList", list);
        map.put("totalPages", 0 == total%rows ? total/rows : total/rows + 1);
        return map;
    }

    @Override
    public int insTbItem(TbItem tbItem, String desc) throws IOException, SolrServerException {
        SolrInputDocument doc = new SolrInputDocument();

        doc.addField("id", tbItem.getId());
        doc.addField("item_title", tbItem.getTitle());
        doc.addField("item_sell_point", tbItem.getSellPoint());
        doc.addField("item_price",tbItem.getPrice() );
        doc.addField("item_image", tbItem.getImage());
        doc.addField("item_desc", desc);

        client.add(doc);
        UpdateResponse response = client.commit();
        // 新增成功返回0
        if(0 == response.getStatus()){
            return 1;
        }
        return 0;
    }
}
