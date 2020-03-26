package com.karljeong.fourtysix.application.admin.board.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.admin.auth.service.AuthService;
import com.karljeong.fourtysix.application.admin.board.service.BoardService;

@Controller
@RequestMapping("/admin/board")
public class BoardController {
	private final BoardService boardService;
	private final AuthService authService;

	@Autowired
	BoardController(BoardService boardService, AuthService authService) {
		this.boardService = boardService;
		this.authService = authService;
	}

	@GetMapping("/viewmain")
	public String viewMain() {
		return "/view/admin/board/board";
	}

	@GetMapping("/viewcreate")
	public String viewCreate(Model model) {
		model.addAttribute("allAuthList", authService.findAll());
		return "/view/admin/board/boardC";
	}

	@GetMapping("/viewupdate/{boardId}")
	public String viewUpdate(Model model, @PathVariable("boardId") BigInteger boardId) {
		return "/view/admin/board/boardU";
	}
}
