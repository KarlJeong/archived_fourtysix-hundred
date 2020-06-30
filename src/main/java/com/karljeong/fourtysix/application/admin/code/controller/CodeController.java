package com.karljeong.fourtysix.application.admin.code.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.admin.code.service.CodeService;
import com.karljeong.fourtysix.application.admin.codeGroup.service.CodeGroupService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;

@Controller
@RequestMapping("/admin/code")
public class CodeController {
	private final CodeService codeService;
	private final CodeGroupService codeGroupService;
	private final LoadStatic loadStatic;

	@Autowired
	CodeController(CodeService codeService, CodeGroupService codeGroupService, LoadStatic loadStatic) {
		this.codeService = codeService;
		this.codeGroupService = codeGroupService;
		this.loadStatic = loadStatic;
	}

	@GetMapping("/viewmain")
	public String viewMain(Model model) {
		model.addAttribute("codeUseYn", loadStatic.getSystemCode().get("USE_YN").get("code"));
		return "view/admin/code/code";
	}

	@GetMapping("/viewcreate")
	public String viewCreate(Model model) {
		model.addAttribute("codeGroupList", codeGroupService.findAll());
		model.addAttribute("codeUseYn", loadStatic.getSystemCode().get("USE_YN").get("code"));
		return "view/admin/code/codeC";
	}

	@GetMapping("/viewupdate/{codeId}")
	public String viewUpdate(Model model, @PathVariable("codeId") BigInteger codeId) {
		model.addAttribute("mainInfo", codeService.findById(codeId));
		model.addAttribute("codeGroupList", codeGroupService.findAll());
		model.addAttribute("codeUseYn", loadStatic.getSystemCode().get("USE_YN").get("code"));
		return "view/admin/code/codeU";
	}
}
