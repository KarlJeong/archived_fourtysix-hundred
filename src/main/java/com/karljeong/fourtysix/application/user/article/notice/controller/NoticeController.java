package com.karljeong.fourtysix.application.user.article.notice.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.karljeong.fourtysix.application.user.article.notice.service.NoticeService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;
import com.karljeong.fourtysix.database.entity.TbComArticle;
import com.karljeong.fourtysix.utils.PagingUtil;
import com.karljeong.fourtysix.utils.ValidationUtil;

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

	@SuppressWarnings("unchecked")
	@GetMapping("/viewmain")
	public String viewMain(Model model, @RequestParam(required = false) Map<String, Object> searchRequest,
			final Pageable pageable) {

		List<Map<String, Object>> articleNumberList = (List<Map<String, Object>>) loadStatic.getSystemCode()
				.get("ARTICLE_NUMBER").get("code");
		if (!ValidationUtil.isValidPageSize(pageable.getPageSize(), articleNumberList)) {
			throw new RuntimeException("Invalid Paging Request.");
		}

		Page<TbComArticle> noticeList = noticeService.readList(searchRequest, pageable);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("articleNumber", articleNumberList);
		model.addAttribute("paging", PagingUtil.getPageList(noticeList.getTotalPages(), noticeList.getNumber()));
		return "/view/article/notice/notice";
	}

	@GetMapping("/viewdetail/{articleId}")
	public String viewUpdate(Model model, @PathVariable("articleId") BigInteger articleId) {
		model.addAttribute("articleInfo", noticeService.findById(articleId));
		return "/view/article/notice/noticeR";
	}
}
