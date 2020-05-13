package com.karljeong.fourtysix.application.user.article.general.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbArticleGeneral;
import com.karljeong.fourtysix.database.entity.TbArticleGeneralReply;
import com.karljeong.fourtysix.database.repository.TbArticleGeneralReplyRepository;
import com.karljeong.fourtysix.database.repository.TbArticleGeneralRepository;
import com.karljeong.fourtysix.database.specification.TbArticleGeneralSpec;
import com.karljeong.fourtysix.database.specification.TbArticleGeneralSpec.SearchKey;

@Service
public class GeneralService {

	private final TbArticleGeneralRepository tbArticleGeneralRepository;
	private final TbArticleGeneralReplyRepository tbArticleGeneralReplyRepository;

	@Autowired
	GeneralService(TbArticleGeneralRepository tbArticleGeneralRepository, TbArticleGeneralReplyRepository tbArticleGeneralReplyRepository) {
		this.tbArticleGeneralRepository = tbArticleGeneralRepository;
		this.tbArticleGeneralReplyRepository = tbArticleGeneralReplyRepository;
	}

	public Page<TbArticleGeneral> readList(Map<String, Object> searchRequest, Pageable pageable) {
		Map<SearchKey, Object> searchKeys = new HashMap<>();

		searchRequest.put("articleDeleteYn", 0);
		searchRequest.put("articleBanYn", 0);

		for (String key : searchRequest.keySet()) {
			if (searchRequest.get(key) != null && !"".equals(searchRequest.get(key))) {
				if (!Arrays.asList(new String[] { "SIZE", "PAGE", "SORT" }).contains(key.toUpperCase())) {
					searchKeys.put(SearchKey.valueOf(key.toUpperCase()), searchRequest.get(key));
				}
			}
		}

		Page<TbArticleGeneral> tbArticleGeneralList = searchKeys.isEmpty()
				? tbArticleGeneralRepository.findAll(pageable)
				: tbArticleGeneralRepository.findAll(TbArticleGeneralSpec.searchWithKeys(searchKeys), pageable);

		for (TbArticleGeneral tbArticleGeneral : tbArticleGeneralList) {
			tbArticleGeneral.setArticleWriterUserName(
					tbArticleGeneralRepository.findArticleWriterName(tbArticleGeneral.getArticleWriterId()));
		}

		return tbArticleGeneralList;
	}

	public TbArticleGeneral findById(BigInteger articleId) {
		TbArticleGeneral tbArticleGeneral = tbArticleGeneralRepository.findById(articleId).get();
		tbArticleGeneral.setArticleWriterUserName(
				tbArticleGeneralRepository.findArticleWriterName(tbArticleGeneral.getArticleWriterId()));
		return tbArticleGeneral;
	}

	public TbArticleGeneral create(TbArticleGeneral tbArticleGeneral) {
		tbArticleGeneral.setCreateUserId(BigInteger.valueOf(1));
		tbArticleGeneral.setArticleWriterId(BigInteger.valueOf(1));
		return tbArticleGeneralRepository.save(tbArticleGeneral);

	}

	public TbArticleGeneral update(TbArticleGeneral tbArticleGeneral) {
		tbArticleGeneral.setUpdateUserId(BigInteger.valueOf(1));
		tbArticleGeneral.setArticleModifierId(BigInteger.valueOf(1));
		return tbArticleGeneralRepository.save(tbArticleGeneral);

	}

    public TbArticleGeneralReply reply(TbArticleGeneralReply tbArticleGeneralReply) {
        tbArticleGeneralReply.setCreateUserId(BigInteger.valueOf(1));
        tbArticleGeneralReply.setReplyWriterId(BigInteger.valueOf(1));
        return tbArticleGeneralReplyRepository.save(tbArticleGeneralReply);

    }

}
