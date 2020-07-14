package com.karljeong.fourtysix.application.admin.user.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComUser;
import com.karljeong.fourtysix.database.repository.TbComUserRepository;

@Service
public class UserService {

    private final TbComUserRepository tbComUserRepository;

    @Autowired
	UserService(TbComUserRepository tbComUserRepository) {
		this.tbComUserRepository = tbComUserRepository;
	}


	public List<TbComUser> findAll() {
		return tbComUserRepository.findAll();
	}
	
	public TbComUser findById(BigInteger userId) {
		return tbComUserRepository.findById(userId).get();
	}

}
