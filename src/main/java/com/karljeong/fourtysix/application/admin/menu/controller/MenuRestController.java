package com.karljeong.fourtysix.application.admin.menu.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.admin.menu.service.MenuService;
import com.karljeong.fourtysix.database.entity.TbComMenu;

@RestController
@RequestMapping("/v1/api/admin/menu")
public class MenuRestController {
    final MenuService menuService;

	MenuRestController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping
	public List<TbComMenu> readList() {
		return menuService.findAll();
	}

    @PostMapping
    public TbComMenu create(@RequestBody TbComMenu tbComMenu) {
        return menuService.create(tbComMenu);
    }

    @PostMapping("/{menuId}")
    public TbComMenu update(@RequestBody TbComMenu tbComMenu, @PathVariable("menuId") BigInteger menuId) {
        return menuService.update(tbComMenu);
    }

    @DeleteMapping("/{menuId}")
    public void delete(@PathVariable("menuId") BigInteger menuId) {
        menuService.delete(menuId);
    }
}
