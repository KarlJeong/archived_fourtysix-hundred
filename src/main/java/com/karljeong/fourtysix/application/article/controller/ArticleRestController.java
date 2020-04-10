package com.karljeong.fourtysix.application.article.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.article.service.ArticleService;
import com.karljeong.fourtysix.database.entity.TbComArticle;
import com.karljeong.fourtysix.resulthandler.ResultDto;
import com.karljeong.fourtysix.resulthandler.ResultDto.ResultCodeEnum;
import com.karljeong.fourtysix.resulthandler.ResultSetter;

@RestController
@RequestMapping("/v1/api/admin/article")
public class ArticleRestController {

    private final ArticleService articleService;

    @Autowired
	ArticleRestController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@GetMapping
	public ResultDto readList(@RequestParam(required = false) Map<String, Object> searchRequest,
			final Pageable pageable) {
	    return new ResultSetter(ResultCodeEnum.SUCCESS, null, articleService.readList(searchRequest, pageable), null).getResultDto();
	}

    @PostMapping
    public ResultDto create(@RequestBody TbComArticle tbComArticle) {
        TbComArticle createdTbComArticle = articleService.create(tbComArticle);
        return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT, "Saved Successfully", createdTbComArticle, "/admin/article/viewupdate/" + createdTbComArticle.getArticleId()).getResultDto();
    }

}
