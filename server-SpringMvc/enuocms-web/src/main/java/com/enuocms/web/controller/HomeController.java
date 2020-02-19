package com.enuocms.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {

    @RequestMapping("")
    public String index(Model model) {
    	return "/home/index";
	}

    @RequestMapping("about")
    public String about(Model model) {

        model.addAttribute("message","Your application description page.");

        return "/home/about";
    }
}