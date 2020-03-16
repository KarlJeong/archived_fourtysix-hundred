package com.karljeong.fourtysix.application.admin.board.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.admin.board.service.BoardService;
import com.karljeong.fourtysix.database.entity.TbComBoard;

@RestController
@RequestMapping("/v1/api/admin/board")
public class BoardRestController {
	final BoardService boardService;

	BoardRestController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping
	public Page<TbComBoard> readList(@RequestParam(required = false) Map<String, Object> searchRequest,
			final Pageable pageable) {
		return boardService.readList(searchRequest, pageable);
	}

	@PostMapping
	public TbComBoard create(@RequestBody TbComBoard tbComBoard) {
		return boardService.create(tbComBoard);
	}
}
