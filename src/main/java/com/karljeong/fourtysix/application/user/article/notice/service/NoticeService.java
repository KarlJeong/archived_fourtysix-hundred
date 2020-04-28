package com.karljeong.fourtysix.application.user.article.notice.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComArticle;
import com.karljeong.fourtysix.database.repository.TbComArticleRepository;
import com.karljeong.fourtysix.database.repository.TbComBoardRepository;
import com.karljeong.fourtysix.database.specification.TbComArticleSpec;
import com.karljeong.fourtysix.database.specification.TbComArticleSpec.SearchKey;

@Service
public class NoticeService {

    private final TbComArticleRepository tbComArticleRepository;
    private final TbComBoardRepository tbComBoardRepository;

    @Autowired
    NoticeService(TbComArticleRepository tbComArticleRepository, TbComBoardRepository tbComBoardRepository) {
        this.tbComArticleRepository = tbComArticleRepository;
        this.tbComBoardRepository = tbComBoardRepository;
    }

    public Page<TbComArticle> readList(Map<String, Object> searchRequest, Pageable pageable) {
        Map<SearchKey, Object> searchKeys = new HashMap<>();

        searchRequest.put("articleDeleteYn", 0);
        searchRequest.put("articlePublishYn", 1);
        searchRequest.put("boardId", 14);

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

    public List<TbComArticle> findArticyeForDashboardByBoardCode(String boardCode){
        Pageable pageable = PageRequest.of(0, 3);
        return tbComArticleRepository.findArticyeForDashboardByBoardCode(boardCode, pageable);
    }



}
