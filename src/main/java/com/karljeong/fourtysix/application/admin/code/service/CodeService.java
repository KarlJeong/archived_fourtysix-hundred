package com.karljeong.fourtysix.application.admin.code.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComCode;
import com.karljeong.fourtysix.database.repository.TbComCodeRepository;
import com.karljeong.fourtysix.database.specification.TbComCodeSpec;
import com.karljeong.fourtysix.database.specification.TbComCodeSpec.SearchKey;

@Service
public class CodeService {

	TbComCodeRepository tbComCodeRepository;

	CodeService(TbComCodeRepository tbComCodeRepository) {
		this.tbComCodeRepository = tbComCodeRepository;
	}

    public Page<TbComCode> readList(Map<String, Object> searchRequest, Pageable pageable) {
        Map<SearchKey, Object> searchKeys = new HashMap<>();
        for (String key : searchRequest.keySet()) {
            if (searchRequest.get(key) != null && !"".equals(searchRequest.get(key))) {
                if (!Arrays.asList(new String[] { "SIZE", "PAGE", "SORT" }).contains(key.toUpperCase())) {
                    searchKeys.put(SearchKey.valueOf(key.toUpperCase()), searchRequest.get(key));
                }
            }
        }
        return searchKeys.isEmpty() ? tbComCodeRepository.findAll(pageable)
                : tbComCodeRepository.findAll(TbComCodeSpec.searchWithKeys(searchKeys), pageable);
    }

	public TbComCode findById(Long codeId) {
		return tbComCodeRepository.findById(codeId).get();
	}
}
