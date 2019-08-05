package com.ego.dubbo.service;

import com.commons.pojo.EasyUIDataGrid;

/**
 * @Description: 商品规格
 * @Author: tl
 * @Date: 2019-08-05 10:40
 * @Version: 1.0
 */
public interface TbItemParamDubboService {
    /**
     * @description: 查询商品规格表
     * @param: page
     * @param: rows
     * @Date: 2019-08-05 10:41
     * @return: com.commons.pojo.EasyUIDataGrid
     */
    EasyUIDataGrid show(int page, int rows);
}
