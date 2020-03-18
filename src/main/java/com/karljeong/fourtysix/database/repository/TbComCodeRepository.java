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
		extends PagingAndSortingRepository<TbComCode, BigInteger>, JpaSpecificationExecutor<TbComCode> {

	@Override
	List<TbComCode> findAll();

	@Query("SELECT c FROM TbComCode c WHERE c.codeId = :codeId")
	TbComCode findByCodeId(BigInteger codeId);

	@Query("SELECT c FROM TbComCode c WHERE c.codeGroupId IS NULL")
	List<TbComCode> findByCodeGroupIdNull();

	@Query("SELECT c FROM TbComCode c WHERE c.codeGroupId IS NOT NULL AND c.useYn = 1")
	List<TbComCode> findByCodeGroupIdNotNull();

	@Query("SELECT c FROM TbComCode c WHERE c.codeGroupId = :codeGroupId")
	List<TbComCode> findByCodeGroupId(@Param("codeGroupId") BigInteger codeGroupId);

	@Transactional
	@Modifying
	@Query("UPDATE TbComCode c SET c.updateUserId = :updateUserId, c.codeGroupId = :codeGroupId WHERE c.codeId = :codeId")
	int saveCodeGroupId(@Param("updateUserId") BigInteger updateUserId, @Param("codeId") BigInteger codeId,
			@Param("codeGroupId") BigInteger codeGroupId);

	@Transactional
	@Modifying
	@Query("UPDATE TbComCode c SET c.updateUserId = :updateUserId, c.codeGroupId = NULL WHERE c.codeGroupId = :codeGroupId")
	int deleteCodeGroupId(@Param("updateUserId") BigInteger updateUserId, @Param("codeGroupId") BigInteger codeGroupId);

	@Transactional
	@Modifying
	@Query("DELETE FROM TbComCode c WHERE c.codeId = :codeId")
	void deleteById(@Param("codeId") BigInteger codeId);

}
