package com.karljeong.fourtysix.application.user.article.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.user.article.blog.service.BlogService;
import com.karljeong.fourtysix.database.entity.TbArticleBlog;
import com.karljeong.fourtysix.resulthandler.ResultDto;
import com.karljeong.fourtysix.resulthandler.ResultDto.ResultCodeEnum;
import com.karljeong.fourtysix.resulthandler.ResultSetter;

@RestController
@RequestMapping("/v1/api/blogs")
public class BlogRestController {

    @Autowired
    BlogService blogService;

    @GetMapping
    public Iterable<TbArticleBlog> getBlogs(@RequestBody TbArticleBlog tbArticleBlog) {
        return blogService.getBlogs(tbArticleBlog);
    }

    @GetMapping("/{articleId}")
    public TbArticleBlog getBlog(@PathVariable String articleId) {
        return blogService.getBlog(articleId);
    }

    @PostMapping
    public ResultDto save(@RequestBody TbArticleBlog tbArticleBlog, HttpServletRequest request) {
        tbArticleBlog.setUserInfo(request);
        TbArticleBlog createTbArticleBlog = blogService.create(tbArticleBlog);
        return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT, "Saved Successfully", createTbArticleBlog,
                "/blog/viewdetail/" + createTbArticleBlog.getArticleId()).getResultDto();
    }
}
