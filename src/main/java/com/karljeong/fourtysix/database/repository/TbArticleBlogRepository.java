package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbArticleBlog;
import com.karljeong.fourtysix.database.entity.TbComArticle;

@RepositoryRestResource(collectionResourceRel = "articleEntity", path = "tbArticleBlogRepository")
public interface TbArticleBlogRepository extends PagingAndSortingRepository<TbArticleBlog, BigInteger>, JpaSpecificationExecutor<TbArticleBlog> {

	
	@Query(value = "SELECT c.*, FN_GET_USER_NAME(b.USER_ID) AS ARTICLE_WRITER_USER_NAME, b.SNS_ID "
			+ "FROM TB_ARTICLE_BLOG c INNER JOIN TB_COM_USER b ON c.ARTICLE_WRITER_ID = b.USER_ID "
			+ "WHERE c.ARTICLE_ID = :articleId", nativeQuery = true)
	Optional<TbArticleBlog> findById(@Param("articleId") BigInteger articleId);
	
    @Query(value = "SELECT FN_GET_USER_NAME(:userId)", nativeQuery = true)
    String findArticleWriterName(@Param("userId") BigInteger userId);

    List<TbArticleBlog> findByArticleTitle(@Param("articleTitle") String articleTitle);

    List<TbArticleBlog> findByArticleContents(@Param("articleContents") String articleContents);
}
