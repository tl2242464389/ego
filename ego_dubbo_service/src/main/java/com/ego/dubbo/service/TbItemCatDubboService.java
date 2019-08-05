package com.ego.dubbo.service;

import com.ego.pojo.TbItemCat;

import java.util.List;

/**
 * @Description: 商品类目树
 * @Author: tl
 * @Date: 2019-08-03 11:26
 * @Version: 1.0
 */
public interface TbItemCatDubboService {

    /**
     * @description: 显示商品类目树
     * @param: pid
     * @Date: 2019-08-03 11:29
     * @return: java.util.List<com.ego.pojo.TbItemCat>
     */
    List<TbItemCat> show(long pid);

    /**
     * @description: 根据主键查询商品类目
     * @param: id
     * @Date: 2019-08-05 10:51
     * @return: com.ego.pojo.TbItemCat
     */
    TbItemCat selById(long id);
}
