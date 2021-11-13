package com.fsoft.igos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portal")
public class PortalController {
    private static Logger LOGGER = LoggerFactory.getLogger(PortalController.class);

    @GetMapping(value = {""})
    public String index(Model model) {
        return "redirect:/portal/index";
    }

    @GetMapping(value = {"/", "/index"})
    public String getPortalIndex(Model model) {
        return "portal/index";
    }

}
