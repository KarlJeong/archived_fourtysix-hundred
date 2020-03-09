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

import com.karljeong.fourtysix.database.entity.TbComCode;

@RepositoryRestResource(collectionResourceRel = "codeEntity", path = "tbComCodeRepository")
public interface TbComCodeRepository
		extends PagingAndSortingRepository<TbComCode, Long>, JpaSpecificationExecutor<TbComCode> {

	@Query("SELECT c FROM TbComCode c WHERE c.codeGroupId IS NULL")
	List<TbComCode> findByCodeGroupIdNull();

	@Transactional
	@Modifying
	@Query("UPDATE TbComCode c SET c.updateUserId = :updateUserId, c.codeGroupId = :codeGroupId WHERE c.codeId = :codeId")
	int saveCodeGroupId(@Param("updateUserId") BigInteger updateUserId, @Param("codeId") BigInteger codeId,
			@Param("codeGroupId") BigInteger codeGroupId);

	@Query("SELECT c FROM TbComCode c WHERE c.codeGroupId = :codeGroupId")
	List<TbComCode> findByCodeGroupId(@Param("codeGroupId") BigInteger codeGroupId);

}
