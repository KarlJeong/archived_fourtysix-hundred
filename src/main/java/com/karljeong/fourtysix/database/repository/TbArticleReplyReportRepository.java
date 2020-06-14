package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbArticleReplyReport;

@RepositoryRestResource(collectionResourceRel = "articleReplyReportEntity", path = "tbArticleReplyReport")
public interface TbArticleReplyReportRepository extends PagingAndSortingRepository<TbArticleReplyReport, BigInteger>, JpaSpecificationExecutor<TbArticleReplyReport> {
}
