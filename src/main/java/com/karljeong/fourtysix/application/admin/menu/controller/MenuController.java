package com.karljeong.fourtysix.application.admin.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.admin.menu.service.MenuService;

@Controller
@RequestMapping("/admin/menu")
public class MenuController {
	final MenuService menuService;

	MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping("/viewmain")
	public String viewMain() {
		return "/view/admin/menu/menuCRU";
	}

}
