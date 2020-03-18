package com.karljeong.fourtysix.application.admin.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.admin.menu.service.MenuService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;

@Controller
@RequestMapping("/admin/menu")
public class MenuController {
	final MenuService menuService;
	final LoadStatic loadStatic;

	MenuController(MenuService menuService, LoadStatic loadStatic) {
		this.menuService = menuService;
		this.loadStatic = loadStatic;
	}

	@GetMapping("/viewmain")
	public String viewMain(Model model) {
		model.addAttribute("codeMenuType", loadStatic.getSystemCode().get("MENU_TYPE").get("code"));
		model.addAttribute("codeUseYn", loadStatic.getSystemCode().get("USE_YN").get("code"));
		return "/view/admin/menu/menuCRU";
	}

}
