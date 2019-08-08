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

    /**
     * @description: 新增商品，同时新增商品描述
     * @param: tbItem
     * @param: desc
     * @Date: 2019-08-03 11:02
     * @return: int
     */
    int insTbItemAndDesc(TbItem tbItem, String desc) throws Exception;

    /**
     * @description: 新增商品，同时新增商品描述、商品规格
     * @param: tbItem
     * @param: desc
     * @param: itemParams
     * @Date: 2019-08-07 16:12
     * @return: int
     */
    int insTbItemAndDesc(TbItem tbItem, String desc, String itemParams) throws Exception;
}
