package com.karljeong.fourtysix.application.admin.code.controller;

import java.math.BigInteger;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.admin.code.service.CodeService;
import com.karljeong.fourtysix.database.entity.TbComCode;

import resulthandler.ResultDto;
import resulthandler.ResultDto.ResultCodeEnum;
import resulthandler.ResultSetter;

@RestController
@RequestMapping("/v1/api/admin/code")
public class CodeRestController {
	final CodeService codeService;

	CodeRestController(CodeService codeService) {
		this.codeService = codeService;
	}

	@GetMapping
	public ResultDto readList(@RequestParam(required = false) Map<String, Object> searchRequest,
			final Pageable pageable) {
		return new ResultSetter(ResultCodeEnum.SUCCESS, null, codeService.readList(searchRequest, pageable), null).getResultDto();
	}

	@PostMapping
	public ResultDto create(@RequestBody TbComCode tbComCode) {
	    TbComCode createdTbComCode = codeService.create(tbComCode);
        return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT, "Saved Successfully", createdTbComCode, "/admin/code/viewupdate/" + createdTbComCode.getCodeId()).getResultDto();
	}

	@PostMapping("/{codeId}")
	public ResultDto update(@RequestBody TbComCode TbComCode, @PathVariable("codeId") BigInteger codeId) {
	    TbComCode updatedTbComCode = codeService.update(TbComCode);
		return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT, "Modified Successfully", updatedTbComCode, "/admin/code/viewupdate/" + updatedTbComCode.getCodeId()).getResultDto();
	}

	@DeleteMapping("/{codeId}")
	public ResultDto delete(@PathVariable("codeId") BigInteger codeId) {
	    codeService.delete(codeId);
		return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT, "Deleted Successfully", null, "/admin/code/viewmain").getResultDto();
	}
}
