package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
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

	@Transactional
    @Modifying
	@Query(value = "UPDATE TB_ARTICLE_GENERAL SET ARTICLE_LIKE_COUNT = (SELECT COUNT(1) FROM TB_ARTICLE_GENERAL_LIKE WHERE LIKE_YN = 1 AND ARTICLE_ID = :articleId) WHERE ARTICLE_ID = :articleId", nativeQuery = true)
	int saveLikeCount(@Param("articleId") BigInteger articleId);

	@Transactional
    @Modifying
    @Query(value = "UPDATE TB_ARTICLE_GENERAL SET ARTICLE_REPLY_COUNT = (SELECT COUNT(1) FROM TB_ARTICLE_GENERAL_REPLY WHERE ARTICLE_ID = :articleId) WHERE ARTICLE_ID = :articleId", nativeQuery = true)
    int saveReplyCount(@Param("articleId") BigInteger articleId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TB_ARTICLE_GENERAL SET ARTICLE_VIEW_COUNT = ARTICLE_VIEW_COUNT + 1 WHERE ARTICLE_ID = :articleId", nativeQuery = true)
    int saveViewCount(@Param("articleId") BigInteger articleId);


}
