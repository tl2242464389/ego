package com.ego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.EasyUIDataGrid;
import com.commons.utils.HttpClientUtil;
import com.commons.utils.IDUtils;
import com.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbitemDubboService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemExample;
import com.ego.pojo.TbItemParamItem;
import com.ego.service.TbitemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-07-30 11:18
 * @Version: 1.0
 */
@Service
public class TbitemServiceImpl implements TbitemService{

    @Reference
    private TbitemDubboService tbitemDubboService;
    @Value("${search.url}")
    private String url;

    @Override
    public EasyUIDataGrid show(int page, int rows) {
        return tbitemDubboService.show(page, rows);
    }

    @Override
    public int updItemStatus(String ids, byte status) {
        return tbitemDubboService.updItemStatus(ids, status);
    }

    @Override
    public int insTbItemAndDesc(TbItem tbItem, String desc) throws Exception {
        return insTbItemAndDesc(tbItem, desc, null);
    }

    @Override
    public int insTbItemAndDesc(TbItem tbItem, String desc, String itemParams) throws Exception {
        long id = IDUtils.genItemId();
        Date data = new Date();

        tbItem.setId(id);
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(data);
        tbItem.setUpdated(data);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(id);
        tbItemDesc.setCreated(data);
        tbItemDesc.setUpdated(data);
        tbItemDesc.setItemDesc(desc);

        int index = 0;

        if(null != itemParams){
            TbItemParamItem tbItemParamItem = new TbItemParamItem();
            tbItemParamItem.setItemId(id);
            tbItemParamItem.setParamData(itemParams);
            tbItemParamItem.setCreated(data);
            tbItemParamItem.setUpdated(data);
            index = tbitemDubboService.insItemAndDesc(tbItem, tbItemDesc, tbItemParamItem);
        }else{
            index = tbitemDubboService.insItemAndDesc(tbItem, tbItemDesc);
        }

        // 开始同步solr数据，通过子线程同步，提升效率
        final TbItem tbItemFinal = tbItem;
        final String descFinal = desc;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String,Object> map = new HashMap<>();
                map.put("tbItem", tbItemFinal);
                map.put("desc", descFinal);
                HttpClientUtil.doPostJson(url, JsonUtils.objectToJson(map));
            }
        }).start();

        if(index == 1){
            return 1;
        }
        return 0;
    }
}
