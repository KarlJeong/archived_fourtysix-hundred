package com.karljeong.fourtysix.application.admin.menu.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.admin.menu.service.MenuService;
import com.karljeong.fourtysix.database.entity.TbComMenu;
import com.karljeong.fourtysix.resulthandler.ResultDto;
import com.karljeong.fourtysix.resulthandler.ResultSetter;
import com.karljeong.fourtysix.resulthandler.ResultDto.ResultCodeEnum;

@RestController
@RequestMapping("/v1/api/admin/menu")
public class MenuRestController {
    private final MenuService menuService;

    @Autowired
	MenuRestController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping
	public ResultDto readList() {
	    return new ResultSetter(ResultCodeEnum.SUCCESS, null, menuService.findAll(), null).getResultDto();
	}

    @PostMapping
    public ResultDto create(@RequestBody TbComMenu tbComMenu) {
        TbComMenu createdTbComMenu =  menuService.create(tbComMenu);
        return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT_ALERT, "Saved Successfully", createdTbComMenu, "/admin/menu/viewmain").getResultDto();
    }

    @PostMapping("/{menuId}")
    public ResultDto update(@RequestBody TbComMenu tbComMenu, @PathVariable("menuId") BigInteger menuId) {
        TbComMenu updatedTbComMenu =  menuService.update(tbComMenu);
        return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT_ALERT, "Modified Successfully", updatedTbComMenu, "/admin/menu/viewmain").getResultDto();
    }

    @DeleteMapping("/{menuId}")
    public ResultDto delete(@PathVariable("menuId") BigInteger menuId) {
        menuService.delete(menuId);
        return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT_ALERT, "Deleted Successfully", null, "/admin/menu/viewmain").getResultDto();

    }
}
