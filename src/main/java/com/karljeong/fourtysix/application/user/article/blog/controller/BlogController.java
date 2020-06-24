package com.karljeong.fourtysix.application.user.article.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.karljeong.fourtysix.application.user.article.blog.service.BlogService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;
import com.karljeong.fourtysix.database.entity.TbArticleBlog;
import com.karljeong.fourtysix.utils.PagingUtil;
import com.karljeong.fourtysix.utils.ValidationUtil;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final LoadStatic loadStatic;
    private final BlogService blogService;

    @Autowired
    BlogController(LoadStatic loadStatic, BlogService blogService) {
        this.loadStatic = loadStatic;
        this.blogService = blogService;
    }

    @GetMapping("/viewmain")
    public String viewMain(Model model, @RequestParam(required = false) Map<String, Object> searchRequest, final Pageable pageable) {
        List<Map<String, Object>> articleNumberList = (List<Map<String, Object>>) loadStatic.getSystemCode().get("ARTICLE_NUMBER").get("code");
        if (!ValidationUtil.isValidPageSize(pageable.getPageSize(), articleNumberList)) {
            throw new RuntimeException("Invalid Paging Request.");
        }

        Page<TbArticleBlog> articleBlogList = blogService.readList(searchRequest, pageable);
        model.addAttribute("articleList", articleBlogList);
        model.addAttribute("articleNumber", articleNumberList);
        model.addAttribute("paging", PagingUtil.getPageList(articleBlogList.getTotalPages(), articleBlogList.getNumber()));

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
