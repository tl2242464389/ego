package com.ego.service.impl;

import com.commons.utils.FtpUtil;
import com.ego.service.PicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-01 11:50
 * @Version: 1.0
 */
@Service
public class PicServiceImpl implements PicService {

    @Value("${ftpclient.host}")
    private String host;
    @Value("${ftpclient.port}")
    private int port;
    @Value("${ftpclient.username}")
    private String username;
    @Value("${ftpclient.password}")
    private String password;
    @Value("${ftpclient.basepath}")
    private String basepath;
    @Value("${ftpclient.filepath}")
    private String filepath;

    @Override
    public Map<String, Object> upload(MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        boolean result = false;
        try {
            result = FtpUtil.uploadFile(host, port, username, password, basepath, filepath, newName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(result){
                map.put("error", 0);
                map.put("url", "http://" + host + filepath + newName);
            }else{
                map.put("error", 1);
                map.put("message", "图片上传失败");
            }
        }
        return map;
    }
}
