package com.karljeong.fourtysix.application.user.article.blog.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.user.article.blog.service.BlogService;
import com.karljeong.fourtysix.database.entity.TbArticleBlog;
import com.karljeong.fourtysix.database.entity.TbArticleBlogLike;
import com.karljeong.fourtysix.database.entity.TbArticleBlogLikePK;
import com.karljeong.fourtysix.database.entity.TbArticleBlogReply;
import com.karljeong.fourtysix.database.entity.TbArticleGeneralLike;
import com.karljeong.fourtysix.database.entity.TbArticleGeneralLikePK;
import com.karljeong.fourtysix.database.entity.TbArticleGeneralReply;
import com.karljeong.fourtysix.resulthandler.ResultDto;
import com.karljeong.fourtysix.resulthandler.ResultDto.ResultCodeEnum;
import com.karljeong.fourtysix.utils.PagingUtil;
import com.karljeong.fourtysix.resulthandler.ResultSetter;

@RestController
@RequestMapping("/v1/api/blog")
public class BlogRestController {

    @Autowired
    BlogService blogService;

    @GetMapping
    public Iterable<TbArticleBlog> getBlogs(@RequestBody TbArticleBlog tbArticleBlog) {
        return null;
    }

	@GetMapping("/{articleId}/reply/{pageNumber}")
	public ResultDto readReplyList(@PathVariable("articleId") BigInteger articleId,
			@PathVariable("pageNumber") int pageNumber) {
		Page<TbArticleBlogReply> retrievedTbArticleBlogReply = blogService.readReplyList(articleId,
				pageNumber);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("replyList", retrievedTbArticleBlogReply);
		data.put("paging", PagingUtil.getPageList(retrievedTbArticleBlogReply.getTotalPages(),
				retrievedTbArticleBlogReply.getNumber()));

		return new ResultSetter(ResultCodeEnum.SUCCESS, data).getResultDto();
	}

    @PostMapping
    public ResultDto create(@RequestBody TbArticleBlog tbArticleBlog, HttpServletRequest request) {
        tbArticleBlog.setUserInfo(request);
        TbArticleBlog createTbArticleBlog = blogService.create(tbArticleBlog);
        return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT_ALERT, "Saved Successfully", createTbArticleBlog,
                "/blog/viewupdate/" + createTbArticleBlog.getArticleId()).getResultDto();
    }

    @PostMapping("/{articleId}")
    public ResultDto update(@RequestBody TbArticleBlog tbArticleBlog, HttpServletRequest request) {
        tbArticleBlog.setUserInfo(request);
        TbArticleBlog createTbArticleBlog = blogService.update(tbArticleBlog);
        return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT_ALERT, "Saved Successfully", createTbArticleBlog,
                "/blog/viewupdate/" + createTbArticleBlog.getArticleId()).getResultDto();
    }

	@PostMapping("/{articleId}/reply")
	public ResultDto reply(@RequestBody TbArticleBlogReply tbArticleBlogReply,
			@PathVariable("articleId") BigInteger articleId, HttpServletRequest request) {
		tbArticleBlogReply.setUserInfo(request);
		tbArticleBlogReply.setArticleId(articleId);
		TbArticleBlogReply createTbArticleReplyGeneral = blogService.reply(tbArticleBlogReply);
		return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT_ALERT, "Saved Successfully", createTbArticleReplyGeneral,
				"/blog/viewdetail/" + createTbArticleReplyGeneral.getArticleId()).getResultDto();
	}

	@PostMapping("/{articleId}/replydynamic")
	public ResultDto replyDynamic(@RequestBody TbArticleBlogReply tbArticleBlogReply,
			@PathVariable("articleId") BigInteger articleId, HttpServletRequest request) {
		tbArticleBlogReply.setUserInfo(request);
		tbArticleBlogReply.setArticleId(articleId);
		int createTbArticleReplyGeneral = blogService.replyDynamic(tbArticleBlogReply);
		return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT_ALERT, "Saved Successfully", createTbArticleReplyGeneral,
				"/blog/viewdetail/" + tbArticleBlogReply.getArticleId()).getResultDto();
	}

	@PostMapping("/{articleId}/like/{likeYn}")
	public ResultDto toggleLike(@PathVariable("articleId") BigInteger articleId, @PathVariable("likeYn") byte likeYn, HttpServletRequest request) {
		TbArticleBlogLike tbArticleBlogLike = new TbArticleBlogLike();
		TbArticleBlogLikePK tbArticleGeneralLikePK = new TbArticleBlogLikePK();
		tbArticleBlogLike.setUserInfo(request);
		tbArticleBlogLike.setLikeYn(likeYn);
		tbArticleGeneralLikePK.setArticleId(articleId);
		tbArticleGeneralLikePK.setUserInfo(request);
		tbArticleBlogLike.setId(tbArticleGeneralLikePK);
		TbArticleBlogLike createdtbArticleBlogLike = blogService.toggleLike(tbArticleBlogLike);
		return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT_ALERT, "Saved Successfully", createdtbArticleBlogLike,
                "/blog/viewdetail/" + articleId).getResultDto();
	}
}
