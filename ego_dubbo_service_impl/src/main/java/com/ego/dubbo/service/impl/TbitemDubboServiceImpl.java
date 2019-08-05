package com.ego.dubbo.service.impl;

import com.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.mapper.TbItemDescMapper;
import com.ego.dubbo.mapper.TbItemMapper;
import com.ego.dubbo.service.TbitemDubboService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-07-30 10:45
 * @Version: 1.0
 */
public class TbitemDubboServiceImpl implements TbitemDubboService {
    @Resource
    private TbItemMapper tbItemMapper;
    @Resource
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public EasyUIDataGrid show(int page, int rows) {
        // 设置分页条件，page -- 第几页，rows -- 多少条数据
        PageHelper.startPage(page, rows);

        List<TbItem> tbItems = tbItemMapper.selectByExample(new TbItemExample());

        // 使用分页插件将返回的集合解析成 EasyUI 能够解析的 EasyUIDataGrid 对象
        PageInfo pageInfo = new PageInfo(tbItems);

        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setRows(pageInfo.getList());
        easyUIDataGrid.setTotal(pageInfo.getTotal());

        return easyUIDataGrid;
    }

    @Override
    public int updItemStatus(String ids, byte status) {
        String[] idStrs = ids.split(",");
        List<Long> list = new ArrayList<>();
        for (String id : idStrs){
            list.add(Long.parseLong(id));
        }
        TbItem tbItem = new TbItem();
        tbItem.setStatus(status);

        TbItemExample tbItemExample = new TbItemExample();
        tbItemExample.createCriteria().andIdIn(list);

        int index = tbItemMapper.updateByExampleSelective(tbItem, tbItemExample);
        System.out.println(index);
        return index;
    }

    @Override
    public int insItemAndDesc(TbItem tbItem, TbItemDesc desc) throws Exception {
        int index =0;
        try {
            index += tbItemMapper.insert(tbItem);
            index += tbItemDescMapper.insert(desc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(index == 2){
            return 1;
        }else{
            throw new Exception("商品新增失败，请联系管理员");
        }
    }

}
