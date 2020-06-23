package com.karljeong.fourtysix.application.user.article.blog.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.application.admin.file.service.FileService;
import com.karljeong.fourtysix.database.entity.TbArticleBlog;
import com.karljeong.fourtysix.database.repository.TbArticleBlogRepository;

@Service
public class BlogService {
    @Autowired
    FileService fileService;

    @Autowired
    TbArticleBlogRepository tbArticleBlogRepository;

    public Iterable<TbArticleBlog> getBlogs(TbArticleBlog tbArticleDiary) {
        return tbArticleBlogRepository.findAll();
    }

    public TbArticleBlog getBlog(String articleId) {
        return tbArticleBlogRepository.findById(new BigInteger(articleId)).orElseGet(() -> new TbArticleBlog());
    }

    public TbArticleBlog create(TbArticleBlog tbArticleBlog) {
        TbArticleBlog save = tbArticleBlogRepository.save(tbArticleBlog);
        return save;
    }
}
