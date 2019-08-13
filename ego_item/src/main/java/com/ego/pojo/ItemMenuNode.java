package com.ego.pojo;

import java.util.List;

/**
 * @Description: 商品类目子列表格式
 * @Author: tl
 * @Date: 2019-08-09 10:51
 * @Version: 1.0
 */
public class ItemMenuNode {
    // URL 地址
    private String u;
    // 当前节点名称
    private String n;
    // 当前节点的子节点
    private List<Object> i;

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public List<Object> getI() {
        return i;
    }

    public void setI(List<Object> i) {
        this.i = i;
    }
}
