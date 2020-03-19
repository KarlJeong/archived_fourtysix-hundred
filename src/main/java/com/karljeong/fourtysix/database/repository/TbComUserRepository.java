package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbComUser;

@RepositoryRestResource(collectionResourceRel = "userEntity", path = "tbComUserRepository")
public interface TbComUserRepository
		extends PagingAndSortingRepository<TbComUser, BigInteger>, JpaSpecificationExecutor<TbComUser> {

	@Override
    List<TbComUser> findAll();

	TbComUser findByLoginIdAndLoginPassword(String loginid, String loginPassword);

}
