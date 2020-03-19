package com.karljeong.fourtysix.application.admin.auth.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComAuth;
import com.karljeong.fourtysix.database.entity.TbComUser;
import com.karljeong.fourtysix.database.entity.TbMappUserAuth;
import com.karljeong.fourtysix.database.entity.TbMappUserAuthPK;
import com.karljeong.fourtysix.database.repository.TbComAuthRepository;
import com.karljeong.fourtysix.database.repository.TbMappUserAuthRepository;
import com.karljeong.fourtysix.database.specification.TbComAuthSpec;
import com.karljeong.fourtysix.database.specification.TbComAuthSpec.SearchKey;

@Service
public class AuthService {

	TbComAuthRepository tbComAuthRepository;
	TbMappUserAuthRepository tbMappUserAuthRepository;

	AuthService(TbComAuthRepository tbComAuthRepository, TbMappUserAuthRepository tbMappUserAuthRepository) {
		this.tbComAuthRepository = tbComAuthRepository;
		this.tbMappUserAuthRepository = tbMappUserAuthRepository;
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

		Page<TbComAuth> tbComAuthList = searchKeys.isEmpty() ? tbComAuthRepository.findAll(pageable)
				: tbComAuthRepository.findAll(TbComAuthSpec.searchWithKeys(searchKeys), pageable);

		for (TbComAuth tbComAuth : tbComAuthList) {
			tbComAuth.setTbComUsers(tbMappUserAuthRepository.findUsersByAuthId(tbComAuth.getAuthId()));
		}

		return tbComAuthList;
	}

	public List<TbComAuth> findAll() {
		return tbComAuthRepository.findAll();
	}

	public TbComAuth findById(BigInteger authId) {
		TbComAuth tbComAuth = tbComAuthRepository.findByAuthId(authId);
		tbComAuth.setTbComUsers(tbMappUserAuthRepository.findUsersByAuthId(tbComAuth.getAuthId()));
		return tbComAuth;
	}

	public TbComAuth create(TbComAuth tbComAuth) {
		tbComAuth.setCreateUserId(BigInteger.valueOf(11111));
		TbComAuth save = tbComAuthRepository.save(tbComAuth);

		List<TbComUser> tbComUsers = tbComAuth.getTbComUsers();
		if (tbComUsers != null && !tbComUsers.isEmpty()) {
			for (TbComUser tbComUser : tbComUsers) {
				TbMappUserAuth tbMappUserAuth = new TbMappUserAuth();
				tbMappUserAuth.setCreateUserId(BigInteger.valueOf(11111));
				tbMappUserAuth.setDeleteYn((byte) 0);

				TbMappUserAuthPK tbMappUserAuthPK = new TbMappUserAuthPK();
				tbMappUserAuthPK.setUserId(tbComUser.getUserId());
				tbMappUserAuthPK.setAuthId(tbComAuth.getAuthId());
				tbMappUserAuth.setId(tbMappUserAuthPK);

				tbMappUserAuth.setDeleteYn((byte) 0);

				tbMappUserAuthRepository.save(tbMappUserAuth);
			}
		}

		return save;
	}

	public TbComAuth update(TbComAuth tbComAuth) {
		tbComAuth.setUpdateUserId(BigInteger.valueOf(11111));
		TbComAuth save = tbComAuthRepository.save(tbComAuth);
		List<TbComUser> tbComUsers = tbComAuth.getTbComUsers();

		if (tbComUsers != null && !tbComUsers.isEmpty()) {
			tbMappUserAuthRepository.deleteByAuthId(tbComAuth.getAuthId());

			for (TbComUser tbComUser : tbComUsers) {
				TbMappUserAuth tbMappUserAuth = new TbMappUserAuth();
				tbMappUserAuth.setCreateUserId(BigInteger.valueOf(11111));
				tbMappUserAuth.setDeleteYn((byte) 0);

				TbMappUserAuthPK tbMappUserAuthPK = new TbMappUserAuthPK();
				tbMappUserAuthPK.setUserId(tbComUser.getUserId());
				tbMappUserAuthPK.setAuthId(tbComAuth.getAuthId());
				tbMappUserAuth.setId(tbMappUserAuthPK);

				tbMappUserAuth.setDeleteYn((byte) 0);

				tbMappUserAuthRepository.save(tbMappUserAuth);
			}
		}
		return save;

	}

	public int delete(BigInteger authId) {
		tbMappUserAuthRepository.deleteByAuthId(authId);
		return tbComAuthRepository.deleteByAuthId(authId);
	}

}
