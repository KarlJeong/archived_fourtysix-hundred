package com.karljeong.fourtysix.application.admin.code.controller;

import java.math.BigInteger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.admin.code.service.CodeService;
import com.karljeong.fourtysix.application.admin.codeGroup.service.CodeGroupService;

@Controller
@RequestMapping("/admin/code")
public class CodeController {
	final CodeService codeService;
	final CodeGroupService codeGroupService;

	CodeController(CodeService codeService, CodeGroupService codeGroupService) {
		this.codeService = codeService;
		this.codeGroupService = codeGroupService;
	}

	@GetMapping("/viewmain")
	public String viewMain() {
		return "/view/admin/code/code";
	}

	@GetMapping("/viewcreate")
	public String viewCreate(Model model) {
        model.addAttribute("codeGroupList", codeGroupService.findAll());
		return "/view/admin/code/codeC";
	}

	@GetMapping("/viewupdate/{codeId}")
	public String viewUpdate(Model model, @PathVariable("codeId") BigInteger codeId) {
		model.addAttribute("mainInfo", codeService.findById(codeId));
        model.addAttribute("codeGroupList", codeGroupService.findAll());
		return "/view/admin/code/codeU";
	}
}
