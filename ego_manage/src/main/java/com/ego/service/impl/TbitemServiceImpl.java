package com.ego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbitemDubboService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemExample;
import com.ego.service.TbitemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
    private TbitemDubboService tbitemDubboServiceImpl;

    @Override
    public EasyUIDataGrid show(int page, int rows) {
        return tbitemDubboServiceImpl.show(page, rows);
    }

    @Override
    public int updItemStatus(String ids, byte status) {
        return tbitemDubboServiceImpl.updItemStatus(ids, status);
    }
}
