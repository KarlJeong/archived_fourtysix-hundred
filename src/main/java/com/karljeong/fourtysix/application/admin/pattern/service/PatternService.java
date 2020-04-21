package com.karljeong.fourtysix.application.admin.pattern.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.common.loadstatic.LoadStatic;
import com.karljeong.fourtysix.database.entity.TbComAuth;
import com.karljeong.fourtysix.database.entity.TbComPattern;
import com.karljeong.fourtysix.database.entity.TbMappPatternAuth;
import com.karljeong.fourtysix.database.entity.TbMappPatternAuthPK;
import com.karljeong.fourtysix.database.repository.TbComPatternRepository;
import com.karljeong.fourtysix.database.repository.TbMappPatternAuthRepository;

@Service
public class PatternService {

	private final TbComPatternRepository tbComPatternRepository;
	private final TbMappPatternAuthRepository tbMappPatternAuthRepository;
	private final LoadStatic loadStatic;

	@Autowired
	PatternService(TbComPatternRepository tbComPatternRepository, TbMappPatternAuthRepository tbMappPatternAuthRepository, LoadStatic loadStatic) {
		this.tbComPatternRepository = tbComPatternRepository;
		this.tbMappPatternAuthRepository = tbMappPatternAuthRepository;
		this.loadStatic = loadStatic;
	}

	public Page<TbComPattern> readList(Map<String, Object> searchRequest, Pageable pageable) {
	    Page<TbComPattern> comPatternList = tbComPatternRepository.findAll(pageable);

        for (TbComPattern tbComPattern : comPatternList) {
            tbComPattern.setTbComAuths(tbMappPatternAuthRepository.findByPatternId(tbComPattern.getPatternId()));
            tbComPattern.setTbMappPatternAuths(tbMappPatternAuthRepository.findMethodByPatternId(tbComPattern.getPatternId()));
        }

        return comPatternList;
	}

    public TbComPattern findById(BigInteger patternId) {
        TbComPattern tbComPattern = tbComPatternRepository.findById(patternId).get();
        tbComPattern.setTbComAuths(tbMappPatternAuthRepository.findByPatternId(patternId));
        tbComPattern.setTbMappPatternAuths(tbMappPatternAuthRepository.findMethodByPatternId(patternId));
        return tbComPattern;
    }


    public TbComPattern create(TbComPattern tbComPattern) {
        tbComPattern.setCreateUserId(BigInteger.valueOf(11111));
        TbComPattern save = tbComPatternRepository.save(tbComPattern);

        List<TbComAuth> tbComAuths = tbComPattern.getTbComAuths();
        List<TbMappPatternAuth> tbMappPatternAuths = tbComPattern.getTbMappPatternAuths();
        if (tbComAuths != null && tbMappPatternAuths != null && !tbComAuths.isEmpty() && !tbMappPatternAuths.isEmpty()) {
            for (TbComAuth tbComAuth : tbComAuths) {
                for (TbMappPatternAuth tbMappPatternAuth : tbMappPatternAuths) {
                    tbMappPatternAuth.setCreateUserId(BigInteger.valueOf(11111));
                    tbMappPatternAuth.setDeleteYn((byte) 0);

                    TbMappPatternAuthPK tbMappPatternAuthPK = new TbMappPatternAuthPK();
                    tbMappPatternAuthPK.setPatternId(save.getPatternId());
                    tbMappPatternAuthPK.setAuthId(tbComAuth.getAuthId());
                    tbMappPatternAuthPK.setMethod(tbMappPatternAuth.getMethod());
                    tbMappPatternAuth.setId(tbMappPatternAuthPK);

                    tbMappPatternAuthRepository.save(tbMappPatternAuth);
                }
            }
        }
        loadStatic.resetPatterList();
        return save;
    }

    public TbComPattern update(TbComPattern tbComPattern) {
        tbComPattern.setUpdateUserId(BigInteger.valueOf(11111));
        TbComPattern save = tbComPatternRepository.save(tbComPattern);


        List<TbComAuth> tbComAuths = tbComPattern.getTbComAuths();
        List<TbMappPatternAuth> tbMappPatternAuths = tbComPattern.getTbMappPatternAuths();
        if (tbComAuths != null && tbMappPatternAuths != null && !tbComAuths.isEmpty() && !tbMappPatternAuths.isEmpty()) {
            tbMappPatternAuthRepository.deleteByPatternId(tbComPattern.getPatternId());

            for (TbComAuth tbComAuth : tbComAuths) {
                for (TbMappPatternAuth tbMappPatternAuth : tbMappPatternAuths) {
                    tbMappPatternAuth.setCreateUserId(BigInteger.valueOf(11111));
                    tbMappPatternAuth.setDeleteYn((byte) 0);
                    TbMappPatternAuthPK tbMappPatternAuthPK = new TbMappPatternAuthPK();
                    tbMappPatternAuthPK.setPatternId(save.getPatternId());
                    tbMappPatternAuthPK.setAuthId(tbComAuth.getAuthId());
                    tbMappPatternAuthPK.setMethod(tbMappPatternAuth.getMethod());
                    tbMappPatternAuth.setId(tbMappPatternAuthPK);

                    tbMappPatternAuthRepository.save(tbMappPatternAuth);
                }
            }
        }
        loadStatic.resetPatterList();
        return save;

    }

    public void delete(BigInteger patternId) {
        tbMappPatternAuthRepository.deleteByPatternId(patternId);
        tbComPatternRepository.deleteById(patternId);
        loadStatic.resetPatterList();
    }

}
