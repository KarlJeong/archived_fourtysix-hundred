package com.karljeong.fourtysix.application.article.diary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diary")
public class DiaryController {

    @GetMapping("/viewMain")
    public String viewMain() {
        return "/view/article/diary/diary";
    }

    @GetMapping("/viewCreate")
    public String viewCreate() {
        return "/view/article/diary/diaryC";
    }

    @GetMapping("/viewDetail")
    public String viewDetail() {
        return "/view/article/diary/diaryR";
    }

}
