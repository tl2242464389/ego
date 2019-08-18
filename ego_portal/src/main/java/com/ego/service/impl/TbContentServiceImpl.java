package com.ego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.utils.JsonUtils;
import com.ego.dao.JedisDao;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.pojo.TbContent;
import com.ego.service.TbContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-17 10:37
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
    public String showBigPic() {
        if(jedisDaoImpl.exists(key)){
            return jedisDaoImpl.get(key);
        }
        List<TbContent> list = tbContentDubboServiceImpl.showBigPic(6, true);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (TbContent tbContent : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("srcB", tbContent.getPic2());
            map.put("height", 240);
            map.put("alt", null);
            map.put("width", 670);
            map.put("src", tbContent.getPic());
            map.put("widthB", 550);
            map.put("href", "www.baidu.com");
            map.put("heightB", 240);
            mapList.add(map);
        }
        String bigPic = JsonUtils.objectToJson(mapList);
        System.out.println(bigPic);
        jedisDaoImpl.set(key, bigPic);
        return bigPic;
    }
}
