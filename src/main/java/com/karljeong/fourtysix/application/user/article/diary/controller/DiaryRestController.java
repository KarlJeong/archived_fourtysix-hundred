package com.karljeong.fourtysix.application.user.article.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.karljeong.fourtysix.application.user.article.diary.service.DiaryService;
import com.karljeong.fourtysix.database.entity.TbArticleDiary;

@RestController
@RequestMapping("/v1/api/diaries")
public class DiaryRestController {

    @Autowired
    DiaryService diaryService;

    @GetMapping
    public Iterable<TbArticleDiary> getDiaries(@RequestBody TbArticleDiary tbArticleDiary) {
        return diaryService.getDiaries(tbArticleDiary);
    }

    @GetMapping("/{articleId}")
    public TbArticleDiary getDiary(@PathVariable String articleId) {
        return diaryService.getDiary(articleId);
    }

    @PostMapping
    public TbArticleDiary save(@RequestBody TbArticleDiary tbArticleDiary) {
        return diaryService.create(tbArticleDiary);
    }
}
