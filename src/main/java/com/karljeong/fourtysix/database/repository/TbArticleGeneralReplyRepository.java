package com.karljeong.fourtysix.database.repository;

import java.math.BigInteger;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.karljeong.fourtysix.database.entity.TbArticleGeneralReply;

@RepositoryRestResource(collectionResourceRel = "articleGeneralReplyEntity", path = "tbArticleGeneralReplyRepository")
public interface TbArticleGeneralReplyRepository extends PagingAndSortingRepository<TbArticleGeneralReply, BigInteger>,
		JpaSpecificationExecutor<TbArticleGeneralReply> {

	@Query(value = "SELECT FN_GET_USER_NAME(:userId)", nativeQuery = true)
	String findReplyWriterName(@Param("userId") BigInteger userId);


	@Query
	(
	        value ="WITH RECURSIVE tmp1 AS " +
	                "(" +
	                "    SELECT *, CONCAT(IFNULL(REPLY_ORDER, 0)) AS PATH " +
	                "    FROM TB_ARTICLE_GENERAL_REPLY WHERE PRIOR_REPLY_ID IS NULL AND ARTICLE_ID = :articleId " +
	                "    UNION ALL " +
	                "    SELECT e.*, CONCAT(t.PATH, ',', e.REPLY_ORDER) AS PATH " +
	                "    FROM tmp1 t JOIN TB_ARTICLE_GENERAL_REPLY e ON t.REPLY_ID = e.PRIOR_REPLY_ID WHERE e.ARTICLE_ID = t.ARTICLE_ID " +
	                ") " +
	                "SELECT * " +
	                "FROM tmp1 " +
	                "ORDER BY PATH",
	        countQuery ="WITH RECURSIVE tmp1 AS " +
                    "(" +
                    "    SELECT *, CONCAT(IFNULL(REPLY_ORDER, 0)) AS PATH " +
                    "    FROM TB_ARTICLE_GENERAL_REPLY WHERE PRIOR_REPLY_ID IS NULL AND ARTICLE_ID = :articleId " +
                    "    UNION ALL " +
                    "    SELECT e.*, CONCAT(t.PATH, ',', e.REPLY_ORDER) AS PATH " +
                    "    FROM tmp1 t JOIN TB_ARTICLE_GENERAL_REPLY e ON t.REPLY_ID = e.PRIOR_REPLY_ID WHERE e.ARTICLE_ID = t.ARTICLE_ID " +
                    ") " +
                    "SELECT COUNT(1) " +
                    "FROM tmp1 " +
                    "ORDER BY PATH",
	        nativeQuery = true
	)
	Page<TbArticleGeneralReply> findByArticleId(@Param("articleId") BigInteger articleId, Pageable pageable);

	@Transactional
    @Modifying
    @Query
    (
                value = "INSERT INTO TB_ARTICLE_GENERAL_REPLY (CREATE_USER_ID, ARTICLE_ID, PRIOR_REPLY_ID, REPLY_CONTENTS, REPLY_LEVEL, REPLY_ORDER, REPLY_WRITER_ID) " +
                        "SELECT :tbArticleGeneralReply.createUserId " +
                        "     , C.ARTICLE_ID " +
                        "     , :tbArticleGeneralReply.priorReplyId " +
                        "     , :tbArticleGeneralReply.replyContents " +
                        "     , C.REPLY_LEVEL +1 " +
                        "     , (SELECT IFNULL(MAX(SUB.REPLY_ORDER) +1, 1) FROM TB_ARTICLE_GENERAL_REPLY SUB WHERE SUB.PRIOR_REPLY_ID = :tbArticleGeneralReply.priorReplyId AND SUB.REPLY_LEVEL = C.REPLY_LEVEL +1) " +
                        "     , :tbArticleGeneralReply.replyWriterId " +
                        "FROM TB_ARTICLE_GENERAL_REPLY C "+
                        "WHERE C.REPLY_ID = :tbArticleGeneralReply.priorReplyId "+
                        ")",
                nativeQuery = true
    )
    int saveReplyDynamic(@Param("tbArticleGeneralReply") TbArticleGeneralReply tbArticleGeneralReply);
}
