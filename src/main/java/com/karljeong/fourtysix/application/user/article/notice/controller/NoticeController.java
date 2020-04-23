package com.karljeong.fourtysix.application.user.article.notice.controller;

import java.math.BigInteger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.karljeong.fourtysix.application.user.article.notice.service.NoticeService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	private final LoadStatic loadStatic;
    private final NoticeService noticeService;

	@Autowired
	NoticeController(LoadStatic loadStatic, NoticeService noticeService) {
		this.loadStatic = loadStatic;
		this.noticeService = noticeService;
	}

	@GetMapping("/viewmain")
	public String viewMain(Model model, @RequestParam(required = false) Map<String, Object> searchRequest, final Pageable pageable) {
	    model.addAttribute("noticeList", noticeService.readList(searchRequest, pageable));
		return "/view/article/notice/notice";
	}

	@GetMapping("/viewdetail/{articleId}")
	public String viewUpdate(Model model, @PathVariable("articleId") BigInteger articleId) {
	    model.addAttribute("mainInfo", noticeService.findById(articleId));
        model.addAttribute("publishYn", loadStatic.getSystemCode().get("PUBLISH_YN").get("code"));
        model.addAttribute("deletedYn", loadStatic.getSystemCode().get("DELETED_YN").get("code"));
        return "/view/article/common/articleCommon";
	}
}
