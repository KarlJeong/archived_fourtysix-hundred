package com.karljeong.fourtysix.application.admin.code.service;

import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComCode;
import com.karljeong.fourtysix.database.repository.TbComCodeRepository;

@Service
public class CodeService {

	TbComCodeRepository tbComCodeRepository;

	CodeService(TbComCodeRepository tbComCodeRepository) {
		this.tbComCodeRepository = tbComCodeRepository;
	}

	public TbComCode findById(Long codeId) {
		return tbComCodeRepository.findById(codeId).get();
	}
}
