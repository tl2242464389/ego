package com.ego.dubbo.service;

import com.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;

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

    /**
     * @description: 更新商品状态
     * @param: ids
     * @param: status
     * @Date: 2019-07-31 11:29
     * @return: int
     */
    int updItemStatus(String ids, byte status);

    /**
     * @description: 新增商品，同时新增商品描述
     * @param: tbItem
     * @param: desc
     * @Date: 2019-08-03 10:55
     * @return: int
     */
    int insItemAndDesc(TbItem tbItem, TbItemDesc desc) throws Exception;
}
