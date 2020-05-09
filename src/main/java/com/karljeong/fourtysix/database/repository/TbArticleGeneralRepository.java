package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbArticleGeneral;

@RepositoryRestResource(collectionResourceRel = "articleGeneralEntity", path = "tbArticleGeneralRepository")
public interface TbArticleGeneralRepository
		extends PagingAndSortingRepository<TbArticleGeneral, BigInteger>, JpaSpecificationExecutor<TbArticleGeneral> {

	@Query(value = "SELECT FN_GET_USER_NAME(:userId)", nativeQuery = true)
	String findArticleWriterName(@Param("userId") BigInteger userId);
}
