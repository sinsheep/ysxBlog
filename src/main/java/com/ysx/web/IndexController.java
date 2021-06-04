package com.ysx.web;

import com.ysx.service.BlogService;
import com.ysx.service.TagService;
import com.ysx.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {

        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        System.out.println();
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 10
            , sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model
            , @RequestParam String query) {

        model.addAttribute("page",blogService.listBlog("%"+query.trim()+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }
}
