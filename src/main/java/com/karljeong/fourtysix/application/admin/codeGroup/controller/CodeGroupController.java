package com.karljeong.fourtysix.application.admin.codeGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.admin.code.service.CodeService;
import com.karljeong.fourtysix.application.admin.codeGroup.service.CodeGroupService;

@Controller
@RequestMapping("/admin/codegroup")
public class CodeGroupController {

	final CodeGroupService codeGroupService;
	final CodeService codeService;

	CodeGroupController(CodeGroupService codeGroupService, CodeService codeService) {
		this.codeGroupService = codeGroupService;
		this.codeService = codeService;
	}

	@GetMapping("/viewmain")
	public String viewMain() {
		return "/view/admin/codeGroup/codeGroup";
	}

	@GetMapping("/viewcreate")
	public String viewCreate(Model model) {
		model.addAttribute("unselectedCodeList", codeService.findByCodeGroupIdNull());
		return "/view/admin/codeGroup/codeGroupC";
	}

	@GetMapping("/viewupdate/{codeGroupId}")
	public String viewUpdate(Model model, @PathVariable("codeGroupId") String codeGroupId) {
		model.addAttribute("mainInfo", codeGroupService.findById(codeGroupId));
		model.addAttribute("unselectedCodeList", codeService.findByCodeGroupIdNull());
		return "/view/admin/codeGroup/codeGroupU";
	}

}
