package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

import com.karljeong.fourtysix.utils.DateUtil;
import com.karljeong.fourtysix.utils.UserUtil;


/**
 * The persistent class for the TB_ARTICLE_REPORT database table.
 *
 */
@Entity
@Table(name="TB_ARTICLE_REPORT")
@NamedQuery(name="TbArticleReport.findAll", query="SELECT t FROM TbArticleReport t")
public class TbArticleReport implements Serializable {
    private static final long serialVersionUID = 890436894694236857L;

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ARTICLE_REPORT_ID")
	private BigInteger articleReportId;

    @Column(name="CREATE_DATETIME", updatable = false)
    private Timestamp createDatetime = DateUtil.getTimestamp();

    @Column(name="CREATE_USER_ID", updatable = false)
    private BigInteger createUserId;

    @Column(name="REPORTER_USER_ID", updatable = false)
    private BigInteger reporterUserId;

	@Column(name="BOARD_CODE")
	private String boardCode;

	@Column(name="ARTICLE_ID")
	private BigInteger articleId;

    @Column(name="REPORT_CATEGORY_CODE")
    private String reportCategoryCode;

	@Lob
	@Column(name="REPORT_DETAIL")
	private String reportDetail;


	public TbArticleReport() {
	}

	public BigInteger getArticleId() {
		return this.articleId;
	}

	public void setArticleId(BigInteger articleId) {
		this.articleId = articleId;
	}

    public BigInteger getArticleReportId() {
        return articleReportId;
    }

    public void setArticleReportId(BigInteger articleReportId) {
        this.articleReportId = articleReportId;
    }

    public Timestamp getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Timestamp createDatetime) {
        this.createDatetime = createDatetime;
    }

    public BigInteger getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(BigInteger createUserId) {
        this.createUserId = createUserId;
    }

    public String getBoardCode() {
        return boardCode;
    }

    public void setBoardCode(String boardCode) {
        this.boardCode = boardCode;
    }

    public String getReportCategoryCode() {
        return reportCategoryCode;
    }

    public void setReportCategoryCode(String reportCategoryCode) {
        this.reportCategoryCode = reportCategoryCode;
    }

    public String getReportDetail() {
        return reportDetail;
    }

    public void setReportDetail(String reportDetail) {
        this.reportDetail = reportDetail;
    }

    public void setUserInfo(HttpServletRequest request) {
        BigInteger userId = UserUtil.getUserId(request);
        this.createUserId = userId;
        this.reporterUserId = userId;
    }


}