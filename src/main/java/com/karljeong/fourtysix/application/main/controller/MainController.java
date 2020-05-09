package com.karljeong.fourtysix.application.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.user.article.notice.service.NoticeService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;

@Controller
@RequestMapping
public class MainController {

	private final LoadStatic loadStatic;
	private final NoticeService noticeService;

	@Autowired
	MainController(LoadStatic loadStatic, NoticeService noticeService) {
		this.loadStatic = loadStatic;
		this.noticeService = noticeService;
	}

	@GetMapping
	public String view(Model model) {
		return this.viewMain(model);
	}

	@GetMapping("/main")
	public String viewMain(Model model) {
		model.addAttribute("noticeArticleList", noticeService.findArticyeForDashboardByBoardCode("NOTICE"));
		return "/view/main/main";
	}

}
