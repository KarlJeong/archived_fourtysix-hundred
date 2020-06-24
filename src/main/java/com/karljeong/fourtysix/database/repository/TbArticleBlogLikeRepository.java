package com.karljeong.fourtysix.database.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbArticleBlogLike;
import com.karljeong.fourtysix.database.entity.TbArticleBlogLikePK;

@RepositoryRestResource(collectionResourceRel = "articleBlogLikeEntity", path = "tbArticleBlogLikeRepository")
public interface TbArticleBlogLikeRepository
		extends PagingAndSortingRepository<TbArticleBlogLike, TbArticleBlogLikePK>,
		JpaSpecificationExecutor<TbArticleBlogLike> {
}
