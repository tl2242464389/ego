package com.commons.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: EasyUI通用表格返回对象
 * @Author: tl
 * @Date: 2019-07-30 10:20
 * @Version: 1.0
 */
public class EasyUIDataGrid implements Serializable{
    // 总条目数
    private long total;
    // 当前页显示的行数据
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
