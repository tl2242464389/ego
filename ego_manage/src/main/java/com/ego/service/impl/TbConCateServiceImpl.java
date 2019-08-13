package com.ego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.EasyUiTree;
import com.commons.pojo.EgoResult;
import com.commons.utils.IDUtils;
import com.ego.dubbo.service.TbConCateDubboService;
import com.ego.pojo.TbContentCategory;
import com.ego.service.TbConCateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 商品分类管理
 * @Author: tl
 * @Date: 2019-08-10 11:07
 * @Version: 1.0
 */
@Service
public class TbConCateServiceImpl implements TbConCateService {
    @Reference
    private TbConCateDubboService tbConCateDubboServiceImpl;

    @Override
    public List<EasyUiTree> show(long pid) {
        List<EasyUiTree> listTree = new ArrayList<EasyUiTree>();
        List<TbContentCategory> list = tbConCateDubboServiceImpl.show(pid);
        for (TbContentCategory category : list) {
            EasyUiTree tree = new EasyUiTree();
            tree.setId(category.getId());
            tree.setText(category.getName());
            tree.setState(category.getIsParent() ? "closed" : "open");
            listTree.add(tree);
        }
        return listTree;
    }

    @Override
    public EgoResult insTbConCate(TbContentCategory category) {
        EgoResult er = new EgoResult();
        List<TbContentCategory> list = tbConCateDubboServiceImpl.show(category.getParentId());
        for (TbContentCategory tbContentCategory : list) {
            if(tbContentCategory.getName().equals(category.getName())){
                er.setData("当前节点名称存在重复，新增失败");
                return er;
            }
        }
        Date date = new Date();
        long id = IDUtils.genItemId();
        category.setId(id);
        category.setCreated(date);
        category.setUpdated(date);
        category.setIsParent(false);
        category.setStatus(1);
        category.setSortOrder(1);
        int index = tbConCateDubboServiceImpl.insTbConCate(category);
        if(1 == index){
            TbContentCategory category_parent = new TbContentCategory();
            category_parent.setId(category.getParentId());
            category_parent.setIsParent(true);
            this.updTbConCateParent(category_parent);
        }
        er.setStatus(200);
        // 返回当前节点创建成功后的ID
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        er.setData(map);
        return er;
    }

    @Override
    public int updTbConCateParent(TbContentCategory category) {
        return tbConCateDubboServiceImpl.updTbConCateParent(category);
    }

    @Override
    public EgoResult updName(long id, String name) {
        EgoResult er = new EgoResult();
        Date date = new Date();
        TbContentCategory category = tbConCateDubboServiceImpl.selById(id);
        if(null != category){
            List<TbContentCategory> list = tbConCateDubboServiceImpl.show(category.getParentId());
            for (TbContentCategory contentCategory : list) {
                if(name.equals(contentCategory.getName())){
                    er.setData("重命名失败，当前名称已经存在对应节点");
                    return er;
                }
            }
            // 根据ID重命名
            TbContentCategory category_newName = new TbContentCategory();
            category_newName.setId(id);
            category_newName.setName(name);
            category_newName.setUpdated(date);
            int index = tbConCateDubboServiceImpl.updTbConCateParent(category_newName);
            if(1 == index){
                er.setStatus(200);
            }
        }
        return er;
    }

    @Override
    public EgoResult delById(long id) {
        Date date = new Date();
        EgoResult er = new EgoResult();
        TbContentCategory category = new TbContentCategory();
        category.setId(id);
        category.setStatus(0);
        category.setUpdated(date);
        TbContentCategory parent = tbConCateDubboServiceImpl.selById(id);
        Long parentId = -1L;
        if(null != parent){
            parentId = parent.getParentId();
        }
        int index = tbConCateDubboServiceImpl.updTbConCateParent(category);
        if(1 == index){
            if(-1L == parentId){
                er.setData("删除失败，可能当前节点已被删除！");
                return er;
            }
            List<TbContentCategory> list = tbConCateDubboServiceImpl.show(parentId);
            if(null == list || 0 == list.size()){
                TbContentCategory category_parent = new TbContentCategory();
                category_parent.setId(parentId);
                category_parent.setIsParent(false);
                category_parent.setUpdated(date);
                tbConCateDubboServiceImpl.updTbConCateParent(category_parent);
            }
            er.setStatus(200);
        }
        return er;
    }
}
