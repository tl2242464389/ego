package com.ego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.EasyUIDataGrid;
import com.commons.utils.IDUtils;
import com.ego.dubbo.service.TbitemDubboService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemExample;
import com.ego.service.TbitemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        int index = tbitemDubboService.insItemAndDesc(tbItem, tbItemDesc);
        if(index == 1){
            return 1;
        }
        return 0;
    }
}
