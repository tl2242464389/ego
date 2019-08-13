package com.ego.service;

import com.commons.pojo.EasyUiTree;
import com.commons.pojo.EgoResult;
import com.ego.pojo.TbContentCategory;

import java.util.List;

/**
 * @Description: 商品分类管理
 * @Author: tl
 * @Date: 2019-08-10 11:06
 * @Version: 1.0
 */
public interface TbConCateService {

    /**
     * @description: 根据父节点ID查询所有子节点
     * @param: pid
     * @Date: 2019-08-10 11:07
     * @return: java.util.List<com.commons.pojo.EasyUiTree>
     */
    List<EasyUiTree> show(long pid);

    /**
     * @description: 新增商品分类
     * @param: category
     * @Date: 2019-08-10 11:35
     * @return: com.commons.pojo.EgoResult
     */
    EgoResult insTbConCate(TbContentCategory category);

    /**
     * @description: 修改当前新增节点的父节点isParent字段
     * @param: category
     * @Date: 2019-08-10 11:36
     * @return: int
     */
    int updTbConCateParent(TbContentCategory category);

    /**
     * @description: 根据ID重命名当前节点
     * @param: id
     * @param: name
     * @Date: 2019-08-11 11:09
     * @return: com.commons.pojo.EgoResult
     */
    EgoResult updName(long id, String name);

    /**
     * @description: 根据ID删除当前节点
     * @param: id
     * @Date: 2019-08-11 11:21
     * @return: com.commons.pojo.EgoResult
     */
    EgoResult delById(long id);
}
