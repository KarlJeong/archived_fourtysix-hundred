package com.karljeong.fourtysix.application.policy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/policy")
public class PolicyController {

	@GetMapping("/privacy")
	public String viewMain(Model model) {
		return "view/policy/Privacy";
	}

}
