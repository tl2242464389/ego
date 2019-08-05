package com.ego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.service.TbItemParamService;
import org.springframework.stereotype.Service;

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
}
