package com.ego.dubbo.service.impl;

import com.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.mapper.TbContentMapper;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 商品内容管理
 * @Author: tl
 * @Date: 2019-08-11 11:51
 * @Version: 1.0
 */
public class TbContentDubboServiceImpl implements TbContentDubboService {
    @Resource
    private TbContentMapper tbContentMapper;

    @Override
    public EasyUIDataGrid show(long categoryId, int page, int rows) {
        PageHelper.startPage(page, rows);

        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);

        PageInfo<TbContent> info = new PageInfo<>(list);

        EasyUIDataGrid dataGrid = new EasyUIDataGrid();
        dataGrid.setRows(info.getList());
        dataGrid.setTotal(info.getTotal());

        return dataGrid;
    }

    @Override
    public int updTbContent(TbContent content) {
        return tbContentMapper.updateByPrimaryKeyWithBLOBs(content);
    }

    @Override
    public int delTbContent(List<Long> ids) {
        return tbContentMapper.delTbContent(ids);
    }
}
