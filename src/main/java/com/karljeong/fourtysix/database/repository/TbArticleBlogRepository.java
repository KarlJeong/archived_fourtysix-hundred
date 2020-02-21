package com.karljeong.fourtysix.database.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbArticleBlog;

@RepositoryRestResource(collectionResourceRel = "articleEntity", path = "tbArticleBlog")
public interface TbArticleBlogRepository extends PagingAndSortingRepository<TbArticleBlog, Long> {

    List<TbArticleBlog> findByArticleTitle(@Param("articleTitle") String articleTitle);

    List<TbArticleBlog> findByArticleContents(@Param("articleContents") String articleContents);
}
