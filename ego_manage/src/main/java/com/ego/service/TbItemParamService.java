package com.ego.service;

import com.commons.pojo.EasyUIDataGrid;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-05 11:00
 * @Version: 1.0
 */
public interface TbItemParamService {
    /**
     * @description: 查询商品规格信息
     * @param: page
     * @param: rows
     * @Date: 2019-08-05 11:00
     * @return: com.commons.pojo.EasyUIDataGrid
     */
    EasyUIDataGrid show(int page, int rows);
}
