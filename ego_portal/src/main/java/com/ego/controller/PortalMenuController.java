package com.ego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: tl
 * @Date: 2019-08-09 10:40
 * @Version: 1.0
 */
@Controller
public class PortalMenuController {

    @RequestMapping("/")
    public String welcome(){
        return "index";
    }
}
