package com.karljeong.fourtysix.application.admin.code.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComCode;
import com.karljeong.fourtysix.database.repository.TbComCodeGroupRepository;
import com.karljeong.fourtysix.database.repository.TbComCodeRepository;
import com.karljeong.fourtysix.database.specification.TbComCodeSpec;
import com.karljeong.fourtysix.database.specification.TbComCodeSpec.SearchKey;

@Service
public class CodeService {

	TbComCodeRepository tbComCodeRepository;
	TbComCodeGroupRepository tbComCodeGroupRepository;

	CodeService(TbComCodeRepository tbComCodeRepository, TbComCodeGroupRepository tbComCodeGroupRepository) {
		this.tbComCodeRepository = tbComCodeRepository;
		this.tbComCodeGroupRepository = tbComCodeGroupRepository;
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

		Page<TbComCode> tbComCodeList = searchKeys.isEmpty() ? tbComCodeRepository.findAll(pageable)
				: tbComCodeRepository.findAll(TbComCodeSpec.searchWithKeys(searchKeys), pageable);

		for (TbComCode tbComCode : tbComCodeList) {
			tbComCode.setTbComCodeGroup(tbComCodeGroupRepository.findByCodeGroupId(tbComCode.getCodeGroupId()));
		}

		return tbComCodeList;
	}

	public List<TbComCode> findAll() {
		return tbComCodeRepository.findAll();
	}

	public TbComCode findById(BigInteger codeId) {
		return tbComCodeRepository.findByCodeId(codeId);
	}

	public List<TbComCode> findByCodeGroupIdNull() {
		return tbComCodeRepository.findByCodeGroupIdNull();
	}

	public TbComCode create(TbComCode tbComCode) {
		tbComCode.setCreateUserId(BigInteger.valueOf(11111));
		return tbComCodeRepository.save(tbComCode);
	}

	public TbComCode update(TbComCode tbComCode) {
		tbComCode.setUpdateUserId(BigInteger.valueOf(11111));
		return tbComCodeRepository.save(tbComCode);
	}

	public void delete(BigInteger codeId) {
		tbComCodeRepository.deleteById(codeId);
	}
}
