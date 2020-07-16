package com.karljeong.fourtysix.application.admin.blog.controller;

import java.math.BigInteger;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.admin.blog.service.AdminBlogService;
import com.karljeong.fourtysix.database.entity.TbArticleBlog;
import com.karljeong.fourtysix.resulthandler.DataTableDto;
import com.karljeong.fourtysix.resulthandler.ResultDto;
import com.karljeong.fourtysix.resulthandler.ResultDto.ResultCodeEnum;
import com.karljeong.fourtysix.resulthandler.ResultSetter;

@RestController
@RequestMapping("/v1/api/admin/blog")
public class AdminBlogRestController {

    private final AdminBlogService adminBlogService;

    @Autowired
	AdminBlogRestController(AdminBlogService patternService) {
		this.adminBlogService = patternService;
	}

	@GetMapping("/datatable")
	public DataTableDto readListForDataTable(@RequestParam(required = true) MultiValueMap<String, String> formData) {
	    int start = Integer.parseInt(formData.get("start").get(0));
	    int length = Integer.parseInt(formData.get("length").get(0));
	    
	    Page<TbArticleBlog> retrievedBlog = adminBlogService.readList(new HashMap<String, Object>(), PageRequest.of(start / length , length, Sort.by("requestPublishYn").ascending().and(Sort.by("articleWriteDatetime").descending())));

	    DataTableDto dto = new DataTableDto();
	    dto.setDraw(Integer.parseInt(formData.get("draw").get(0)));
	    dto.setRecordsFiltered((int) retrievedBlog.getTotalElements());
	    dto.setRecordsTotal((int) retrievedBlog.getTotalElements());
	    dto.setData(retrievedBlog.toList());

	    return dto;
	}

    @PostMapping("/{articleId}/{publishYn}")
    public ResultDto update(@PathVariable("articleId") BigInteger articleId, @PathVariable("publishYn") int publishYn) {
        int result = adminBlogService.updatePublish(articleId, publishYn);
        return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT_ALERT, "Saved Successfully", result,
                "/admin/blog/viewupdate/" + articleId).getResultDto();
    }
}
