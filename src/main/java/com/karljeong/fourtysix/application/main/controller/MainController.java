package com.karljeong.fourtysix.application.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.article.service.ArticleService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;

@Controller
@RequestMapping
public class MainController {

	private final LoadStatic loadStatic;
	private final ArticleService articleService;

	@Autowired
	MainController(LoadStatic loadStatic, ArticleService articleService) {
		this.loadStatic = loadStatic;
		this.articleService = articleService;
	}

    @GetMapping
    public String view(Model model) {
        return this.viewMain(model);
    }

	@GetMapping("/main")
	public String viewMain(Model model) {
	    model.addAttribute("noticeArticleList", articleService.findArticyeForDashboardByBoardCode("NOTICE"));
		return "/view/main/main";
	}

}
