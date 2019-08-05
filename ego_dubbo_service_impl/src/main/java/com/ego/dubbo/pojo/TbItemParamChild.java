package com.ego.dubbo.pojo;

import com.ego.pojo.TbItemParam;

/**
 * @Description: 商品规格+商品类目
 * @Author: tl
 * @Date: 2019-08-05 10:46
 * @Version: 1.0
 */
public class TbItemParamChild extends TbItemParam{
    // 商品类目名称
    private String itemCatName;

    public String getItemCatName() {
        return itemCatName;
    }

    public void setItemCatName(String itemCatName) {
        this.itemCatName = itemCatName;
    }
}
