package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbArticleBlogLike;
import com.karljeong.fourtysix.database.entity.TbArticleBlogLikePK;

@RepositoryRestResource(collectionResourceRel = "articleBlogLikeEntity", path = "tbArticleBlogLikeRepository")
public interface TbArticleBlogLikeRepository
		extends PagingAndSortingRepository<TbArticleBlogLike, TbArticleBlogLikePK>,
		JpaSpecificationExecutor<TbArticleBlogLike> {

	@Transactional
    @Modifying
	@Query(value = "UPDATE TB_ARTICLE_BLOG SET ARTICLE_LIKE_COUNT = (SELECT COUNT(1) FROM TB_ARTICLE_BLOG_LIKE WHERE LIKE_YN = 1 AND ARTICLE_ID = :articleId) WHERE ARTICLE_ID = :articleId", nativeQuery = true)
	int saveLikeCount(@Param("articleId") BigInteger articleId);
	
	@Transactional
    @Modifying
    @Query(value = "UPDATE TB_ARTICLE_BLOG SET ARTICLE_REPLY_COUNT = (SELECT COUNT(1) FROM TB_ARTICLE_BLOG_REPLY WHERE ARTICLE_ID = :articleId) WHERE ARTICLE_ID = :articleId", nativeQuery = true)
    int saveReplyCount(@Param("articleId") BigInteger articleId);
}
