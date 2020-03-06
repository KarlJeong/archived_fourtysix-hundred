package com.karljeong.fourtysix.application.admin.code.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.admin.code.service.CodeService;

@RestController
@RequestMapping("/v1/api/admin/code")
public class CodeRestController {
	final CodeService codeService;

	CodeRestController(CodeService codeService) {
		this.codeService = codeService;
	}
}
