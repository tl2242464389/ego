package com.ego.controller;

import com.commons.pojo.EasyUiTree;
import com.ego.service.TbItemCatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-03 11:47
 * @Version: 1.0
 */
@Controller
public class TbItemCatController {

    @Resource
    private TbItemCatService tbItemCatServiceImpl;

    @ResponseBody
    @RequestMapping("/item/cat/list")
    public List<EasyUiTree> show(@RequestParam(defaultValue = "0") long id){
        return tbItemCatServiceImpl.show(id);
    }

}
