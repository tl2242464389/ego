package com.ego.dao.impl;

import com.ego.dao.JedisDao;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * @Description: Jedis操作Redis
 * @Author: tl
 * @Date: 2019-08-18 10:36
 * @Version: 1.0
 */
@Service
public class JedisDaoImpl implements JedisDao {
    @Resource
    private JedisCluster jedisClients;

    @Override
    public boolean exists(String key) {
        return jedisClients.exists(key);
    }

    @Override
    public String get(String key) {
        return jedisClients.get(key);
    }

    @Override
    public Long delete(String key) {
        return jedisClients.del(key);
    }

    @Override
    public String set(String key, String value) {
        return jedisClients.set(key, value);
    }
}
