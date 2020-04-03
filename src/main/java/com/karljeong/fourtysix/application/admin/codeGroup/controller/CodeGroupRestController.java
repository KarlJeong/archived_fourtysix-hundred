package com.karljeong.fourtysix.application.admin.codeGroup.controller;

import java.math.BigInteger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.admin.codeGroup.service.CodeGroupService;
import com.karljeong.fourtysix.database.entity.TbComCodeGroup;
import com.karljeong.fourtysix.resulthandler.ResultDto;
import com.karljeong.fourtysix.resulthandler.ResultSetter;
import com.karljeong.fourtysix.resulthandler.ResultDto.ResultCodeEnum;

@RestController
@RequestMapping("/v1/api/admin/codegroup")
public class CodeGroupRestController {

	private final CodeGroupService codeGroupService;

	@Autowired
	CodeGroupRestController(CodeGroupService codeGroupService) {
		this.codeGroupService = codeGroupService;
	}

	@GetMapping
	public ResultDto readList(@RequestParam(required = false) Map<String, Object> searchRequest,
			final Pageable pageable) {
		return new ResultSetter(ResultCodeEnum.SUCCESS, null, codeGroupService.readList(searchRequest, pageable), null).getResultDto();
	}

	@PostMapping
	public ResultDto create(@RequestBody TbComCodeGroup tbComCodeGroup) {
		TbComCodeGroup createdTbComCodeGroup = codeGroupService.create(tbComCodeGroup);
        return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT, "Saved Successfully", createdTbComCodeGroup, "/admin/codegroup/viewupdate/" + createdTbComCodeGroup.getCodeGroupId()).getResultDto();
	}

	@PostMapping("/{codeGroupId}")
	public ResultDto update(@RequestBody TbComCodeGroup tbComCodeGroup,
			@PathVariable("codeGroupId") BigInteger codeGroupId) {
	    TbComCodeGroup updatedTbComCodeGroup = codeGroupService.update(tbComCodeGroup);
        return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT, "Modified Successfully", updatedTbComCodeGroup, "/admin/codegroup/viewupdate/" + updatedTbComCodeGroup.getCodeGroupId()).getResultDto();
	}

	@DeleteMapping("/{codeGroupId}")
	public ResultDto delete(@PathVariable("codeGroupId") BigInteger codeGroupId) {
	    codeGroupService.delete(codeGroupId);
        return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT, "Deleted Successfully", null, "/admin/codegroup/viewmain").getResultDto();
	}
}
