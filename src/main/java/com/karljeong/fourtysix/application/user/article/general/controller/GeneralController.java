package com.karljeong.fourtysix.application.user.article.general.controller;

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

import com.karljeong.fourtysix.application.user.article.general.service.GeneralService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;
import com.karljeong.fourtysix.database.entity.TbArticleGeneral;
import com.karljeong.fourtysix.utils.PagingUtil;
import com.karljeong.fourtysix.utils.ValidationUtil;

@Controller
@RequestMapping("/b/general")
public class GeneralController {

	private final LoadStatic loadStatic;
	private final GeneralService generalService;

	@Autowired
	GeneralController(LoadStatic loadStatic, GeneralService generalService) {
		this.loadStatic = loadStatic;
		this.generalService = generalService;
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

		Page<TbArticleGeneral> articleGeneralList = generalService.readList(searchRequest, pageable);
		model.addAttribute("articleList", articleGeneralList);
		model.addAttribute("articleNumber", articleNumberList);
		model.addAttribute("paging",
				PagingUtil.getPageList(articleGeneralList.getTotalPages(), articleGeneralList.getNumber()));
		return "/view/article/general/general";
	}

	@GetMapping("/viewcreate")
	public String viewCreate(Model model) {
		return "/view/article/general/generalC";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/viewdetail/{articleId}")
	public String viewUpdate(Model model, @PathVariable("articleId") BigInteger articleId,
			@RequestParam(required = false) Map<String, Object> searchRequest, final Pageable pageable) {
		model.addAttribute("articleInfo", generalService.findById(articleId));

		List<Map<String, Object>> articleNumberList = (List<Map<String, Object>>) loadStatic.getSystemCode()
				.get("ARTICLE_NUMBER").get("code");
		if (!ValidationUtil.isValidPageSize(pageable.getPageSize(), articleNumberList)) {
			throw new RuntimeException("Invalid Paging Request.");
		}

		Page<TbArticleGeneral> articleGeneralList = generalService.readList(searchRequest, pageable);
		model.addAttribute("articleList", articleGeneralList);
		model.addAttribute("articleNumber", articleNumberList);
		model.addAttribute("paging",
				PagingUtil.getPageList(articleGeneralList.getTotalPages(), articleGeneralList.getNumber()));

		return "/view/article/general/generalR";
	}
}
