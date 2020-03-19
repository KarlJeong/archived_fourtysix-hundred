package com.karljeong.fourtysix.application.admin.category.diary.controller;

import java.math.BigInteger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @GetMapping("/viewmain")
    public String viewList() {
        return "view/admin/category/auth";
    }

    @GetMapping("/viewcreate")
    public String viewCreate() {
        return "view/admin/category/authC";
    }

    @GetMapping("/viewupdate/{categoryId}")
    public String viewUpdate(@PathVariable BigInteger categoryId) {
        return "view/admin/category/authU";
    }
}
