package com.karljeong.fourtysix.application.admin.board.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComBoard;
import com.karljeong.fourtysix.database.entity.TbMappBoardAuth;
import com.karljeong.fourtysix.database.entity.TbMappBoardAuthPK;
import com.karljeong.fourtysix.database.repository.TbComBoardRepository;
import com.karljeong.fourtysix.database.repository.TbMappBoardAuthRepository;
import com.karljeong.fourtysix.database.specification.TbComBoardSpec;
import com.karljeong.fourtysix.database.specification.TbComBoardSpec.SearchKey;

@Service
public class BoardService {

	private final TbComBoardRepository tbComBoardRepository;
	private final TbMappBoardAuthRepository tbMappBoardAuthRepository;

	@Autowired
	BoardService(TbComBoardRepository tbComBoardRepository, TbMappBoardAuthRepository tbMappBoardAuthRepository) {
		this.tbComBoardRepository = tbComBoardRepository;
		this.tbMappBoardAuthRepository = tbMappBoardAuthRepository;
	}

	public Page<TbComBoard> readList(Map<String, Object> searchRequest, Pageable pageable) {
		Map<SearchKey, Object> searchKeys = new HashMap<>();
		for (String key : searchRequest.keySet()) {
			if (searchRequest.get(key) != null && !"".equals(searchRequest.get(key))) {
				if (!Arrays.asList(new String[] { "SIZE", "PAGE", "SORT" }).contains(key.toUpperCase())) {
					searchKeys.put(SearchKey.valueOf(key.toUpperCase()), searchRequest.get(key));
				}
			}
		}

		Page<TbComBoard> tbComBoardList = searchKeys.isEmpty() ? tbComBoardRepository.findAll(pageable)
				: tbComBoardRepository.findAll(TbComBoardSpec.searchWithKeys(searchKeys), pageable);

		return tbComBoardList;
	}

	public List<TbComBoard> findAll() {
		return tbComBoardRepository.findAll();
	}

    public TbComBoard findById(BigInteger boardId) {
        TbComBoard tbComBoard = tbComBoardRepository.findById(boardId).get();
        tbComBoard.setTbComAuthReadable(tbMappBoardAuthRepository.findReadableByBoardId(boardId));
        tbComBoard.setTbComAuthWritable(tbMappBoardAuthRepository.findWritableByBoardId(boardId));
        return tbComBoard;
    }

	public TbComBoard create(TbComBoard tbComBoard) {
		tbComBoard.setCreateUserId(BigInteger.valueOf(11111));
		TbComBoard save = tbComBoardRepository.save(tbComBoard);
		List<TbMappBoardAuth> tbComAuthReadable = tbComBoard.getTbComAuthReadable();
		List<TbMappBoardAuth> tbComAuthWritable = tbComBoard.getTbComAuthWritable();

		Map<BigInteger, TbMappBoardAuth> tbMappBoardAuths = new HashMap<BigInteger, TbMappBoardAuth>();

		if (tbComAuthReadable != null && !tbComAuthReadable.isEmpty()) {
            for (TbMappBoardAuth tbMappBoardAuth : tbComAuthReadable) {
                tbMappBoardAuth.setReadableYn((byte) 1);

                TbMappBoardAuthPK tbMappBoardAuthPK = new TbMappBoardAuthPK();
                tbMappBoardAuthPK.setBoardId(tbComBoard.getBoardId());
                tbMappBoardAuthPK.setAuthId(tbMappBoardAuth.getAuthId());
                tbMappBoardAuth.setId(tbMappBoardAuthPK);
                tbMappBoardAuths.put(tbMappBoardAuth.getAuthId(), tbMappBoardAuth);
            }
        }

        if (tbComAuthWritable != null && !tbComAuthWritable.isEmpty()) {
            for (TbMappBoardAuth tbMappBoardAuth : tbComAuthWritable) {

                if (tbMappBoardAuths.containsKey(tbMappBoardAuth.getAuthId())) {
                    tbMappBoardAuth = tbMappBoardAuths.get(tbMappBoardAuth.getAuthId());
                    tbMappBoardAuth.setWritableYn((byte) 1);
                } else {
                    tbMappBoardAuth.setWritableYn((byte) 1);

                    TbMappBoardAuthPK tbMappBoardAuthPK = new TbMappBoardAuthPK();
                    tbMappBoardAuthPK.setBoardId(tbComBoard.getBoardId());
                    tbMappBoardAuthPK.setAuthId(tbMappBoardAuth.getAuthId());
                    tbMappBoardAuth.setId(tbMappBoardAuthPK);
                    tbMappBoardAuths.put(tbMappBoardAuth.getAuthId(), tbMappBoardAuth);
                }
            }
        }

        for (BigInteger authId : tbMappBoardAuths.keySet()) {
            tbMappBoardAuthRepository.save(tbMappBoardAuths.get(authId));
        }

		return save;
	}

}
