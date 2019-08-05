package com.ego.controller;

import com.ego.service.PicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-01 14:46
 * @Version: 1.0
 */
@Controller
public class PicController {

    @Resource
    private PicService picServiceImpl;

    @ResponseBody
    @RequestMapping("/pic/upload")
    public Map<String, Object> upload(MultipartFile uploadFile){
        return picServiceImpl.upload(uploadFile);
    }

}
