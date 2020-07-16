package com.karljeong.fourtysix.application.admin.blog.service;

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
import org.springframework.transaction.annotation.Transactional;

import com.karljeong.fourtysix.application.admin.file.service.FileService;
import com.karljeong.fourtysix.application.user.article.article.service.ArticleService;
import com.karljeong.fourtysix.database.entity.TbArticleBlog;
import com.karljeong.fourtysix.database.entity.TbArticleBlogLike;
import com.karljeong.fourtysix.database.entity.TbArticleBlogLikePK;
import com.karljeong.fourtysix.database.entity.TbArticleBlogReply;
import com.karljeong.fourtysix.database.repository.TbArticleBlogLikeRepository;
import com.karljeong.fourtysix.database.repository.TbArticleBlogReplyRepository;
import com.karljeong.fourtysix.database.repository.TbArticleBlogRepository;
import com.karljeong.fourtysix.database.specification.TbArticleBlogSpec;
import com.karljeong.fourtysix.database.specification.TbArticleBlogSpec.SearchKey;
import com.karljeong.fourtysix.utils.ArticleUtil;

@Service
public class AdminBlogService {

	@Autowired
    FileService fileService;
    
    @Autowired
    ArticleService articleService;

    @Autowired
    TbArticleBlogRepository tbArticleBlogRepository;

    @Autowired
    TbArticleBlogLikeRepository tbArticleBlogLikeRepository;
    
    @Autowired
    TbArticleBlogReplyRepository tbArticleBlogReplyRepository;

    public Page<TbArticleBlog> readList(Map<String, Object> searchRequest, Pageable pageable) {
        Map<SearchKey, Object> searchKeys = new HashMap<>();

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

    public TbArticleBlog findById(BigInteger articleId) {
        return tbArticleBlogRepository.findById(articleId).get();
    }

	public int updatePublish(BigInteger articleId, int publishYn) {
		return tbArticleBlogRepository.updatePublish(articleId, publishYn);
	}

}
