package com.ego.dubbo.service.impl;

import com.ego.dubbo.mapper.TbContentCategoryMapper;
import com.ego.dubbo.service.TbConCateDubboService;
import com.ego.pojo.TbContentCategory;
import com.ego.pojo.TbContentCategoryExample;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 商品分类管理
 * @Author: tl
 * @Date: 2019-08-10 11:01
 * @Version: 1.0
 */
public class TbConCateDubboServiceImpl implements TbConCateDubboService {
    @Resource
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TbContentCategory> show(long id) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(id).andStatusEqualTo(1);
        return tbContentCategoryMapper.selectByExample(example);
    }

    @Override
    public int insTbConCate(TbContentCategory category) {
        return tbContentCategoryMapper.insertSelective(category);
    }

    @Override
    public int updTbConCateParent(TbContentCategory category) {
        return tbContentCategoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public TbContentCategory selById(long id) {
        return tbContentCategoryMapper.selectByPrimaryKey(id);
    }
}
