package com.karljeong.fourtysix.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbComAuth;

@RepositoryRestResource(collectionResourceRel = "authEntity", path = "tbComAuthRepository")
public interface TbComAuthRepository
		extends PagingAndSortingRepository<TbComAuth, Long>, JpaSpecificationExecutor<TbComAuth> {

	@Override
	List<TbComAuth> findAll();


}
