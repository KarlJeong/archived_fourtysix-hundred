package com.karljeong.fourtysix.application.user.article.blog.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.application.admin.file.service.FileService;
import com.karljeong.fourtysix.database.entity.TbArticleBlog;
import com.karljeong.fourtysix.database.entity.TbArticleBlogLike;
import com.karljeong.fourtysix.database.entity.TbArticleBlogLikePK;
import com.karljeong.fourtysix.database.repository.TbArticleBlogLikeRepository;
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

    public TbArticleBlog getBlog(String articleId) {
        return tbArticleBlogRepository.findById(new BigInteger(articleId)).orElseGet(() -> new TbArticleBlog());
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

	private void updateViewCount(BigInteger articleId) {
		tbArticleBlogRepository.saveViewCount(articleId);
	}

}
