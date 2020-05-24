package com.karljeong.fourtysix.database.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbArticleGeneralLike;
import com.karljeong.fourtysix.database.entity.TbArticleGeneralLikePK;

@RepositoryRestResource(collectionResourceRel = "articleGeneralLikeEntity", path = "tbArticleGeneralLikeRepository")
public interface TbArticleGeneralLikeRepository
		extends PagingAndSortingRepository<TbArticleGeneralLike, TbArticleGeneralLikePK>,
		JpaSpecificationExecutor<TbArticleGeneralLike> {
}
