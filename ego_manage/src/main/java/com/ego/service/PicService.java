package com.ego.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Description: 图片处理
 * @Author: tl
 * @Date: 2019-08-01 11:48
 * @Version: 1.0
 */
public interface PicService {
    /**
     * @description: 图片上传
     * @param: file
     * @Date: 2019-08-01 11:48
     * @return: Map<String, Object>
     */
    Map<String, Object> upload(MultipartFile file);
}
