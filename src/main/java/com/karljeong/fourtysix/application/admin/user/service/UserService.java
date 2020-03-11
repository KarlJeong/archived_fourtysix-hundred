package com.karljeong.fourtysix.application.admin.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComUser;
import com.karljeong.fourtysix.database.repository.TbComUserRepository;

@Service
public class UserService {

    TbComUserRepository tbComUserRepository;

	UserService(TbComUserRepository tbComUserRepository) {
		this.tbComUserRepository = tbComUserRepository;
	}


	public List<TbComUser> findAll() {
		return tbComUserRepository.findAll();
	}

}
