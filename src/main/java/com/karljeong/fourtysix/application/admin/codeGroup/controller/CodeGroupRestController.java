package com.karljeong.fourtysix.application.admin.codeGroup.controller;

import java.math.BigInteger;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.admin.codeGroup.service.CodeGroupService;
import com.karljeong.fourtysix.database.entity.TbComCodeGroup;

@RestController
@RequestMapping("/v1/api/admin/codegroup")
public class CodeGroupRestController {

	final CodeGroupService codeGroupService;

	CodeGroupRestController(CodeGroupService codeGroupService) {
		this.codeGroupService = codeGroupService;
	}

	@GetMapping
	public Page<TbComCodeGroup> readList(@RequestParam(required = false) Map<String, Object> searchRequest,
			final Pageable pageable) {
		return codeGroupService.readList(searchRequest, pageable);

	}

	@PostMapping
	public TbComCodeGroup create(@RequestBody TbComCodeGroup tbComCodeGroup) {
		return codeGroupService.create(tbComCodeGroup);
	}

	@PostMapping("/{codeGroupId}")
	public TbComCodeGroup update(@RequestBody TbComCodeGroup tbComCodeGroup,
			@PathVariable("codeGroupId") BigInteger codeGroupId) {
		return codeGroupService.update(tbComCodeGroup);
	}
}
