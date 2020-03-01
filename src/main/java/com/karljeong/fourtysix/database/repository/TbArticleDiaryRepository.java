package com.karljeong.fourtysix.database.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbArticleDiary;

@RepositoryRestResource(collectionResourceRel = "articleEntity", path = "tbArticleDiary")
public interface TbArticleDiaryRepository extends PagingAndSortingRepository<TbArticleDiary, Long> {

    List<TbArticleDiary> findByArticleTitle(@Param("articleTitle") String articleTitle);

    List<TbArticleDiary> findByArticleContents(@Param("articleContents") String articleContents);
}
