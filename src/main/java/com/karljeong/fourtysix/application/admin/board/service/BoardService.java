package com.karljeong.fourtysix.application.admin.board.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComAuth;
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

	public TbComBoard create(TbComBoard tbComBoard) {
		tbComBoard.setCreateUserId(BigInteger.valueOf(11111));
		TbComBoard save = tbComBoardRepository.save(tbComBoard);
		List<TbComAuth> tbComAuthReadable = tbComBoard.getTbComAuthReadable();
		List<TbComAuth> tbComAuthWritable = tbComBoard.getTbComAuthWritable();

		Map<BigInteger, List<TbComAuth>> groupedList = Stream.of(tbComAuthReadable, tbComAuthWritable)
				.flatMap(x -> x.stream()).collect(Collectors.groupingBy(y -> y.getAuthId()));

		// TO-Do writable, readable 입력
		if (tbComAuthReadable != null && !tbComAuthReadable.isEmpty()) {
			for (TbComAuth tbComAuth : tbComAuthReadable) {
				TbMappBoardAuth tbMappBoardAuth = new TbMappBoardAuth();
				tbMappBoardAuth.setReadableYn((byte) 1);

				TbMappBoardAuthPK tbMappBoardAuthPK = new TbMappBoardAuthPK();
				tbMappBoardAuthPK.setBoardId(tbComBoard.getBoardId());
				tbMappBoardAuthPK.setAuthId(tbComAuth.getAuthId());
				tbMappBoardAuth.setId(tbMappBoardAuthPK);

				tbMappBoardAuthRepository.save(tbMappBoardAuth);
			}
		}

		if (tbComAuthWritable != null && !tbComAuthWritable.isEmpty()) {
			for (TbComAuth tbComAuth : tbComAuthWritable) {
				TbMappBoardAuth tbMappBoardAuth = new TbMappBoardAuth();
				tbMappBoardAuth.setWritableYn((byte) 1);

				TbMappBoardAuthPK tbMappBoardAuthPK = new TbMappBoardAuthPK();
				tbMappBoardAuthPK.setBoardId(tbComBoard.getBoardId());
				tbMappBoardAuthPK.setAuthId(tbComAuth.getAuthId());
				tbMappBoardAuth.setId(tbMappBoardAuthPK);

				tbMappBoardAuthRepository.save(tbMappBoardAuth);
			}
		}
		return save;
	}

}
