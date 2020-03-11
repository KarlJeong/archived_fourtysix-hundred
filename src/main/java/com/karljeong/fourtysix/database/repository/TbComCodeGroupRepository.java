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

import com.karljeong.fourtysix.database.entity.TbComCodeGroup;

@RepositoryRestResource(collectionResourceRel = "codeGroupEntity", path = "tbComCodeGroupRepository")
public interface TbComCodeGroupRepository
		extends PagingAndSortingRepository<TbComCodeGroup, String>, JpaSpecificationExecutor<TbComCodeGroup> {

	@Override
	List<TbComCodeGroup> findAll();

	@Query("SELECT c FROM TbComCodeGroup c WHERE c.codeGroupId = :codeGroupId")
	TbComCodeGroup findById(@Param("codeGroupId") BigInteger codeGroupId);

	@Query("SELECT c FROM TbComCodeGroup c WHERE c.codeGroupId = :codeGroupId")
	TbComCodeGroup findByCodeGroupId(@Param("codeGroupId") BigInteger codeGroupId);

	@Transactional
	@Modifying
	@Query("DELETE FROM TbComCodeGroup c WHERE c.codeGroupId = :codeGroupId")
	int deleteById(@Param("codeGroupId") BigInteger codeGroupId);
}
