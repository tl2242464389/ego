package com.ego.controller;

import com.ego.service.ItemMenuService;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-09 11:27
 * @Version: 1.0
 */
@Controller
public class ItemMenuController {

    @Resource
    private ItemMenuService itemMenuServiceImpl;

    @ResponseBody
    @RequestMapping("/rest/itemcat/all")
    public MappingJacksonValue show(String callback){
        MappingJacksonValue mjv = new MappingJacksonValue(itemMenuServiceImpl.show());
        mjv.setJsonpFunction(callback);
        return mjv;
    }
}
