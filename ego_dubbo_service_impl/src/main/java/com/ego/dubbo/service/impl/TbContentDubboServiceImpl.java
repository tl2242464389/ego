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
import java.util.Map;

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

    @Override
    public List<TbContent> showBigPic(int size, boolean sort) {
        TbContentExample example = new TbContentExample();
        if(0 != size){
            PageHelper.startPage(1, size);
        }
        if(sort){
            example.setOrderByClause("updated desc");
        }
        List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(example);
        PageInfo<TbContent> info = new PageInfo<>(tbContents);
        System.out.println(info.getList());
        return info.getList();
    }

    @Override
    public int saveTbConent(TbContent tbContent) {
        return tbContentMapper.insertSelective(tbContent);
    }
}
