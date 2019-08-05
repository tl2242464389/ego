package com.ego.controller;

import com.commons.pojo.EasyUIDataGrid;
import com.ego.service.TbItemParamService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 商品规格
 * @Author: tl
 * @Date: 2019-08-05 11:03
 * @Version: 1.0
 */
@Controller
public class TbItemParamController {

    @Resource
    private TbItemParamService tbItemParamServiceImpl;

    @ResponseBody
    @RequestMapping("/item/param/list")
    public EasyUIDataGrid show(int page, int rows){
        return tbItemParamServiceImpl.show(page, rows);
    }
}
