package com.karljeong.fourtysix.application.user.article.general.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	GeneralService(TbArticleGeneralRepository tbArticleGeneralRepository,
			TbArticleGeneralReplyRepository tbArticleGeneralReplyRepository) {
		this.tbArticleGeneralRepository = tbArticleGeneralRepository;
		this.tbArticleGeneralReplyRepository = tbArticleGeneralReplyRepository;
	}

	public Page<TbArticleGeneral> readList(Map<String, Object> searchRequest, Pageable pageable) {
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

		Page<TbArticleGeneral> tbArticleGeneralList = searchKeys.isEmpty()
				? tbArticleGeneralRepository.findAll(pageable)
				: tbArticleGeneralRepository.findAll(TbArticleGeneralSpec.searchWithKeys(searchKeys), pageable);

		for (TbArticleGeneral tbArticleGeneral : tbArticleGeneralList) {
			tbArticleGeneral.setArticleWriterUserName(
					tbArticleGeneralRepository.findArticleWriterName(tbArticleGeneral.getArticleWriterId()));
		}

		return tbArticleGeneralList;
	}

	public Page<TbArticleGeneralReply> readReplyList(BigInteger articleId, int pageNumber) {
		final int pageSize = 20;
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<TbArticleGeneralReply> retrievedReplyList = tbArticleGeneralReplyRepository.findByArticleId(articleId,
				pageable);
		for (TbArticleGeneralReply tbArticleGeneralReply : retrievedReplyList) {
			tbArticleGeneralReply.setReplyWriterUserName(
					tbArticleGeneralReplyRepository.findReplyWriterName(tbArticleGeneralReply.getReplyWriterId()));

		}
		return tbArticleGeneralReplyRepository.findByArticleId(articleId, pageable);
	}

	public TbArticleGeneral findById(BigInteger articleId) {
		TbArticleGeneral tbArticleGeneral = tbArticleGeneralRepository.findById(articleId).get();
		tbArticleGeneral.setArticleWriterUserName(
				tbArticleGeneralRepository.findArticleWriterName(tbArticleGeneral.getArticleWriterId()));
		return tbArticleGeneral;
	}

	public TbArticleGeneral create(TbArticleGeneral tbArticleGeneral) {
		return tbArticleGeneralRepository.save(tbArticleGeneral);

	}

	public TbArticleGeneral update(TbArticleGeneral tbArticleGeneral) {
		return tbArticleGeneralRepository.save(tbArticleGeneral);

	}

	public TbArticleGeneralReply reply(TbArticleGeneralReply tbArticleGeneralReply) {
		return tbArticleGeneralReplyRepository.save(tbArticleGeneralReply);

	}

	public int replyDynamic(TbArticleGeneralReply tbArticleGeneralReply) {
		return tbArticleGeneralReplyRepository.saveReplyDynamic(tbArticleGeneralReply);

	}

}
