package com.ego.service;

import com.commons.pojo.EasyUIDataGrid;
import com.commons.pojo.EgoResult;
import com.ego.pojo.TbContent;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-11 11:58
 * @Version: 1.0
 */
public interface TbContentService {

    /**
     * @description: 根据categoryId分页显示商品内容
     * @param: categoryId
     * @param: page
     * @param: rows
     * @Date: 2019-08-11 11:59
     * @return: com.commons.pojo.EasyUIDataGrid
     */
    EasyUIDataGrid show(long categoryId, int page, int rows);

    /**
     * @description: 更新当前商品内容数据
     * @param: content
     * @Date: 2019-08-13 9:26
     * @return: com.commons.pojo.EgoResult
     */
    EgoResult updTbContent(TbContent content);

    /**
     * @description: 批量删除商品内容
     * @param: ids
     * @Date: 2019-08-13 9:49
     * @return: com.commons.pojo.EgoResult
     */
    EgoResult delTbContent(String ids);
}
