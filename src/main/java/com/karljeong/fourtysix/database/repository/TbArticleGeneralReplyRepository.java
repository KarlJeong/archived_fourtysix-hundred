package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbArticleGeneralReply;

@RepositoryRestResource(collectionResourceRel = "articleGeneralReplyEntity", path = "tbArticleGeneralReplyRepository")
public interface TbArticleGeneralReplyRepository extends PagingAndSortingRepository<TbArticleGeneralReply, BigInteger>,
		JpaSpecificationExecutor<TbArticleGeneralReply> {

	@Query(value = "SELECT FN_GET_USER_NAME(:userId)", nativeQuery = true)
	String findReplyWriterName(@Param("userId") BigInteger userId);
}
