package com.karljeong.fourtysix.application.user.mypage.myarticle.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.user.mypage.myarticle.service.MyArticleService;
import com.karljeong.fourtysix.database.entity.TbArticle;
import com.karljeong.fourtysix.resulthandler.ResultDto;
import com.karljeong.fourtysix.resulthandler.ResultDto.ResultCodeEnum;
import com.karljeong.fourtysix.resulthandler.ResultSetter;
import com.karljeong.fourtysix.utils.PagingUtil;
import com.karljeong.fourtysix.utils.UserUtil;

@RestController
@RequestMapping("/v1/api/myarticle")
public class MyArticleRestController {

	@Autowired
	MyArticleService myArticleService;

	@GetMapping
	public ResultDto readList(@RequestParam(required = false) Map<String, Object> searchRequest,
			final Pageable pageable) {
		searchRequest.put("articleWriterId", UserUtil.getUserId());
		System.out.println(searchRequest.get("articleCode"));
		Page<TbArticle> retrievedTbArticle = myArticleService.readList(searchRequest, pageable);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("articleList", retrievedTbArticle);
		data.put("paging", PagingUtil.getPageList(retrievedTbArticle.getTotalPages(), retrievedTbArticle.getNumber()));

		return new ResultSetter(ResultCodeEnum.SUCCESS, data).getResultDto();
	}

}
