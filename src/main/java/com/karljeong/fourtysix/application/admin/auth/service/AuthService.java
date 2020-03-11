package com.karljeong.fourtysix.application.admin.auth.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComAuth;
import com.karljeong.fourtysix.database.repository.TbComAuthRepository;
import com.karljeong.fourtysix.database.specification.TbComAuthSpec;
import com.karljeong.fourtysix.database.specification.TbComAuthSpec.SearchKey;

@Service
public class AuthService {

    TbComAuthRepository tbComAuthRepository;

	AuthService(TbComAuthRepository tbComAuthRepository) {
		this.tbComAuthRepository = tbComAuthRepository;
	}

    public Page<TbComAuth> readList(Map<String, Object> searchRequest, Pageable pageable) {
        Map<SearchKey, Object> searchKeys = new HashMap<>();
        for (String key : searchRequest.keySet()) {
            if (searchRequest.get(key) != null && !"".equals(searchRequest.get(key))) {
                if (!Arrays.asList(new String[] { "SIZE", "PAGE", "SORT" }).contains(key.toUpperCase())) {
                    searchKeys.put(SearchKey.valueOf(key.toUpperCase()), searchRequest.get(key));
                }
            }
        }

        Page<TbComAuth> tbComCodeList = searchKeys.isEmpty() ? tbComAuthRepository.findAll(pageable)
                : tbComAuthRepository.findAll(TbComAuthSpec.searchWithKeys(searchKeys), pageable);

        return tbComCodeList;
    }

}
