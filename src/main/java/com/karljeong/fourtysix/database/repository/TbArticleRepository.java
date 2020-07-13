package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbArticle;
import com.karljeong.fourtysix.database.entity.TbArticleBlogLike;
import com.karljeong.fourtysix.database.entity.TbArticleBlogLikePK;
import com.karljeong.fourtysix.database.entity.TbComUser;

@RepositoryRestResource(collectionResourceRel = "articleEntity", path = "tbArticleRepository")
public interface TbArticleRepository
		extends PagingAndSortingRepository<TbArticle, BigInteger>,
		JpaSpecificationExecutor<TbArticle> {

	TbArticle findByArticleIdAndArticleCode(BigInteger articleId, String articleCode);
}
