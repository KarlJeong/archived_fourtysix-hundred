package com.karljeong.fourtysix.application.admin.codeGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.admin.codeGroup.service.CodeGroupService;

@Controller
@RequestMapping("/admin/codegroup")
public class CodeGroupController {

	final CodeGroupService codeGroupService;

	CodeGroupController(CodeGroupService codeGroupService) {
		this.codeGroupService = codeGroupService;
	}

	@GetMapping("/viewmain")
	public String viewMain() {
		return "/view/admin/codeGroup/codeGroup";
	}

	@GetMapping("/viewcreate")
	public String viewCreate() {
		return "/view/admin/codeGroup/codeGroupC";
	}

	@GetMapping("/viewupdate/{codeGroupId}")
	public String viewUpdate(Model model, @PathVariable("codeGroupId") Long codeGroupId) {
		model.addAttribute("mainInfo", codeGroupService.findById(codeGroupId));
		return "/view/admin/codeGroup/codeGroupU";
	}

}
