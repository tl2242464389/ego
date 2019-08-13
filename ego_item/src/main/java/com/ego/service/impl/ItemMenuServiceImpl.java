package com.ego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.pojo.ItemMenu;
import com.ego.pojo.ItemMenuNode;
import com.ego.pojo.TbItemCat;
import com.ego.service.ItemMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 商品类目
 * @Author: tl
 * @Date: 2019-08-09 11:11
 * @Version: 1.0
 */
@Service
public class ItemMenuServiceImpl implements ItemMenuService {

    @Reference
    private TbItemCatDubboService tbItemCatDubboServiceImpl;

    @Override
    public ItemMenu show() {
        List<TbItemCat> list = tbItemCatDubboServiceImpl.show(0);
        ItemMenu itemMenu = new ItemMenu();
        itemMenu.setData(getAllMenu(list));
        return itemMenu;
    }

    public List<Object> getAllMenu(List<TbItemCat> list){
        // 从根节点开始，遍历每层节点并设置返回的节点格式
        List<Object> listMenu = new ArrayList<>();
        for (TbItemCat tbItemCat : list) {
            if(tbItemCat.getIsParent()){
                ItemMenuNode itemMenuNode = new ItemMenuNode();
                itemMenuNode.setU("/products/" + tbItemCat.getId() + ".html");
                itemMenuNode.setN("<a href='/products/" + tbItemCat.getId() + ".html'>"
                        + tbItemCat.getName() + "</a>");
                itemMenuNode.setI(getAllMenu(tbItemCatDubboServiceImpl.show(tbItemCat.getId())));
                listMenu.add(itemMenuNode);
            }else{
                listMenu.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
            }
        }
        return listMenu;
    }
}
