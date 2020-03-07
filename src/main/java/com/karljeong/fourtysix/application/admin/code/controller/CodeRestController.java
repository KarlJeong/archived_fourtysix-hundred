package com.karljeong.fourtysix.application.admin.code.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.admin.code.service.CodeService;
import com.karljeong.fourtysix.database.entity.TbComCode;

@RestController
@RequestMapping("/v1/api/admin/code")
public class CodeRestController {
	final CodeService codeService;

	CodeRestController(CodeService codeService) {
		this.codeService = codeService;
	}

    @GetMapping
    public Page<TbComCode> readList(@RequestParam(required = false) Map<String, Object> searchRequest,
            final Pageable pageable) {
        return codeService.readList(searchRequest, pageable);

    }
}
