package com.ego.controller;

import com.ego.service.TbContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-09 10:40
 * @Version: 1.0
 */
@Controller
public class PortalMenuController {

    @Resource
    private TbContentService tbContentServiceImpl;

    @RequestMapping("/")
    public String welcome(HttpServletRequest request){
        return "forward:/show";
    }

    @RequestMapping("/show")
    public String showBigPic(Model model){
        model.addAttribute("BigPic", tbContentServiceImpl.showBigPic());
        System.out.println("--------shouBigPic----------");
        return "index";
    }
}
