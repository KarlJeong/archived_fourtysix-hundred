package com.karljeong.fourtysix.application.admin.codeGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/codegroup")
public class CodeGroupController {

    @GetMapping("/viewmain")
    public String viewMain() {
        return "/view/admin/codeGroup/codeGroup";
    }

    @GetMapping("/viewcreate")
    public String viewCreate() {
        return "/view/admin/codeGroup/codeGroupC";
    }

    @GetMapping("/viewupdate")
    public String viewUpdate() {
        return "/view/admin/codeGroup/codeGroupU";
    }

}
