package com.ego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.EasyUIDataGrid;
import com.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.pojo.TbItemParam;
import com.ego.service.TbItemParamService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 商品规格
 * @Author: tl
 * @Date: 2019-08-05 11:01
 * @Version: 1.0
 */
@Service
public class TbItemParamServiceImpl implements TbItemParamService {

    @Reference
    private TbItemParamDubboService tbItemParamDubboServiceImpl;

    @Override
    public EasyUIDataGrid show(int page, int rows) {
        return tbItemParamDubboServiceImpl.show(page, rows);
    }

    @Override
    public EgoResult delParamById(String ids) {
        String[] idStr = ids.split(",");
        List<Long> list = new ArrayList<>();
        for (String id : idStr) {
            list.add(Long.parseLong(id));
        }

        int index = 0;
        EgoResult er = new EgoResult();

        try {
            index = tbItemParamDubboServiceImpl.delParamById(list);
            if(1 == index){
                er.setStatus(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
            er.setData(e.getMessage());
        }

        return er;
    }

    @Override
    public EgoResult selByCatId(long catId) {
        TbItemParam tbItemParam = tbItemParamDubboServiceImpl.selByCatId(catId);
        EgoResult er = new EgoResult();
        if(null != tbItemParam){
            er.setStatus(200);
            er.setData(tbItemParam);
            System.out.println(" test--------------------- ");
            return er;
        }
        return er;
    }

    @Override
    public EgoResult saveTbItemParam(long catId, String paramData) {
        TbItemParam tbItemParam = new TbItemParam();
        Date date = new Date();
        EgoResult er = new EgoResult();

        tbItemParam.setItemCatId(catId);
        tbItemParam.setParamData(paramData);
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);

        int index = 0;
        try {
            index = tbItemParamDubboServiceImpl.saveTbItemParam(tbItemParam);
            if(1 == index){
                er.setStatus(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
            er.setData(e.getMessage());
        }
        return er;
    }
}
