package com.ego.controller;

import com.commons.pojo.EasyUIDataGrid;
import com.commons.pojo.EgoResult;
import com.ego.pojo.TbContent;
import com.ego.service.TbContentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-11 12:01
 * @Version: 1.0
 */
@Controller
public class TbContentController {
    @Resource
    private TbContentService tbContentServiceImpl;

    @ResponseBody
    @RequestMapping("/content/query/list")
    public EasyUIDataGrid show(long categoryId, int page, int rows){
        return tbContentServiceImpl.show(categoryId, page, rows);
    }

    @ResponseBody
    @RequestMapping("/rest/content/edit")
    public EgoResult updTbContent(TbContent content){
        return tbContentServiceImpl.updTbContent(content);
    }

    @ResponseBody
    @RequestMapping(value = "/content/delete", method = RequestMethod.POST)
    public EgoResult delTbContent(String ids){
        return tbContentServiceImpl.delTbContent(ids);
    }
}
