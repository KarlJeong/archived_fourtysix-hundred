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
@Table(name="TB_ARTICLE_REPLY_REPORT")
@NamedQuery(name="TbArticleReplyReport.findAll", query="SELECT t FROM TbArticleReplyReport t")
public class TbArticleReplyReport implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -581292027841401593L;

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ARTICLE_REPLY_REPORT_ID")
	private BigInteger articleReplyReportId;

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

    @Column(name="REPLY_ID")
    private BigInteger replyId;

    @Column(name="REPORT_CATEGORY_CODE")
    private String reportCategoryCode;

	@Lob
	@Column(name="REPORT_DETAIL")
	private String reportDetail;


	public TbArticleReplyReport() {
	}


    public BigInteger getArticleReplyReportId() {
        return articleReplyReportId;
    }


    public void setArticleReplyReportId(BigInteger articleReplyReportId) {
        this.articleReplyReportId = articleReplyReportId;
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


    public BigInteger getReporterUserId() {
        return reporterUserId;
    }


    public void setReporterUserId(BigInteger reporterUserId) {
        this.reporterUserId = reporterUserId;
    }


    public String getBoardCode() {
        return boardCode;
    }


    public void setBoardCode(String boardCode) {
        this.boardCode = boardCode;
    }


    public BigInteger getArticleId() {
        return articleId;
    }


    public void setArticleId(BigInteger articleId) {
        this.articleId = articleId;
    }


    public BigInteger getReplyId() {
        return replyId;
    }


    public void setReplyId(BigInteger replyId) {
        this.replyId = replyId;
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
        BigInteger userId = UserUtil.getUserId();
        this.createUserId = userId;
        this.reporterUserId = userId;
    }


}