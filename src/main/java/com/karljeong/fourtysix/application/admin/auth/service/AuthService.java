package com.karljeong.fourtysix.application.admin.auth.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.restfb.types.User;

@Service
public class AuthService {

    private final TbComAuthRepository tbComAuthRepository;
    private final TbMappUserAuthRepository tbMappUserAuthRepository;

    @Autowired
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

    public TbComAuth findByAuthCode(String authCode) {
    	return tbComAuthRepository.findByAuthCode(authCode);
    }

    public TbComAuth create(TbComAuth tbComAuth) {
        tbComAuth.setCreateUserId(BigInteger.valueOf(11111));
        TbComAuth save = tbComAuthRepository.save(tbComAuth);

        List<TbComUser> tbComUsers = tbComAuth.getTbComUsers();
        if (tbComUsers != null && !tbComUsers.isEmpty()) {
            for (TbComUser tbComUser : tbComUsers) {
            	this.addUserAuth(tbComUser.getUserId(), tbComAuth.getAuthId());
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
            	this.addUserAuth(tbComUser.getUserId(), tbComAuth.getAuthId());
            }
        }
        return save;

    }

    public int delete(BigInteger authId) {
        tbMappUserAuthRepository.deleteByAuthId(authId);
        return tbComAuthRepository.deleteByAuthId(authId);
    }
    
    public TbMappUserAuth createUserAuthMember(BigInteger userId) {
    	TbComAuth tbComAuth = this.findByAuthCode("MEMBER");
    	return this.addUserAuth(userId, tbComAuth.getAuthId());
    }
    
    public TbMappUserAuth findUserAuth (BigInteger userId, BigInteger authId) {
    	TbMappUserAuthPK tbMappUserAuthPK = new TbMappUserAuthPK();
    	tbMappUserAuthPK.setUserId(userId);
	    tbMappUserAuthPK.setAuthId(authId);
	    return tbMappUserAuthRepository.findById(tbMappUserAuthPK).get();
    }
    
    public TbMappUserAuth addUserAuth (BigInteger userId, BigInteger authId) {
    	TbMappUserAuth tbMappUserAuth = new TbMappUserAuth();
        tbMappUserAuth.setCreateUserId(BigInteger.valueOf(11111));
        tbMappUserAuth.setDeleteYn((byte) 0);
        
    	TbMappUserAuthPK tbMappUserAuthPK = new TbMappUserAuthPK();
    	tbMappUserAuthPK.setUserId(userId);
	    tbMappUserAuthPK.setAuthId(authId);
	    tbMappUserAuth.setId(tbMappUserAuthPK);
	
	    tbMappUserAuth.setDeleteYn((byte) 0);
	
	    return tbMappUserAuthRepository.save(tbMappUserAuth);
    }
    
    

}
