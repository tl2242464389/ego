package com.ego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.EasyUIDataGrid;
import com.commons.pojo.EgoResult;
import com.commons.utils.JsonUtils;
import com.ego.dao.JedisDao;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.pojo.TbContent;
import com.ego.service.TbContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-11 11:59
 * @Version: 1.0
 */
@Service
public class TbContentServiceImpl implements TbContentService {
    @Reference
    private TbContentDubboService tbContentDubboServiceImpl;
    @Resource
    private JedisDao jedisDaoImpl;
    @Value("${redis.bigPic.key}")
    private String key;

    @Override
    public EasyUIDataGrid show(long categoryId, int page, int rows) {
        return tbContentDubboServiceImpl.show(categoryId, page, rows);
    }

    @Override
    public EgoResult updTbContent(TbContent content) {
        EgoResult er = new EgoResult();
        Date date = new Date();
        content.setCreated(date);
        content.setUpdated(date);
        int index = tbContentDubboServiceImpl.updTbContent(content);

        if(1 == index){
            er.setStatus(200);
        }
        return er;
    }

    @Override
    public EgoResult delTbContent(String ids) {
        EgoResult er = new EgoResult();

        if(null == ids || "".equals(ids)){
            er.setData("删除商品内容时不允许传入的ID为空");
            return er;
        }

        List<Long> longIds = new ArrayList<>();
        String[] strIds = ids.split(",");
        for (String id : strIds) {
            System.out.println(id);
            longIds.add(Long.parseLong(id));
        }
        int index = tbContentDubboServiceImpl.delTbContent(longIds);
        System.err.println(index);
        if(index > 0){
            er.setStatus(200);
        }
        return er;
    }

    @Override
    public EgoResult saveTbContent(TbContent tbContent) {
        EgoResult er = new EgoResult();
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        int index = tbContentDubboServiceImpl.saveTbConent(tbContent);
        if(1 == index){
            er.setStatus(200);
            if(jedisDaoImpl.exists(key)){
                String bigPic = jedisDaoImpl.get(key);
                List<Map> maps = JsonUtils.jsonToList(bigPic, Map.class);
                if(6 == maps.size()){
                    maps.remove(maps.size()-1);
                }
                Map<String,Object> map = new HashMap<>();
                map.put("srcB", tbContent.getPic2());
                map.put("height", 240);
                map.put("alt", null);
                map.put("width", 670);
                map.put("src", tbContent.getPic());
                map.put("widthB", 550);
                map.put("href", "www.baidu.com");
                map.put("heightB", 240);

                maps.add(0, map);

                jedisDaoImpl.set(key, JsonUtils.objectToJson(maps));
            }
        }
        return er;
    }
}
