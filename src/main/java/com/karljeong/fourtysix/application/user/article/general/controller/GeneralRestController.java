package com.karljeong.fourtysix.application.user.article.general.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.user.article.general.service.GeneralService;
import com.karljeong.fourtysix.database.entity.TbArticleGeneral;
import com.karljeong.fourtysix.resulthandler.ResultDto;
import com.karljeong.fourtysix.resulthandler.ResultDto.ResultCodeEnum;
import com.karljeong.fourtysix.resulthandler.ResultSetter;

@RestController
@RequestMapping("/v1/api/b/general")
public class GeneralRestController {

	private final GeneralService generalService;

	@Autowired
	GeneralRestController(GeneralService generalService) {
		this.generalService = generalService;
	}

	@PostMapping
	public ResultDto save(@RequestBody TbArticleGeneral tbArticleGeneral) {
		TbArticleGeneral createTbArticleGeneral = generalService.create(tbArticleGeneral);
		return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT, "Saved Successfully", createTbArticleGeneral,
				"/b/general/viewupdate/" + createTbArticleGeneral.getArticleId()).getResultDto();
	}

}
