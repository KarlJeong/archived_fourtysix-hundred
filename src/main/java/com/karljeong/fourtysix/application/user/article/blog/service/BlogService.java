package com.karljeong.fourtysix.application.user.article.blog.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.application.admin.file.service.FileService;
import com.karljeong.fourtysix.database.entity.TbArticleBlog;
import com.karljeong.fourtysix.database.entity.TbArticleBlogLike;
import com.karljeong.fourtysix.database.entity.TbArticleBlogLikePK;
import com.karljeong.fourtysix.database.entity.TbArticleBlogReply;
import com.karljeong.fourtysix.database.entity.TbArticleGeneralLike;
import com.karljeong.fourtysix.database.entity.TbArticleGeneralReply;
import com.karljeong.fourtysix.database.repository.TbArticleBlogLikeRepository;
import com.karljeong.fourtysix.database.repository.TbArticleBlogReplyRepository;
import com.karljeong.fourtysix.database.repository.TbArticleBlogRepository;
import com.karljeong.fourtysix.database.specification.TbArticleBlogSpec;
import com.karljeong.fourtysix.database.specification.TbArticleBlogSpec.SearchKey;
import com.karljeong.fourtysix.utils.ArticleUtil;

@Service
public class BlogService {
    @Autowired
    FileService fileService;

    @Autowired
    TbArticleBlogRepository tbArticleBlogRepository;

    @Autowired
    TbArticleBlogLikeRepository tbArticleBlogLikeRepository;
    
    @Autowired
    TbArticleBlogReplyRepository tbArticleBlogReplyRepository;

    public Page<TbArticleBlog> readList(Map<String, Object> searchRequest, Pageable pageable) {
        Map<SearchKey, Object> searchKeys = new HashMap<>();

        searchRequest.put("articleDeleteYn", 0);
        searchRequest.put("articleBanYn", 0);

        for (String key : searchRequest.keySet()) {
            if (searchRequest.get(key) != null && !"".equals(searchRequest.get(key))) {
                if (!Arrays.asList(new String[] { "SIZE", "PAGE", "SORT", "ARTICLEID", "REPLYCONTENTS", "PRIORREPLYID",
                        "REPLYDYNAMICCONTENTS" }).contains(key.toUpperCase())) {
                    searchKeys.put(SearchKey.valueOf(key.toUpperCase()), searchRequest.get(key));
                }
            }
        }

        Page<TbArticleBlog> tbArticleBlogList = searchKeys.isEmpty()
                ? tbArticleBlogRepository.findAll(pageable)
                : tbArticleBlogRepository.findAll(TbArticleBlogSpec.searchWithKeys(searchKeys), pageable);

        return tbArticleBlogList;
    }

	public Page<TbArticleBlogReply> readReplyList(BigInteger articleId, int pageNumber) {
		final int pageSize = 20;

		if (pageNumber < 0) {
			int totalCount = tbArticleBlogReplyRepository.countByArticleId(articleId).intValue();
			pageNumber = (totalCount - 1) / pageSize;
		}
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return tbArticleBlogReplyRepository.findByArticleId(articleId, pageable);
	}

    public TbArticleBlog create(TbArticleBlog tbArticleBlog) {
        TbArticleBlog save = tbArticleBlogRepository.save(tbArticleBlog);
        return save;
    }

    public TbArticleBlog update(TbArticleBlog tbArticleBlog) {
        TbArticleBlog save = tbArticleBlogRepository.save(tbArticleBlog);
        return save;
    }

    public TbArticleBlog findById(BigInteger articleId) {
		if (ArticleUtil.readArticleFirst("blog", articleId)) {
			this.updateViewCount(articleId);
		}
		
        return tbArticleBlogRepository.findById(articleId).get();
    }

    public TbArticleBlogLike findById(TbArticleBlogLikePK tbArticleBlogLikePK) {
        Optional<TbArticleBlogLike> tbArticleBlogLike = tbArticleBlogLikeRepository.findById(tbArticleBlogLikePK);

        return tbArticleBlogLike.isPresent() ? tbArticleBlogLike.get() : new TbArticleBlogLike();
    }

	public TbArticleBlogReply reply(TbArticleBlogReply tbArticleBlogReply) {
		TbArticleBlogReply createdTbArticleBlogReply= tbArticleBlogReplyRepository.save(tbArticleBlogReply);
	    this.updateReplyCount(createdTbArticleBlogReply.getArticleId());
		return createdTbArticleBlogReply;
	}

	public int replyDynamic(TbArticleBlogReply tbArticleBlogReply) {
	    int result = tbArticleBlogReplyRepository.saveReplyDynamic(tbArticleBlogReply);
        this.updateReplyCount(tbArticleBlogReply.getArticleId());
		return result;

	}

	public TbArticleBlogLike toggleLike(TbArticleBlogLike tbArticleBlogLike) {
		TbArticleBlogLike createdTbArticleBlogLike = tbArticleBlogLikeRepository.save(tbArticleBlogLike);
	    this.updateLikeCount(createdTbArticleBlogLike.getId().getArticleId());
	    return createdTbArticleBlogLike;
	}

	private void updateLikeCount(BigInteger articleId) {
		tbArticleBlogLikeRepository.saveLikeCount(articleId);
	}

	private void updateReplyCount(BigInteger articleId) {
		tbArticleBlogLikeRepository.saveReplyCount(articleId);
	}

	private void updateViewCount(BigInteger articleId) {
		tbArticleBlogRepository.saveViewCount(articleId);
	}

}
