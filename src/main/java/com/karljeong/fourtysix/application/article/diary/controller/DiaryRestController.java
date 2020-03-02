package com.karljeong.fourtysix.application.article.diary.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.database.entity.TbArticleDiary;

@RestController
@RequestMapping("/v1/api/diary")
public class DiaryRestController {

    @PostMapping
    public String insert(@RequestBody TbArticleDiary tbArticleDiary) {
        System.out.println(tbArticleDiary);
        return "OK";
    }
}
