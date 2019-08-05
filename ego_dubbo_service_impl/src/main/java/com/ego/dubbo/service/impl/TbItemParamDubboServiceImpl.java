package com.ego.dubbo.service.impl;

import com.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.mapper.TbItemCatMapper;
import com.ego.dubbo.mapper.TbItemParamMapper;
import com.ego.dubbo.pojo.TbItemParamChild;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 商品规格
 * @Author: tl
 * @Date: 2019-08-05 10:42
 * @Version: 1.0
 */
public class TbItemParamDubboServiceImpl implements TbItemParamDubboService {
    @Resource
    private TbItemParamMapper tbItemParamMapper;
    @Resource
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public EasyUIDataGrid show(int page, int rows) {
        PageHelper.startPage(page, rows);

        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(
                new TbItemParamExample());
        List<TbItemParamChild> listChild = new ArrayList<>();
        EasyUIDataGrid er = new EasyUIDataGrid();
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        er.setTotal(pageInfo.getTotal());

        // 逆向工程不支持多表查询，只能通过子类封装多表数据
        System.err.println("----------------------------");
        for (TbItemParam param : list){
            TbItemParamChild child = new TbItemParamChild();
            child.setId(param.getId());
            child.setItemCatId(param.getItemCatId());
            child.setCreated(param.getCreated());
            child.setUpdated(param.getUpdated());
            child.setParamData(param.getParamData());
            child.setItemCatName(tbItemCatMapper.selectByPrimaryKey(param.getId()).getName());
            listChild.add(child);
        }

        er.setRows(listChild);
        return er;
    }
}
