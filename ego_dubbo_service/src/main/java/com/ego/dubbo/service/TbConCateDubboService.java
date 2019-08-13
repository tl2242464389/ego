package com.ego.dubbo.service;

import com.commons.pojo.EasyUiTree;
import com.ego.pojo.TbContentCategory;

import java.util.List;

/**
 * @Description: 商品分类管理
 * @Author: tl
 * @Date: 2019-08-10 10:59
 * @Version: 1.0
 */
public interface TbConCateDubboService {
    /**
     * @description: 显示商品分类树
     * @param: id
     * @Date: 2019-08-10 11:05
     * @return: java.util.List<com.ego.pojo.TbContentCategory>
     */
    List<TbContentCategory> show(long id);

    /**
     * @description: 新增商品分类
     * @param: category
     * @Date: 2019-08-10 11:27
     * @return: int
     */
    int insTbConCate(TbContentCategory category);

    /**
     * @description: 更新父节点的isParent为1
     * @param: category
     * @Date: 2019-08-10 11:30
     * @return: int
     */
    int updTbConCateParent(TbContentCategory category);

    /**
     * @description: 根据ID查询当前节点的所有信息
     * @param: id
     * @Date: 2019-08-11 11:00
     * @return: com.ego.pojo.TbContentCategory
     */
    TbContentCategory selById(long id);
}
