package com.karljeong.fourtysix.application.admin.codeGroup.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComCodeGroup;
import com.karljeong.fourtysix.database.repository.TbComCodeGroupRepository;
import com.karljeong.fourtysix.database.repository.TbComCodeRepository;
import com.karljeong.fourtysix.database.specification.TbComCodeGroupSpec;
import com.karljeong.fourtysix.database.specification.TbComCodeGroupSpec.SearchKey;

@Service
public class CodeGroupService {

	TbComCodeGroupRepository tbComCodeGroupRepository;
	TbComCodeRepository tbComCodeRepository;

	CodeGroupService(TbComCodeGroupRepository tbComCodeGroupRepository, TbComCodeRepository tbComCodeRepository) {
		this.tbComCodeGroupRepository = tbComCodeGroupRepository;
		this.tbComCodeRepository = tbComCodeRepository;
	}

	public Page<TbComCodeGroup> readList(Map<String, Object> searchRequest, Pageable pageable) {
		Map<SearchKey, Object> searchKeys = new HashMap<>();
		for (String key : searchRequest.keySet()) {
			if (searchRequest.get(key) != null && !"".equals(searchRequest.get(key))) {
				if (!Arrays.asList(new String[] { "SIZE", "PAGE", "SORT" }).contains(key.toUpperCase())) {
					searchKeys.put(SearchKey.valueOf(key.toUpperCase()), searchRequest.get(key));
				}
			}
		}
		Page<TbComCodeGroup> comCodeGroupList = searchKeys.isEmpty() ? tbComCodeGroupRepository.findAll(pageable)
				: tbComCodeGroupRepository.findAll(TbComCodeGroupSpec.searchWithKeys(searchKeys), pageable);

		for (TbComCodeGroup tbComCodeGroup : comCodeGroupList) {
			tbComCodeGroup.setTbComCodes(tbComCodeRepository.findByCodeGroupId(tbComCodeGroup.getCodeGroupId()));
		}

		return comCodeGroupList;
	}

	public TbComCodeGroup create(TbComCodeGroup tbComCodeGroup) {
		tbComCodeGroup.setCreateUserId(BigInteger.valueOf(11111));
		TbComCodeGroup save = tbComCodeGroupRepository.save(tbComCodeGroup);
		BigInteger[] codeIds = tbComCodeGroup.getCodeIds(); // FIXME: 기존 코드 -> tbComCodeGroup.getTbComCodes()
		for (BigInteger codeId : codeIds) {
			tbComCodeRepository.saveCodeGroupId(new BigInteger("000000"), codeId, tbComCodeGroup.getCodeGroupId());
		}
		return save;
	}

	public TbComCodeGroup findById(BigInteger codeGroupId) {
		TbComCodeGroup tbComCodeGroup = tbComCodeGroupRepository.findById(codeGroupId);
		tbComCodeGroup.setTbComCodes(tbComCodeRepository.findByCodeGroupId(codeGroupId));
		return tbComCodeGroup;
	}
}
