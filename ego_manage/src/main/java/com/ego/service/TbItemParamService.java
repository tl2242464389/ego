package com.ego.service;

import com.commons.pojo.EasyUIDataGrid;
import com.commons.pojo.EgoResult;

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

    /**
     * @description: 根据商品ID删除商品规格明细
     * @param: ids
     * @Date: 2019-08-07 10:58
     * @return: com.commons.pojo.EgoResult
     */
    EgoResult delParamById(String ids);

    /**
     * @description: 根据商品类目ID查询
     * @param: catId
     * @Date: 2019-08-07 11:26
     * @return: com.commons.pojo.EgoResult
     */
    EgoResult selByCatId(long catId);

    /**
     * @description: 新增商品类目规格
     * @param: catId
     * @param: paramData
     * @Date: 2019-08-07 15:48
     * @return: com.commons.pojo.EgoResult
     */
    EgoResult saveTbItemParam(long catId, String paramData);
}
