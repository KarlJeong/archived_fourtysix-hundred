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
import javax.persistence.Transient;

import com.karljeong.fourtysix.utils.DateUtil;


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
	private BigInteger articleId;

	@Column(name="ARTICLE_CATEGORY_ID")
	private BigInteger articleCategoryId;

	@Lob
	@Column(name="ARTICLE_CONTENTS")
	private String articleContents;

	@Column(name="ARTICLE_DELETE_YN")
	private byte articleDeleteYn;

    @Column(name="ARTICLE_PUBLISH_YN")
    private byte articlePublishYn;

	@Column(name="ARTICLE_MODIFIER_ID", insertable = false)
	private BigInteger articleModifierId;

	@Column(name="ARTICLE_MODIFY_DATETIME", insertable = false)
	private Timestamp articleModifyDatetime = DateUtil.getTimestamp();

	@Column(name="ARTICLE_TITLE")
	private String articleTitle;

	@Column(name="ARTICLE_VIEW_COUNT", updatable = false)
	private int articleViewCount = 0;

	@Column(name="ARTICLE_WRITE_DATETIME", updatable = false)
	private Timestamp articleWriteDatetime = DateUtil.getTimestamp();

	@Column(name="ARTICLE_WRITER_ID", updatable = false)
	private BigInteger articleWriterId;

	@Column(name="BOARD_ID")
	private BigInteger boardId;

	@Column(name = "CREATE_DATETIME", updatable = false)
    private Timestamp createDatetime = DateUtil.getTimestamp();

    @Column(name="CREATE_USER_ID", updatable = false)
    private BigInteger createUserId;

    @Column(name="UPDATE_DATETIME", insertable = false)
    private Timestamp updateDatetime = DateUtil.getTimestamp();

    @Column(name = "UPDATE_USER_ID", insertable = false)
    private BigInteger updateUserId;

    @Transient
    private TbComBoard tbComBoard;

    @Transient
    private String articleWriterUserName;

	public TbComArticle() {
	}

	public BigInteger getArticleId() {
		return this.articleId;
	}

	public void setArticleId(BigInteger articleId) {
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

	public byte getArticlePublishYn() {
        return articlePublishYn;
    }

    public void setArticlePublishYn(byte articlePublishYn) {
        this.articlePublishYn = articlePublishYn;
    }

    public BigInteger getArticleModifierId() {
		return this.articleModifierId;
	}

	public void setArticleModifierId(BigInteger articleModifierId) {
		this.articleModifierId = articleModifierId;
	}

	public Timestamp getArticleModifyDatetime() {
		return this.articleModifyDatetime;
	}

	public void setArticleModifyDatetime(Timestamp articleModifyDatetime) {
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

	public BigInteger getBoardId() {
		return this.boardId;
	}

	public void setBoardId(BigInteger boardId) {
		this.boardId = boardId;
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
        return tbComBoard;
    }

    public void setTbComBoard(TbComBoard tbComBoard) {
        this.tbComBoard = tbComBoard;
    }

    public String getArticleWriterUserName() {
        return articleWriterUserName;
    }

    public void setArticleWriterUserName(String articleWriterUserName) {
        this.articleWriterUserName = articleWriterUserName;
    }



}