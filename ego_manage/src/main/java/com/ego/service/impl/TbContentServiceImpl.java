package com.ego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.EasyUIDataGrid;
import com.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.pojo.TbContent;
import com.ego.service.TbContentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
