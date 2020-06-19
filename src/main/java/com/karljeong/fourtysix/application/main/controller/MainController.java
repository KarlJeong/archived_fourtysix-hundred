package com.karljeong.fourtysix.application.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.user.article.general.service.GeneralService;
import com.karljeong.fourtysix.application.user.article.notice.service.NoticeService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;

@Controller
@RequestMapping
public class MainController {

	private final LoadStatic loadStatic;
	private final NoticeService noticeService;
	private final GeneralService generalService;

	@Autowired
	MainController(LoadStatic loadStatic, NoticeService noticeService, GeneralService generalService) {
		this.loadStatic = loadStatic;
		this.noticeService = noticeService;
		this.generalService = generalService;
	}

	@GetMapping
	public String view(Model model) {
		return this.viewMain(model);
	}

	@SuppressWarnings("unchecked")
    @GetMapping("/main")
	public String viewMain(Model model) {
	    List<Map<String, Object>> noticeArticleCategoryList = (List<Map<String, Object>>) loadStatic.getSystemCode().get("ART_NOTICE_CATEGORY").get("code");
	    model.addAttribute("noticeArticleCategoryList", noticeArticleCategoryList);
		model.addAttribute("noticeArticleList", noticeService.findArticyeForDashboardByBoardCode("NOTICE"));

		List<Map<String, Object>> generalArticleCategoryList = (List<Map<String, Object>>) loadStatic.getSystemCode().get("ART_DIARY_CATEGORY").get("code");
		model.addAttribute("generalArticleCategoryList", generalArticleCategoryList);
		model.addAttribute("generalArticleList", generalService.readList(new HashMap<String, Object>(), PageRequest.of(0, 10)));
		return "/view/main/main";
	}

}
