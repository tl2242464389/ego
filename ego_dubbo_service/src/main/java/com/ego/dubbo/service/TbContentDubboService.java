package com.ego.dubbo.service;

import com.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbContent;

import java.util.List;
import java.util.Map;


/**
 * @Description: 商品内容管理
 * @Author: tl
 * @Date: 2019-08-11 11:48
 * @Version: 1.0
 */
public interface TbContentDubboService {

    /**
     * @description: 商品内容分页查询
     * @param: categoryId
     * @param: page
     * @param: rows
     * @Date: 2019-08-11 11:51
     * @return: com.commons.pojo.EasyUIDataGrid
     */
    EasyUIDataGrid show(long categoryId, int page, int rows);

    /**
     * @description: 更新当前商品内容数据
     * @param: content
     * @Date: 2019-08-13 9:23
     * @return: int
     */
    int updTbContent(TbContent content);

    /**
     * @description: 删除商品内容
     * @param: ids
     * @Date: 2019-08-13 9:38
     * @return: int
     */
    int delTbContent(List<Long> ids);

    /**
     * @description: 返回前台大广告
     * @param: size
     * @param: sort
     * @Date: 2019-08-17 10:25
     * @return: java.util.List<com.ego.pojo.TbContent>
     */
    List<TbContent> showBigPic(int size, boolean sort);

    /**
     * @description: 保存商品内容
     * @param: tbContent
     * @Date: 2019-08-18 10:21
     * @return: int
     */
    int saveTbConent(TbContent tbContent);
}
