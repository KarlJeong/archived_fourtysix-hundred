package com.karljeong.fourtysix.application.admin.codeGroup.controller;

import java.math.BigInteger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.admin.code.service.CodeService;
import com.karljeong.fourtysix.application.admin.codeGroup.service.CodeGroupService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;

@Controller
@RequestMapping("/admin/codegroup")
public class CodeGroupController {

	final CodeGroupService codeGroupService;
	final CodeService codeService;
	final LoadStatic loadStatic;

	CodeGroupController(CodeGroupService codeGroupService, CodeService codeService, LoadStatic loadStatic) {
		this.codeGroupService = codeGroupService;
		this.codeService = codeService;
		this.loadStatic = loadStatic;
	}

	@GetMapping("/viewmain")
	public String viewMain(Model model) {
		model.addAttribute("codeUseYn", loadStatic.getSystemCode().get("USE_YN").get("code"));
		return "/view/admin/codeGroup/codeGroup";
	}

	@GetMapping("/viewcreate")
	public String viewCreate(Model model) {
		model.addAttribute("unselectedCodeList", codeService.findByCodeGroupIdNull());
		model.addAttribute("codeUseYn", loadStatic.getSystemCode().get("USE_YN").get("code"));
		return "/view/admin/codeGroup/codeGroupC";
	}

	@GetMapping("/viewupdate/{codeGroupId}")
	public String viewUpdate(Model model, @PathVariable("codeGroupId") BigInteger codeGroupId) {
		model.addAttribute("mainInfo", codeGroupService.findById(codeGroupId));
		model.addAttribute("unselectedCodeList", codeService.findByCodeGroupIdNull());
		model.addAttribute("codeUseYn", loadStatic.getSystemCode().get("USE_YN").get("code"));
		return "/view/admin/codeGroup/codeGroupU";
	}

}
