package com.karljeong.fourtysix.application.admin.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.admin.code.service.CodeService;

@Controller
@RequestMapping("/admin/code")
public class CodeController {
	final CodeService codeService;

	CodeController(CodeService codeService) {
		this.codeService = codeService;
	}

	@GetMapping("/viewmain")
	public String viewMain() {
		return "/view/admin/code/code";
	}

	@GetMapping("/viewcreate")
	public String viewCreate() {
		return "/view/admin/code/codeC";
	}

	@GetMapping("/viewupdate/{codeId}")
	public String viewUpdate(Model model, @PathVariable("codeId") Long codeId) {
		model.addAttribute("mainInfo", codeService.findById(codeId));
		return "/view/admin/code/codeU";
	}
}
