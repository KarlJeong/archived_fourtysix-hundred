package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the TB_COM_ARTICLE database table.
 * 
 */
@Entity
@Table(name="TB_COM_ARTICLE")
@NamedQuery(name="TbComArticle.findAll", query="SELECT t FROM TbComArticle t")
public class TbComArticle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ARTICLE_ID")
	private String articleId;

	@Column(name="ARTICLE_CATEGORY_ID")
	private BigInteger articleCategoryId;

	@Lob
	@Column(name="ARTICLE_CONTENTS")
	private String articleContents;

	@Column(name="ARTICLE_DELETE_YN")
	private byte articleDeleteYn;

	@Column(name="ARTICLE_MODIFIER_ID")
	private BigInteger articleModifierId;

	@Column(name="ARTICLE_MODIFY_DATETIME")
	private String articleModifyDatetime;

	@Column(name="ARTICLE_TITLE")
	private String articleTitle;

	@Column(name="ARTICLE_VIEW_COUNT")
	private int articleViewCount;

	@Column(name="ARTICLE_WRITE_DATETIME")
	private Timestamp articleWriteDatetime;

	@Column(name="ARTICLE_WRITER_ID")
	private BigInteger articleWriterId;

	@Column(name="CREATE_DATETIME")
	private Timestamp createDatetime;

	@Column(name="CREATE_USER_ID")
	private BigInteger createUserId;

	@Column(name="UPDATE_DATETIME")
	private Timestamp updateDatetime;

	@Column(name="UPDATE_USER_ID")
	private BigInteger updateUserId;

	//bi-directional many-to-one association to TbComBoard
	@ManyToOne
	@JoinColumn(name="BOARD_ID", insertable = false, updatable = false)
	private TbComBoard tbComBoard;

	public TbComArticle() {
	}

	public String getArticleId() {
		return this.articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public BigInteger getArticleCategoryId() {
		return this.articleCategoryId;
	}

	public void setArticleCategoryId(BigInteger articleCategoryId) {
		this.articleCategoryId = articleCategoryId;
	}

	public String getArticleContents() {
		return this.articleContents;
	}

	public void setArticleContents(String articleContents) {
		this.articleContents = articleContents;
	}

	public byte getArticleDeleteYn() {
		return this.articleDeleteYn;
	}

	public void setArticleDeleteYn(byte articleDeleteYn) {
		this.articleDeleteYn = articleDeleteYn;
	}

	public BigInteger getArticleModifierId() {
		return this.articleModifierId;
	}

	public void setArticleModifierId(BigInteger articleModifierId) {
		this.articleModifierId = articleModifierId;
	}

	public String getArticleModifyDatetime() {
		return this.articleModifyDatetime;
	}

	public void setArticleModifyDatetime(String articleModifyDatetime) {
		this.articleModifyDatetime = articleModifyDatetime;
	}

	public String getArticleTitle() {
		return this.articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public int getArticleViewCount() {
		return this.articleViewCount;
	}

	public void setArticleViewCount(int articleViewCount) {
		this.articleViewCount = articleViewCount;
	}

	public Timestamp getArticleWriteDatetime() {
		return this.articleWriteDatetime;
	}

	public void setArticleWriteDatetime(Timestamp articleWriteDatetime) {
		this.articleWriteDatetime = articleWriteDatetime;
	}

	public BigInteger getArticleWriterId() {
		return this.articleWriterId;
	}

	public void setArticleWriterId(BigInteger articleWriterId) {
		this.articleWriterId = articleWriterId;
	}

	public Timestamp getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Timestamp createDatetime) {
		this.createDatetime = createDatetime;
	}

	public BigInteger getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(BigInteger createUserId) {
		this.createUserId = createUserId;
	}

	public Timestamp getUpdateDatetime() {
		return this.updateDatetime;
	}

	public void setUpdateDatetime(Timestamp updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public BigInteger getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(BigInteger updateUserId) {
		this.updateUserId = updateUserId;
	}

	public TbComBoard getTbComBoard() {
		return this.tbComBoard;
	}

	public void setTbComBoard(TbComBoard tbComBoard) {
		this.tbComBoard = tbComBoard;
	}

}