package com.karljeong.fourtysix.application.admin.blog.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.admin.blog.service.AdminBlogService;
import com.karljeong.fourtysix.application.admin.file.service.FileService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;
import com.karljeong.fourtysix.database.entity.TbArticleBlog;
import com.karljeong.fourtysix.utils.RequestContextUtil;
import com.karljeong.fourtysix.utils.UserUtil;

@Controller
@RequestMapping("/admin/blog")
public class AdminBlogController {

	private final AdminBlogService adminBlogService;
	private final FileService fileService;
	private final LoadStatic loadStatic;

	@Autowired
	AdminBlogController(AdminBlogService adminBlogService, FileService fileService, LoadStatic loadStatic) {
		this.adminBlogService = adminBlogService;
		this.fileService = fileService;
		this.loadStatic = loadStatic;
	}

	@GetMapping("/viewmain")
	public String viewMain(Model model) {
		return "view/admin/blog/adminBlog";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/viewupdate/{articleId}")
	public String viewupdate(Model model, @PathVariable("articleId") BigInteger articleId, HttpServletResponse response)
			throws IOException {
		TbArticleBlog tbArticleBlog = adminBlogService.findById(articleId);
		model.addAttribute("articleInfo", tbArticleBlog);
		model.addAttribute("thumbnailInfo", fileService.getFileInfo(tbArticleBlog.getThumbnailFileId()));

		List<Map<String, Object>> blogArticleCategoryList = (List<Map<String, Object>>) loadStatic.getSystemCode()
				.get("ART_BLOG_CATEGORY").get("code");
		model.addAttribute("blogArticleCategoryList", blogArticleCategoryList);
		return "view/admin/blog/adminBlogU";
	}

}
