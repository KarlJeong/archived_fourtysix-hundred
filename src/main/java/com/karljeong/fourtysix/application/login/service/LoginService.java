package com.karljeong.fourtysix.application.login.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComAuth;
import com.karljeong.fourtysix.database.entity.TbComUser;
import com.karljeong.fourtysix.database.entity.TbComUserBan;
import com.karljeong.fourtysix.database.repository.TbComAuthRepository;
import com.karljeong.fourtysix.database.repository.TbComUserBanRepository;
import com.karljeong.fourtysix.database.repository.TbComUserRepository;
import com.karljeong.fourtysix.database.repository.TbMappUserAuthRepository;
import com.karljeong.fourtysix.utils.DateUtil;
import com.restfb.types.User;

@Service
public class LoginService {

    private final TbComUserRepository tbComUserRepository;
    private final TbComUserBanRepository tbComUserBanRepository;
    private final TbComAuthRepository tbComAuthRepository;
    private final TbMappUserAuthRepository tbMappUserAuthRepository;

    @Autowired
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

    public TbComUser save(User facebookUser) {
        TbComUser tbComUser = new TbComUser();
        tbComUser.setCreateUserId(BigInteger.valueOf(11111));
        tbComUser.setLoginId(facebookUser.getEmail());
        tbComUser.setLoginPassword(facebookUser.getEmail());
        tbComUser.setActivateYn((byte)1);
        tbComUser.setActivateDatetime(DateUtil.getTimestamp());
        tbComUser.setUserLocale(facebookUser.getLocale());
        tbComUser.setLastLoginDatetime(DateUtil.getTimestamp());
        tbComUser.setEmail(facebookUser.getEmail());
        tbComUser.setUserName(facebookUser.getName());
        tbComUser.setUserNickname(facebookUser.getName());
        tbComUser.setSnsId(facebookUser.getId());
        if (facebookUser.getBirthdayAsDate() != null) {
        	tbComUser.setBirthday(new Timestamp(facebookUser.getBirthdayAsDate().getTime()));
        }
        if (facebookUser.getLocation() != null) {
        	tbComUser.setLocationId(facebookUser.getLocation().getId());
        	tbComUser.setLocationName(facebookUser.getLocation().getName().toUpperCase());
        }
        if (facebookUser.getGender() != null) {
        	tbComUser.setGender(facebookUser.getGender().toUpperCase());
        }
        return tbComUserRepository.save(tbComUser);
    }

    public int findBannedInfoByUserId(BigInteger userId) {
        List<TbComUserBan> tbComoUserBans = tbComUserBanRepository.findByBanUserIdAndBanYn(userId, (byte) 1);
        return tbComoUserBans.size();
    }

    public List<TbComAuth> findAuthsInfoByUserId(BigInteger userId) {
        return tbMappUserAuthRepository.findAuthsByUserId(userId);
    }
}