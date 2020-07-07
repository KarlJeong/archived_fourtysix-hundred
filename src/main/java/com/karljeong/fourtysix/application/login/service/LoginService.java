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

    public TbComUser save(User faebookUser) {
        TbComUser tbComUser = new TbComUser();
        tbComUser.setCreateUserId(BigInteger.valueOf(11111));
        tbComUser.setLoginId(faebookUser.getEmail());
        tbComUser.setLoginPassword(faebookUser.getEmail());
        tbComUser.setActivateYn((byte)1);
        tbComUser.setActivateDatetime(DateUtil.getTimestamp());
        tbComUser.setUserLocale(faebookUser.getLocale());
        tbComUser.setLastLoginDatetime(DateUtil.getTimestamp());
        tbComUser.setEmail(faebookUser.getEmail());
        tbComUser.setUserName(faebookUser.getName());
        tbComUser.setUserNickname(faebookUser.getName());
        tbComUser.setSnsId(faebookUser.getId());
        tbComUser.setBirthday(new Timestamp(faebookUser.getBirthdayAsDate().getTime()));
        tbComUser.setLocationId(faebookUser.getLocation().getId());
        tbComUser.setLocationName(faebookUser.getLocation().getName());
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