package com.karljeong.fourtysix.application.article.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComArticle;
import com.karljeong.fourtysix.database.repository.TbComArticleRepository;
import com.karljeong.fourtysix.database.specification.TbComArticleSpec;
import com.karljeong.fourtysix.database.specification.TbComArticleSpec.SearchKey;

@Service
public class ArticleService {

    private final TbComArticleRepository tbComArticleRepository;

    @Autowired
    ArticleService(TbComArticleRepository tbComArticleRepository) {
        this.tbComArticleRepository = tbComArticleRepository;
    }

    public Page<TbComArticle> readList(Map<String, Object> searchRequest, Pageable pageable) {
        Map<SearchKey, Object> searchKeys = new HashMap<>();
        for (String key : searchRequest.keySet()) {
            if (searchRequest.get(key) != null && !"".equals(searchRequest.get(key))) {
                if (!Arrays.asList(new String[] { "SIZE", "PAGE", "SORT" }).contains(key.toUpperCase())) {
                    searchKeys.put(SearchKey.valueOf(key.toUpperCase()), searchRequest.get(key));
                }
            }
        }

        Page<TbComArticle> tbComArticleList = searchKeys.isEmpty() ? tbComArticleRepository.findAll(pageable)
                : tbComArticleRepository.findAll(TbComArticleSpec.searchWithKeys(searchKeys), pageable);

        return tbComArticleList;
    }

    public TbComArticle findById(BigInteger articleId) {
        return tbComArticleRepository.findById(articleId).get();
    }

    public TbComArticle create(TbComArticle tbComArticle) {
        tbComArticle.setCreateUserId(BigInteger.valueOf(11111));
        tbComArticle.setArticleWriterId(BigInteger.valueOf(11111));
        return tbComArticleRepository.save(tbComArticle);

    }

}
