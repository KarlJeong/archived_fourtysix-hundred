package com.karljeong.fourtysix.application.user.article.general.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbArticleGeneral;
import com.karljeong.fourtysix.database.entity.TbArticleGeneralLike;
import com.karljeong.fourtysix.database.entity.TbArticleGeneralLikePK;
import com.karljeong.fourtysix.database.entity.TbArticleGeneralReply;
import com.karljeong.fourtysix.database.repository.TbArticleGeneralLikeRepository;
import com.karljeong.fourtysix.database.repository.TbArticleGeneralReplyRepository;
import com.karljeong.fourtysix.database.repository.TbArticleGeneralRepository;
import com.karljeong.fourtysix.database.specification.TbArticleGeneralSpec;
import com.karljeong.fourtysix.database.specification.TbArticleGeneralSpec.SearchKey;
import com.karljeong.fourtysix.utils.ArticleUtil;

@Service
public class GeneralService {

	private final TbArticleGeneralRepository tbArticleGeneralRepository;
	private final TbArticleGeneralReplyRepository tbArticleGeneralReplyRepository;
	private final TbArticleGeneralLikeRepository tbArticleGeneralLikeRepository;

	@Autowired
	GeneralService(TbArticleGeneralRepository tbArticleGeneralRepository,
			TbArticleGeneralReplyRepository tbArticleGeneralReplyRepository,
			TbArticleGeneralLikeRepository tbArticleGeneralLikeRepository) {
		this.tbArticleGeneralRepository = tbArticleGeneralRepository;
		this.tbArticleGeneralReplyRepository = tbArticleGeneralReplyRepository;
		this.tbArticleGeneralLikeRepository = tbArticleGeneralLikeRepository;
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

		return tbArticleGeneralList;
	}

	public Page<TbArticleGeneralReply> readReplyList(BigInteger articleId, int pageNumber) {
		final int pageSize = 20;

		if (pageNumber < 0) {
			int totalCount = tbArticleGeneralReplyRepository.countByArticleId(articleId).intValue();
			pageNumber = (totalCount - 1) / pageSize;
		}
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return tbArticleGeneralReplyRepository.findByArticleId(articleId, pageable);
	}

	public TbArticleGeneral findById(BigInteger articleId, HttpServletRequest request) {
		if (ArticleUtil.readArticleFirst("blog", articleId)) {
			this.updateViewCount(articleId);
		}
		return tbArticleGeneralRepository.findById(articleId).get();
	}

	public TbArticleGeneralLike findById(TbArticleGeneralLikePK tbArticleGeneralLikePK) {
		Optional<TbArticleGeneralLike> tbArticleGeneralLike = tbArticleGeneralLikeRepository
				.findById(tbArticleGeneralLikePK);

		return tbArticleGeneralLike.isPresent() ? tbArticleGeneralLike.get() : new TbArticleGeneralLike();
	}

	public TbArticleGeneral create(TbArticleGeneral tbArticleGeneral) {
		String articleContents = tbArticleGeneral.getArticleContents();
		Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
        Matcher matcher = pattern.matcher(articleContents);
         
        if(matcher.find()){
        	tbArticleGeneral.setContainImage((byte) 1);
        }

		return tbArticleGeneralRepository.save(tbArticleGeneral);

	}

	public TbArticleGeneral update(TbArticleGeneral tbArticleGeneral) {
		return tbArticleGeneralRepository.save(tbArticleGeneral);

	}

	public TbArticleGeneralReply reply(TbArticleGeneralReply tbArticleGeneralReply) {
	    TbArticleGeneralReply createdTbArticleGeneralReply= tbArticleGeneralReplyRepository.save(tbArticleGeneralReply);
	    this.updateReplyCount(createdTbArticleGeneralReply.getArticleId());
		return createdTbArticleGeneralReply;

	}

	public int replyDynamic(TbArticleGeneralReply tbArticleGeneralReply) {
	    int result = tbArticleGeneralReplyRepository.saveReplyDynamic(tbArticleGeneralReply);
	    
	    
        this.updateReplyCount(tbArticleGeneralReply.getArticleId());
		return result;

	}

	public TbArticleGeneralLike toggleLike(TbArticleGeneralLike tbArticleGeneralLike) {
	    TbArticleGeneralLike createdTbArticleGeneralLike = tbArticleGeneralLikeRepository.save(tbArticleGeneralLike);
	    this.updateLikeCount(createdTbArticleGeneralLike.getId().getArticleId());
	    return createdTbArticleGeneralLike;
	}

	private void updateLikeCount(BigInteger articleId) {
	    tbArticleGeneralRepository.saveLikeCount(articleId);
	}

	private void updateReplyCount(BigInteger articleId) {
	    tbArticleGeneralRepository.saveReplyCount(articleId);
	}

	private void updateViewCount(BigInteger articleId) {
        tbArticleGeneralRepository.saveViewCount(articleId);
	}

}
