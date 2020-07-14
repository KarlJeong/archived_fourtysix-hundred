package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbArticle;

@RepositoryRestResource(collectionResourceRel = "articleEntity", path = "tbArticleRepository")
public interface TbArticleRepository
		extends PagingAndSortingRepository<TbArticle, BigInteger>,
		JpaSpecificationExecutor<TbArticle> {

	TbArticle findByArticleIdAndArticleCode(BigInteger articleId, String articleCode);

	@Query(value = "SELECT ARTICLE_CODE, COUNT(1) AS ARTICLE_COUNT FROM TB_ARTICLE WHERE ARTICLE_WRITER_ID = :userId GROUP BY ARTICLE_CODE", nativeQuery = true)
	List<Object[]> getArticleCodes(BigInteger userId);
}
