package com.karljeong.fourtysix.application.article.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.article.diary.service.DiaryService;
import com.karljeong.fourtysix.database.entity.TbArticleDiary;

@RestController
@RequestMapping("/v1/api/diary")
public class DiaryRestController {

    @Autowired
    DiaryService diaryService;

    @PostMapping
    public TbArticleDiary save(@RequestBody TbArticleDiary tbArticleDiary) {
        return diaryService.create(tbArticleDiary);
    }
}
