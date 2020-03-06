package com.karljeong.fourtysix.application.admin.codeGroup.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComCodeGroup;
import com.karljeong.fourtysix.database.repository.TbComCodeGroupRepository;
import com.karljeong.fourtysix.database.specification.TbComCodeGroupSpec;
import com.karljeong.fourtysix.database.specification.TbComCodeGroupSpec.SearchKey;

@Service
public class CodeGroupService {

	TbComCodeGroupRepository tbComCodeGroupRepository;

	CodeGroupService(TbComCodeGroupRepository tbComCodeGroupRepository) {
		this.tbComCodeGroupRepository = tbComCodeGroupRepository;
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
		return searchKeys.isEmpty() ? tbComCodeGroupRepository.findAll(pageable)
				: tbComCodeGroupRepository.findAll(TbComCodeGroupSpec.searchWithKeys(searchKeys), pageable);
	}

	public TbComCodeGroup create(TbComCodeGroup tbComCodeGroup) {
		tbComCodeGroup.setCreateUserId(Long.valueOf(11111));
		TbComCodeGroup save = tbComCodeGroupRepository.save(tbComCodeGroup);
		return save;
	}

	public TbComCodeGroup findById(Long codeGroupId) {
		return tbComCodeGroupRepository.findById(codeGroupId).get();
	}
}
