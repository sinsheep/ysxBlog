package com.ysx.web;

import com.ysx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutShowController {


    @Autowired
    private TagService tagService;

    @GetMapping("/about")
    public String about(Model model){

        model.addAttribute("tags",tagService.listTagTop(5));
        return "about";
    }

}
