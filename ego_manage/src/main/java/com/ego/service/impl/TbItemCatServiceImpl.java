package com.ego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.EasyUiTree;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.pojo.TbItemCat;
import com.ego.service.TbItemCatService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-03 11:37
 * @Version: 1.0
 */
@Service
public class TbItemCatServiceImpl implements TbItemCatService {

    @Reference
    private TbItemCatDubboService tbItemCatDubboServiceImpl;

    @Override
    public List<EasyUiTree> show(long pid) {
        List<TbItemCat> list = tbItemCatDubboServiceImpl.show(pid);
        List<EasyUiTree> easyUiTreeList = new ArrayList<>();
        for(TbItemCat tbItemCat : list){
            EasyUiTree tree = new EasyUiTree();
            tree.setId(tbItemCat.getId());
            tree.setText(tbItemCat.getName());
            tree.setState(tbItemCat.getIsParent() == true ? "closed" : "open");
            easyUiTreeList.add(tree);
        }
        return easyUiTreeList;
    }
}
