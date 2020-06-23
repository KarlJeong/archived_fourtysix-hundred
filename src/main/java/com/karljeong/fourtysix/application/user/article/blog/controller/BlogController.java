package com.karljeong.fourtysix.application.user.article.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.common.loadstatic.LoadStatic;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final LoadStatic loadStatic;

    @Autowired
    BlogController(LoadStatic loadStatic) {
        this.loadStatic = loadStatic;
    }

    @GetMapping("/viewmain")
    public String viewMain() {
        return "/view/article/blog/blog";
    }

    @GetMapping("/viewcreate")
    public String viewCreate(Model model) {
        List<Map<String, Object>> blogArticleCategoryList = (List<Map<String, Object>>) loadStatic.getSystemCode().get("ART_BLOG_CATEGORY").get("code");
        model.addAttribute("blogArticleCategoryList", blogArticleCategoryList);
        return "/view/article/blog/blogC";
    }

    @GetMapping("/viewdetail")
    public String viewDetail() {
        return "/view/article/blog/blogR";
    }

}
