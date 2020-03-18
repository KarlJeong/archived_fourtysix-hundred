package com.karljeong.fourtysix.application.admin.auth.controller;

import java.math.BigInteger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.admin.auth.service.AuthService;
import com.karljeong.fourtysix.application.admin.user.service.UserService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;

@Controller
@RequestMapping("/admin/auth")
public class AuthController {

	final AuthService authService;
	final UserService userService;
	final LoadStatic loadStatic;

	AuthController(AuthService authService, UserService userService, LoadStatic loadStatic) {
		this.authService = authService;
		this.userService = userService;
		this.loadStatic = loadStatic;
	}

	@GetMapping("/viewmain")
	public String viewMain(Model model) {
		model.addAttribute("codeUseYn", loadStatic.getSystemCode().get("USE_YN").get("code"));
		return "/view/admin/auth/auth";
	}

	@GetMapping("/viewcreate")
	public String viewCreate(Model model) {
		model.addAttribute("allUserList", userService.findAll());
		model.addAttribute("codeUseYn", loadStatic.getSystemCode().get("USE_YN").get("code"));
		return "/view/admin/auth/authC";
	}

	@GetMapping("/viewupdate/{authId}")
	public String viewUpdate(Model model, @PathVariable("authId") BigInteger authId) {
		model.addAttribute("mainInfo", authService.findById(authId));
		model.addAttribute("allUserList", userService.findAll());
		model.addAttribute("codeUseYn", loadStatic.getSystemCode().get("USE_YN").get("code"));
		return "/view/admin/auth/authU";
	}
}
