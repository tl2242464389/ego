package com.ego.service;

import com.commons.pojo.EasyUiTree;
import com.ego.pojo.TbItemCat;

import java.util.List;

/**
 * @Description: 商品类目树
 * @Author: tl
 * @Date: 2019-08-03 11:36
 * @Version: 1.0
 */
public interface TbItemCatService {

    /**
     * @description: 显示商品类目树
     * @param: pid
     * @Date: 2019-08-03 11:37
     * @return: java.util.List<com.ego.pojo.EasyUiTree>
     */
    List<EasyUiTree> show(long pid);
}
