package com.karljeong.fourtysix.application.admin.board.controller;

import java.math.BigInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.admin.auth.service.AuthService;
import com.karljeong.fourtysix.application.admin.board.service.BoardService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;
import com.karljeong.fourtysix.database.entity.TbComBoard;

@Controller
@RequestMapping("/admin/board")
public class BoardController {
	private final BoardService boardService;
	private final AuthService authService;
    private final LoadStatic loadStatic;

	@Autowired
	BoardController(BoardService boardService, AuthService authService, LoadStatic loadStatic) {
		this.boardService = boardService;
		this.authService = authService;
		this.loadStatic = loadStatic;
	}

	@GetMapping("/viewmain")
	public String viewMain() {
		return "view/admin/board/board";
	}

	@GetMapping("/viewcreate")
	public String viewCreate(Model model) {
		model.addAttribute("allAuthList", authService.findAll());
		model.addAttribute("systemBoardYn", loadStatic.getSystemCode().get("SYSTEM_BOARD_YN").get("code"));
		model.addAttribute("codeUseYn", loadStatic.getSystemCode().get("USE_YN").get("code"));
		return "view/admin/board/boardC";
	}

	@GetMapping("/viewupdate/{boardId}")
	public String viewUpdate(Model model, @PathVariable("boardId") BigInteger boardId) {
        TbComBoard tbComBoard = boardService.findById(boardId);
        model.addAttribute("mainInfo", tbComBoard);
        model.addAttribute("allAuthList", authService.findAll());
        model.addAttribute("selectedWritableList", tbComBoard.getTbComAuthWritable().stream().map(d -> d.getId().getAuthId()).collect(Collectors.toList()));
        model.addAttribute("selectedReadableList", tbComBoard.getTbComAuthReadable().stream().map(d -> d.getId().getAuthId()).collect(Collectors.toList()));
        model.addAttribute("systemBoardYn", loadStatic.getSystemCode().get("SYSTEM_BOARD_YN").get("code"));
	    model.addAttribute("codeUseYn", loadStatic.getSystemCode().get("USE_YN").get("code"));
		return "view/admin/board/boardU";
	}
}
