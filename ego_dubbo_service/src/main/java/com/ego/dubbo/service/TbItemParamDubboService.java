package com.ego.dubbo.service;

import com.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItemParam;

import java.util.List;

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

    /**
     * @description: 根据商品ID删除类目明细
     * @param: List<Long>
     * @Date: 2019-08-07 10:49
     * @return: int
     */
    int delParamById(List<Long> ids) throws Exception;

    /**
     * @description: 根据商品类目ID查询
     * @param: catId
     * @Date: 2019-08-07 11:22
     * @return: com.ego.pojo.TbItemParam
     */
    TbItemParam selByCatId(long catId);

    /**
     * @description: 新增商品类目
     * @param: tbItemParam
     * @Date: 2019-08-07 15:41
     * @return: int
     */
    int saveTbItemParam(TbItemParam tbItemParam) throws Exception;
}
