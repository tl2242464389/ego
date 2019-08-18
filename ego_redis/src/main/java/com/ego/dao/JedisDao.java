package com.ego.dao;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-18 10:36
 * @Version: 1.0
 */
public interface JedisDao {
    /**
     * @description: 判断当前key是否存在
     * @param: key
     * @Date: 2019-08-18 10:50
     * @return: boolean
     */
    boolean exists(String key);

    /**
     * @description: 获得当前key的值
     * @param: key
     * @Date: 2019-08-18 10:51
     * @return: java.lang.String
     */
    String get(String key);

    /**
     * @description: 删除当前key
     * @param: key
     * @Date: 2019-08-18 10:52
     * @return: Long
     */
    Long delete(String key);

    /**
     * @description: 新增一组数据
     * @param: key
     * @param: value
     * @Date: 2019-08-18 10:52
     * @return: String
     */
    String set(String key, String value);
}
