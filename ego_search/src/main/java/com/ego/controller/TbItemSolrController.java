package com.ego.controller;

import com.commons.utils.JsonUtils;
import com.ego.pojo.TbItem;
import com.ego.service.TbItemSolrService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-28 10:19
 * @Version: 1.0
 */
@Controller
public class TbItemSolrController {
    @Resource
    private TbItemSolrService tbItemSolrServiceImpl;

    @ResponseBody
    @RequestMapping(value = "/init", produces = "text/html;charset=utf-8")
    public String init(){
        long start = System.currentTimeMillis();
        try {
            tbItemSolrServiceImpl.init();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        return "耗时：" + (end-start) + "秒";
    }

    @RequestMapping("/search.html")
    public String search(String q, @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "20") int rows, Model model){
        try {
            // 字符编码过滤器只能解决post请求中的中文乱码，get方式的乱码只能转码
            q = new String(q.getBytes("ISO8859-1"), "utf-8");
            Map<String, Object> map = tbItemSolrServiceImpl.selByQuery(q, page, rows);
            model.addAttribute("query", q);
            model.addAttribute("itemList", map.get("itemList"));
            model.addAttribute("totalPages", map.get("totalPages"));
            model.addAttribute("page", page);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return "search";
    }

    @ResponseBody
    @RequestMapping("solr/add")
    public int solrAdd(@RequestBody Map<String,Object> map){
        String str = JsonUtils.objectToJson(map.get("tbItem"));
        System.out.println(str);
        TbItem tbItem = JsonUtils.jsonToPojo(str, TbItem.class);
        System.out.println(tbItem);
        try {
            return tbItemSolrServiceImpl.insTbItem(tbItem, map.get("desc").toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
