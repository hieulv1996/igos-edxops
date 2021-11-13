package com.fsoft.igos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("message", "Hello");
        return "index";
    }

}
