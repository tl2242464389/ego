package com.ego.service;

import com.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-07-30 11:16
 * @Version: 1.0
 */
public interface TbitemService {
    /**
     * @description: 分页显示商品信息
     * @param: page 第几页
     * @param: rows 显示多少行
     * @Date: 2019-07-30 11:17
     * @return: com.commons.pojo.EasyUIDataGrid
     */
    EasyUIDataGrid show(int page, int rows);

    /**
     * @description: 更新商品状态
     * @param: ids
     * @param: status
     * @Date: 2019-07-31 10:57
     * @return: int
     */
    int updItemStatus(String ids, byte status);
}
