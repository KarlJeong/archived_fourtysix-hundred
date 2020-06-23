package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbArticleBlog;

@RepositoryRestResource(collectionResourceRel = "articleEntity", path = "tbArticleBlogRepository")
public interface TbArticleBlogRepository extends PagingAndSortingRepository<TbArticleBlog, BigInteger>, JpaSpecificationExecutor<TbArticleBlog> {

    List<TbArticleBlog> findByArticleTitle(@Param("articleTitle") String articleTitle);

    List<TbArticleBlog> findByArticleContents(@Param("articleContents") String articleContents);
}
