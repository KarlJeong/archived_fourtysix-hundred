package com.karljeong.fourtysix.application.article.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.admin.board.service.BoardService;
import com.karljeong.fourtysix.application.article.service.ArticleService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;

@Controller
@RequestMapping("/admin/article")
public class ArticleController {

	private final LoadStatic loadStatic;
    private final BoardService boardService;
    private final ArticleService articleService;

	@Autowired
	ArticleController(LoadStatic loadStatic, BoardService boardService, ArticleService articleService) {
		this.loadStatic = loadStatic;
		this.boardService = boardService;
		this.articleService = articleService;
	}

	@GetMapping("/viewmain")
	public String viewMain(Model model) {
		return "/view/admin/article/article";
	}

	@GetMapping("/viewcreate")
	public String viewCreate(Model model) {
	    model.addAttribute("allBoardList", boardService.findAll());
	    model.addAttribute("publishYn", loadStatic.getSystemCode().get("PUBLISH_YN").get("code"));
        model.addAttribute("deletedYn", loadStatic.getSystemCode().get("DELETED_YN").get("code"));
		return "/view/admin/article/articleC";
	}

	@GetMapping("/viewupdate/{articleId}")
	public String viewUpdate(Model model, @PathVariable("articleId") BigInteger articleId) {
	    model.addAttribute("mainInfo", articleService.findById(articleId));
        model.addAttribute("allBoardList", boardService.findAll());
        model.addAttribute("publishYn", loadStatic.getSystemCode().get("PUBLISH_YN").get("code"));
        model.addAttribute("deletedYn", loadStatic.getSystemCode().get("DELETED_YN").get("code"));
		return "/view/admin/article/articleU";
	}
}
