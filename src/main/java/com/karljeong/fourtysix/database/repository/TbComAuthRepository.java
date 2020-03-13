package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbComAuth;

@RepositoryRestResource(collectionResourceRel = "authEntity", path = "tbComAuthRepository")
public interface TbComAuthRepository
		extends PagingAndSortingRepository<TbComAuth, Long>, JpaSpecificationExecutor<TbComAuth> {

	@Override
	List<TbComAuth> findAll();

	@Query("SELECT c FROM TbComAuth c WHERE c.authId = :authId")
    TbComAuth findByAuthId(@Param("authId") BigInteger authId);

	@Transactional
	@Modifying
	@Query("DELETE FROM TbComAuth c WHERE c.authId = :authId")
	int deleteByAuthId(@Param("authId") BigInteger authId);


}
