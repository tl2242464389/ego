package com.ego.controller;

import com.commons.pojo.EasyUIDataGrid;
import com.ego.service.TbitemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-07-30 11:14
 * @Version: 1.0
 */
@Controller
public class TbitemController {

    @Resource
    private TbitemService tbitemServiceImpl;

    @RequestMapping("/")
    public String wellcome(){
        return "index";
    }

    @RequestMapping("{page}")
    public String page(@PathVariable String page){
        return page;
    }

    @ResponseBody
    @RequestMapping("/item/list")
    public EasyUIDataGrid show(int page, int rows){
        return tbitemServiceImpl.show(page, rows);
    }

}
