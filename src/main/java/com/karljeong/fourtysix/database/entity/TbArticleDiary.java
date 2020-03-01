package com.karljeong.fourtysix.database.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TbArticleDiary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long articleId;
    private Long articleCategoryId;
    private String articleTitle;
    private String articleContents;
    private Long articleWriterId;
    private String articleWriteDatetime;
    private Long articleModifierId;
    private String articleModoifyDatetime;
    private Integer articleViewCount;
    private Integer articleLikeCount;
    private Integer articleReplyCount;
    private Integer articleReportCount;
    private Integer articleDeleteYn;
    private Integer articleBanYn;

}
