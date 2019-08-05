package com.commons.pojo;

import java.io.Serializable;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-07-31 10:35
 * @Version: 1.0
 */
public class EgoResult implements Serializable {
    private int status;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
