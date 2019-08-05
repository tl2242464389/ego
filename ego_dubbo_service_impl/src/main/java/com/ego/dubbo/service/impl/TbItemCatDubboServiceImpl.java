package com.ego.dubbo.service.impl;

import com.ego.dubbo.mapper.TbItemCatMapper;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemCatExample;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 查询当前节点下的所有子节点
 * @Author: tl
 * @Date: 2019-08-03 11:32
 * @Version: 1.0
 */
public class TbItemCatDubboServiceImpl implements TbItemCatDubboService {

    @Resource
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCat> show(long pid) {
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(pid);
        return tbItemCatMapper.selectByExample(example);
    }

    @Override
    public TbItemCat selById(long id) {
        return tbItemCatMapper.selectByPrimaryKey(id);
    }
}
