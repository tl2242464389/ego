package com.ego.dubbo.service;

import com.commons.pojo.EasyUIDataGrid;

/**
 * @Description: 商品服务接口
 * @Author: tl
 * @Date: 2019-07-30 10:43
 * @Version: 1.0
 */
public interface TbitemDubboService {
    /**
     * @description: 返回当前页所有商品资料
     * @param: page
     * @param: rows
     * @Date: 2019-07-30 10:44
     * @return: com.commons.pojo.EasyUIDataGrid
     */
    EasyUIDataGrid show(int page, int rows);
}
