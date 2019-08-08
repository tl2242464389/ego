package com.ego.controller;

import com.commons.pojo.EasyUIDataGrid;
import com.commons.pojo.EgoResult;
import com.ego.pojo.TbItem;
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

    @ResponseBody
    @RequestMapping("/rest/item/delete")
    public EgoResult delete(String ids){
        EgoResult er = new EgoResult();
        int index = tbitemServiceImpl.updItemStatus(ids, (byte) 3);
        if(index >= 1){
            er.setStatus(200);
        }
        return er;
    }

    @ResponseBody
    @RequestMapping("/rest/item/instock")
    public EgoResult instock(String ids){
        EgoResult er = new EgoResult();
        int index = tbitemServiceImpl.updItemStatus(ids, (byte) 2);
        if(index >= 1){
            er.setStatus(200);
        }
        return er;
    }

    @ResponseBody
    @RequestMapping("/rest/item/reshelf")
    public EgoResult reshelf(String ids){
        EgoResult er = new EgoResult();
        int index = tbitemServiceImpl.updItemStatus(ids, (byte) 1);
        if(index >= 1){
            er.setStatus(200);
        }
        return er;
    }

    @ResponseBody
    @RequestMapping("/item/save")
    public EgoResult save(TbItem tbItem, String desc, String itemParams){
        EgoResult er = new EgoResult();
        int index = 0;
        try {
            index = tbitemServiceImpl.insTbItemAndDesc(tbItem, desc, itemParams);
            if(index == 1){
                er.setStatus(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
            er.setData(e.getMessage());
        }
        return er;
    }
}
