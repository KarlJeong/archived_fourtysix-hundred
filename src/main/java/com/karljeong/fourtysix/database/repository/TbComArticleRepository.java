package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbComArticle;

@RepositoryRestResource(collectionResourceRel = "articleEntity", path = "tbComArticleRepository")
public interface TbComArticleRepository
		extends PagingAndSortingRepository<TbComArticle, BigInteger>, JpaSpecificationExecutor<TbComArticle> {

	@Override
	List<TbComArticle> findAll();

	@Query("SELECT c FROM TbComArticle c INNER JOIN TbComBoard b ON b.boardId = c.boardId WHERE b.boardCode = :boardCode AND c.articlePublishYn = 1 AND c.articleDeleteYn = 0 ORDER BY c.articleModifyDatetime, c.articleWriteDatetime")
    List<TbComArticle> findArticyeForDashboardByBoardCode(@Param("boardCode") String boardCode);

}
