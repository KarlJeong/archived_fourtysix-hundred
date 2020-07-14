package com.karljeong.fourtysix.application.user.mypage.myarticle.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.application.user.article.article.service.ArticleService;
import com.karljeong.fourtysix.application.user.article.blog.service.BlogService;
import com.karljeong.fourtysix.application.user.article.general.service.GeneralService;
import com.karljeong.fourtysix.database.entity.TbArticle;
import com.karljeong.fourtysix.database.entity.TbArticleBlog;
import com.karljeong.fourtysix.database.entity.TbArticleGeneral;

@Service
public class MyArticleService {

	private final ArticleService articleService;
	private final BlogService blogService;
	private final GeneralService generalService;

	@Autowired
	MyArticleService(ArticleService articleService, BlogService blogService, GeneralService generalService) {
		this.articleService = articleService;
		this.blogService = blogService;
		this.generalService = generalService;
	}

	public Page<TbArticle> readList(Map<String, Object> searchRequest, Pageable pageable) {
		Page<TbArticle> myArticleList = articleService.readList(searchRequest, pageable);
		for (TbArticle tbArticle : myArticleList) {
			if ("BLOG".equals(tbArticle.getArticleCode())) {
				TbArticleBlog tbArticleBlog = blogService.findById(tbArticle.getArticleId());
				setAdditionalArticleInfo(tbArticle, tbArticleBlog.getArticleTitle(),
						tbArticleBlog.getArticleLikeCount(), tbArticleBlog.getArticleReplyCount(),
						tbArticleBlog.getArticleViewCount(), tbArticleBlog.getArticleCategoryCv());
			} else if ("GENERAL".equals(tbArticle.getArticleCode())) {
				TbArticleGeneral tbArticleGeneral = generalService.findById(tbArticle.getArticleId());
				setAdditionalArticleInfo(tbArticle, tbArticleGeneral.getArticleTitle(),
						tbArticleGeneral.getArticleLikeCount(), tbArticleGeneral.getArticleReplyCount(),
						tbArticleGeneral.getArticleViewCount(), tbArticleGeneral.getArticleCategoryCv());
			}
		}
		return myArticleList;
	}

	private void setAdditionalArticleInfo(TbArticle tbArticle, String articleTitle, int articleLikeCount,
			int articleReplyCount, int articleViewCount, String articleCategoryCv) {
		tbArticle.setArticleTitle(articleTitle);
		tbArticle.setArticleLikeCount(articleLikeCount);
		tbArticle.setArticleReplyCount(articleReplyCount);
		tbArticle.setArticleViewCount(articleViewCount);
		tbArticle.setArticleCategoryCv(articleCategoryCv);
	}

}
