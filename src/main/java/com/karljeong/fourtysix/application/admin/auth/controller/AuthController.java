package com.karljeong.fourtysix.application.admin.auth.controller;

import java.math.BigInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.admin.auth.service.AuthService;
import com.karljeong.fourtysix.application.admin.user.service.UserService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;
import com.karljeong.fourtysix.database.entity.TbComAuth;

@Controller
@RequestMapping("/admin/auth")
public class AuthController {

	private final AuthService authService;
	private final UserService userService;
	private final LoadStatic loadStatic;

	@Autowired
	AuthController(AuthService authService, UserService userService, LoadStatic loadStatic) {
		this.authService = authService;
		this.userService = userService;
		this.loadStatic = loadStatic;
	}

	@GetMapping("/viewmain")
	public String viewMain(Model model) {
		model.addAttribute("codeUseYn", loadStatic.getSystemCode().get("USE_YN").get("code"));
		return "view/admin/auth/auth";
	}

	@GetMapping("/viewcreate")
	public String viewCreate(Model model) {
		model.addAttribute("allUserList", userService.findAll());
		model.addAttribute("codeUseYn", loadStatic.getSystemCode().get("USE_YN").get("code"));
		return "view/admin/auth/authC";
	}

	@GetMapping("/viewupdate/{authId}")
	public String viewUpdate(Model model, @PathVariable("authId") BigInteger authId) {
	    TbComAuth tbComAuth = authService.findById(authId);
		model.addAttribute("mainInfo", tbComAuth);
		model.addAttribute("allUserList", userService.findAll());
		model.addAttribute("selectedUserList", tbComAuth.getTbComUsers().stream().map(d -> d.getUserId()).collect(Collectors.toList()));
		model.addAttribute("codeUseYn", loadStatic.getSystemCode().get("USE_YN").get("code"));
		return "view/admin/auth/authU";
	}
}
