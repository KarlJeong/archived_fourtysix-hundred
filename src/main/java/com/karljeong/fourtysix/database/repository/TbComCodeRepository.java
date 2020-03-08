package com.karljeong.fourtysix.database.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	List<TbComCode> findAll();

	@Override
	Page<TbComCode> findAll(Pageable pageable);

	@Query("SELECT c FROM TbComCode c WHERE c.codeGroupId IS NULL")
	List<TbComCode> findByCodeGroupIdNull();

	@Modifying
	@Query("UPDATE TbComCode c SET c.codeGroupId = :codeGroupId WHERE c.codeId = :codeId")
	int setCodeGroupId(@Param("codeId") String codeId, @Param("codeGroupId") String codeGroupId);
}
