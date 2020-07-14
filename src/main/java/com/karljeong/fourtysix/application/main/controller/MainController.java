package com.karljeong.fourtysix.application.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.user.article.blog.service.BlogService;
import com.karljeong.fourtysix.application.user.article.general.service.GeneralService;
import com.karljeong.fourtysix.application.user.article.notice.service.NoticeService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;

@Controller
@RequestMapping
public class MainController {

	private final LoadStatic loadStatic;
	private final BlogService blogService;
	private final NoticeService noticeService;
	private final GeneralService generalService;

	@Autowired
	MainController(LoadStatic loadStatic, NoticeService noticeService, GeneralService generalService, BlogService blogService) {
		this.loadStatic = loadStatic;
		this.blogService= blogService;
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
		model.addAttribute("noticeArticleList", noticeService.readList(new HashMap<String, Object>(), PageRequest.of(0, 3, Sort.by("articleWriteDatetime").descending())));


        List<Map<String, Object>> blogArticleCategoryList = (List<Map<String, Object>>) loadStatic.getSystemCode().get("ART_BLOG_CATEGORY").get("code");
        model.addAttribute("blogArticleCategoryList", blogArticleCategoryList);
        Map<String, Object> blogSearchKey = new HashMap<String, Object>();
        blogSearchKey.put("publishYn", 1);
        model.addAttribute("blogArticleList", blogService.readList(blogSearchKey, PageRequest.of(0, 3, Sort.by("articleWriteDatetime").descending())));

		List<Map<String, Object>> generalArticleCategoryList = (List<Map<String, Object>>) loadStatic.getSystemCode().get("ART_GENERAL_CATEGORY").get("code");
		model.addAttribute("generalArticleCategoryList", generalArticleCategoryList);
		model.addAttribute("generalArticleList", generalService.readList(new HashMap<String, Object>(), PageRequest.of(0, 10, Sort.by("articleWriteDatetime").descending())));
		return "view/main/main";
	}

}
