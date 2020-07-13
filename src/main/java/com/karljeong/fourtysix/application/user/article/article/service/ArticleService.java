package com.karljeong.fourtysix.application.user.article.article.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbArticle;
import com.karljeong.fourtysix.database.entity.TbArticleBlog;
import com.karljeong.fourtysix.database.entity.TbArticleGeneral;
import com.karljeong.fourtysix.database.repository.TbArticleRepository;
import com.karljeong.fourtysix.database.specification.TbArticleSpec;
import com.karljeong.fourtysix.database.specification.TbArticleSpec.SearchKey;

@Service("articleCommonService")
public class ArticleService {

    @Autowired
    TbArticleRepository tbArticleRepository;

    public Page<TbArticle> readList(Map<String, Object> searchRequest, Pageable pageable) {
        Map<SearchKey, Object> searchKeys = new HashMap<>();

        for (String key : searchRequest.keySet()) {
            if (searchRequest.get(key) != null && !"".equals(searchRequest.get(key))) {
                if (!Arrays.asList(new String[] { "SIZE", "PAGE", "SORT"}).contains(key.toUpperCase())) {
                    searchKeys.put(SearchKey.valueOf(key.toUpperCase()), searchRequest.get(key));
                }
            }
        }

        Page<TbArticle> tbArticleBlogList = searchKeys.isEmpty()
                ? tbArticleRepository.findAll(pageable)
                : tbArticleRepository.findAll(TbArticleSpec.searchWithKeys(searchKeys), pageable);

        return tbArticleBlogList;
    }
    
    public List<HashMap<String, Object>> getArticleCodes(BigInteger userId) {
    	List<Object[]> result = tbArticleRepository.getArticleCodes(userId);
    	List<HashMap<String, Object>> codeList = new ArrayList<HashMap<String, Object>>();
    	for (Object[] o : result) {
    		System.out.println(o[0]);
    		System.out.println(o[1]);
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("articleCode", (String) o[0]);
			m.put("articleCodeCount", ((BigInteger) o[1]).intValue());
			codeList.add(m);
    	}
    	return codeList;
    }

    public TbArticle create(TbArticle tbArticle) {
    	return tbArticleRepository.save(tbArticle);
    }

    public TbArticle update(TbArticle tbArticle) {
    	TbArticle retrivedArticle = tbArticleRepository.findByArticleIdAndArticleCode(tbArticle.getArticleId(), tbArticle.getArticleCode());
    	if (retrivedArticle != null) {
    		tbArticle.setId(retrivedArticle.getId());
    	}
    	return tbArticleRepository.save(tbArticle);
    }
    
    public TbArticle convertToTbArticle(TbArticleGeneral tbArticleGeneral) {
    	TbArticle tbArticle = new TbArticle();
    	tbArticle.setArticleId(tbArticleGeneral.getArticleId());
    	tbArticle.setArticleCode("GENERAL");
    	tbArticle.setArticleWriterId(tbArticleGeneral.getArticleWriterId());
    	tbArticle.setArticleWriteDatetime(tbArticleGeneral.getArticleWriteDatetime());
    	tbArticle.setArticleModifierId(tbArticleGeneral.getArticleModifierId());
    	tbArticle.setArticleModifyDatetime(tbArticleGeneral.getArticleModifyDatetime());
    	tbArticle.setCreateUserId(tbArticleGeneral.getCreateUserId());
    	tbArticle.setCreateDatetime(tbArticleGeneral.getCreateDatetime());
    	return tbArticle;
    }
    
    public TbArticle convertToTbArticle(TbArticleBlog tbArticleBlog) {
    	TbArticle tbArticle = new TbArticle();
    	tbArticle.setArticleId(tbArticleBlog.getArticleId());
    	tbArticle.setArticleCode("BLOG");
    	tbArticle.setArticleWriterId(tbArticleBlog.getArticleWriterId());
    	tbArticle.setArticleWriteDatetime(tbArticleBlog.getArticleWriteDatetime());
    	tbArticle.setArticleModifierId(tbArticleBlog.getArticleModifierId());
    	tbArticle.setArticleModifyDatetime(tbArticleBlog.getArticleModifyDatetime());
    	tbArticle.setCreateUserId(tbArticleBlog.getCreateUserId());
    	tbArticle.setCreateDatetime(tbArticleBlog.getCreateDatetime());
    	return tbArticle;
    }
}
