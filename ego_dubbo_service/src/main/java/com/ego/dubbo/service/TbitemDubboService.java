package com.ego.dubbo.service;

import com.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;

import java.util.List;

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

    /**
     * @description: 新增商品，同时新增商品描述、商品规格
     * @param: tbItem
     * @param: desc
     * @param: itemParams
     * @Date: 2019-08-07 16:09
     * @return: int
     */
    int insItemAndDesc(TbItem tbItem, TbItemDesc desc, TbItemParamItem paramItem) throws Exception;

    /**
     * @description: 根据状态查询商品信息
     * @param: status
     * @Date: 2019-08-28 9:44
     * @return: java.util.List<com.ego.pojo.TbItem>
     */
    List<TbItem> selAll(byte status);

    /**
     * @description: 通过ID查询商品描述
     * @param: id
     * @Date: 2019-08-28 9:50
     * @return: com.ego.pojo.TbItemDesc
     */
    TbItemDesc selById(long id);
}
