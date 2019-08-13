package com.ego.controller;

import com.commons.pojo.EasyUiTree;
import com.commons.pojo.EgoResult;
import com.ego.pojo.TbContentCategory;
import com.ego.service.TbConCateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-10 11:14
 * @Version: 1.0
 */
@Controller
public class TbConCateController {
    @Resource
    private TbConCateService tbConCateServiceImpl;

    @ResponseBody
    @RequestMapping("/content/category/list")
    public List<EasyUiTree> show(@RequestParam(defaultValue = "0") long id){
        return tbConCateServiceImpl.show(id);
    }

    @ResponseBody
    @RequestMapping("/content/category/create")
    public EgoResult create(TbContentCategory category){
        return tbConCateServiceImpl.insTbConCate(category);
    }

    @ResponseBody
    @RequestMapping("/content/category/update")
    public EgoResult updName(long id, String name){
        return tbConCateServiceImpl.updName(id, name);
    }

    @ResponseBody
    @RequestMapping("/content/category/delete")
    public EgoResult delById(long id){
        return tbConCateServiceImpl.delById(id);
    }

}
