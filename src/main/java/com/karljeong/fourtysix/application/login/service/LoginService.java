package com.karljeong.fourtysix.application.login.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComAuth;
import com.karljeong.fourtysix.database.entity.TbComUser;
import com.karljeong.fourtysix.database.entity.TbComUserBan;
import com.karljeong.fourtysix.database.repository.TbComAuthRepository;
import com.karljeong.fourtysix.database.repository.TbComUserBanRepository;
import com.karljeong.fourtysix.database.repository.TbComUserRepository;
import com.karljeong.fourtysix.database.repository.TbMappUserAuthRepository;

@Service
public class LoginService {

	final TbComUserRepository tbComUserRepository;
	final TbComUserBanRepository tbComUserBanRepository;
	final TbComAuthRepository tbComAuthRepository;
	final TbMappUserAuthRepository tbMappUserAuthRepository;

	LoginService(TbComUserRepository tbComUserRepository, TbComUserBanRepository tbComUserBanRepository,
			TbComAuthRepository tbComAuthRepository, TbMappUserAuthRepository tbMappUserAuthRepository) {
		this.tbComUserRepository = tbComUserRepository;
		this.tbComUserBanRepository = tbComUserBanRepository;
		this.tbComAuthRepository = tbComAuthRepository;
		this.tbMappUserAuthRepository = tbMappUserAuthRepository;
	}

	public TbComUser findByLoginIdAndLoginPassword(String loginId, String loginPassword) {
		return tbComUserRepository.findByLoginIdAndLoginPassword(loginId, loginPassword);
	}

	public TbComUser findByLoginId(String loginId) {
		return tbComUserRepository.findByLoginId(loginId);
	}

	public int findBannedInfoByUserId(BigInteger userId) {
		List<TbComUserBan> tbComoUserBans = tbComUserBanRepository.findByBanUserIdAndBanYn(userId, (byte) 1);
		return tbComoUserBans.size();
	}

	public List<TbComAuth> findAuthsInfoByUserId(BigInteger userId) {
		return tbMappUserAuthRepository.findAuthsByUserId(userId);
	}
}
