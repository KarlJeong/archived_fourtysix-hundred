package com.karljeong.fourtysix.common.loadstatic.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComCode;
import com.karljeong.fourtysix.database.entity.TbComCodeGroup;
import com.karljeong.fourtysix.database.repository.TbComCodeGroupRepository;
import com.karljeong.fourtysix.database.repository.TbComCodeRepository;

@Service
public class LoadStaticService {

	TbComCodeRepository tbComCodeRepository;
	TbComCodeGroupRepository tbComCodeGroupRepository;

	LoadStaticService(TbComCodeRepository tbComCodeRepository, TbComCodeGroupRepository tbComCodeGroupRepository) {
		this.tbComCodeRepository = tbComCodeRepository;
		this.tbComCodeGroupRepository = tbComCodeGroupRepository;
	}

	public Map<String, Map<String, Object>> loadSystemCode() {
		Map<String, Map<String, Object>> systemCodes = new HashMap<String, Map<String, Object>>();

		List<TbComCode> tbComCodes = tbComCodeRepository.findByCodeGroupIdNotNull();

		Map<BigInteger, List<TbComCode>> systemCodeGrp = tbComCodes.stream()
				.collect(Collectors.groupingBy(c -> c.getCodeGroupId()));

		for (BigInteger codeGroupId : systemCodeGrp.keySet()) {
			Map<String, Object> comCodeGroupMap = new HashMap<String, Object>();
			TbComCodeGroup tbComCodeGroup = tbComCodeGroupRepository.findById(codeGroupId);
			comCodeGroupMap.put("codeGroupValue", tbComCodeGroup.getCodeGroupValue());
			comCodeGroupMap.put("codeGroupType", tbComCodeGroup.getCodeGroupType());
			comCodeGroupMap.put("codeGprId", tbComCodeGroup.getCodeGroupId());
			comCodeGroupMap.put("codeGprName", tbComCodeGroup.getCodeGroupName());

			Map<String, List<TbComCode>> systemCodeVal = systemCodeGrp.get(codeGroupId).stream()
					.collect(Collectors.groupingBy(c -> c.getCodeValue()));

			List<Map<String, Object>> codeList = new ArrayList<Map<String, Object>>();
			for (String codeValue : systemCodeVal.keySet()) {
				Map<String, Object> codeValInfo = new HashMap<String, Object>();
				codeValInfo.put("codeValue", codeValue);
				codeValInfo.put("codeValueId", systemCodeVal.get(codeValue).get(0).getCodeId());
				codeValInfo.put("codeName", systemCodeVal.get(codeValue).get(0).getCodeName());
				codeValInfo.put("codeOrder", systemCodeVal.get(codeValue).get(0).getCodeOrder());
				codeList.add(codeValInfo);

			}

			codeList.sort(Comparator.comparing(o -> (byte) o.get("codeOrder")));
			comCodeGroupMap.put("code", codeList);

			systemCodes.put(tbComCodeGroup.getCodeGroupValue(), comCodeGroupMap);
		}

		return systemCodes;
	}
}
