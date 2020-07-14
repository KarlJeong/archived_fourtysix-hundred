package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import com.karljeong.fourtysix.utils.DateUtil;
import com.karljeong.fourtysix.utils.UserUtil;

/**
 * The persistent class for the TB_ARTICLE database table.
 *
 */
@Entity
@Table(name = "TB_ARTICLE")
@NamedQuery(name = "TbArticle.findAll", query = "SELECT t FROM TbArticle t")
public class TbArticle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "CREATE_DATETIME", updatable = false)
	private Timestamp createDatetime = DateUtil.getTimestamp();

	@Column(name = "CREATE_USER_ID", updatable = false)
	private BigInteger createUserId;

	@Column(name = "UPDATE_DATETIME", insertable = false)
	private Timestamp updateDatetime;

	@Column(name = "UPDATE_USER_ID", insertable = false)
	private BigInteger updateUserId;

	@Column(name = "ARTICLE_ID")
	private BigInteger articleId;

	@Column(name = "ARTICLE_CODE")
	private String articleCode;

	@Column(name = "ARTICLE_WRITE_DATETIME", updatable = false)
	private Timestamp articleWriteDatetime = DateUtil.getTimestamp();

	@Column(name = "ARTICLE_WRITER_ID", updatable = false)
	private BigInteger articleWriterId;

	@ManyToOne(targetEntity = TbComUser.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ARTICLE_WRITER_ID", insertable = false, updatable = false)
	private TbComUser articleBlogWriter;

	@Column(name = "ARTICLE_MODIFIER_ID", insertable = false)
	private BigInteger articleModifierId;

	@ManyToOne(targetEntity = TbComUser.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ARTICLE_MODIFIER_ID", insertable = false, updatable = false)
	private TbComUser articleBlogModifier;

	@Column(name = "ARTICLE_MODIFY_DATETIME", insertable = false)
	private Timestamp articleModifyDatetime = DateUtil.getTimestamp();

	@Transient
	private int articleCodeCount;

	@Transient
	private String articleTitle;

	@Transient
	private int articleReplyCount;

	@Transient
	private int articleViewCount;

	@Transient
	private int articleLikeCount;

	@Transient
	private String articleCategoryCv;

	public TbArticle() {
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
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

	public Timestamp getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Timestamp updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public BigInteger getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(BigInteger updateUserId) {
		this.updateUserId = updateUserId;
	}

	public BigInteger getArticleId() {
		return articleId;
	}

	public void setArticleId(BigInteger articleId) {
		this.articleId = articleId;
	}

	public String getArticleCode() {
		return articleCode;
	}

	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

	public Timestamp getArticleWriteDatetime() {
		return articleWriteDatetime;
	}

	public void setArticleWriteDatetime(Timestamp articleWriteDatetime) {
		this.articleWriteDatetime = articleWriteDatetime;
	}

	public BigInteger getArticleWriterId() {
		return articleWriterId;
	}

	public void setArticleWriterId(BigInteger articleWriterId) {
		this.articleWriterId = articleWriterId;
	}

	public TbComUser getArticleBlogWriter() {
		return articleBlogWriter;
	}

	public void setArticleBlogWriter(TbComUser articleBlogWriter) {
		this.articleBlogWriter = articleBlogWriter;
	}

	public BigInteger getArticleModifierId() {
		return articleModifierId;
	}

	public void setArticleModifierId(BigInteger articleModifierId) {
		this.articleModifierId = articleModifierId;
	}

	public TbComUser getArticleBlogModifier() {
		return articleBlogModifier;
	}

	public void setArticleBlogModifier(TbComUser articleBlogModifier) {
		this.articleBlogModifier = articleBlogModifier;
	}

	public Timestamp getArticleModifyDatetime() {
		return articleModifyDatetime;
	}

	public void setArticleModifyDatetime(Timestamp articleModifyDatetime) {
		this.articleModifyDatetime = articleModifyDatetime;
	}

	public int getArticleCodeCount() {
		return articleCodeCount;
	}

	public void setArticleCodeCount(int articleCodeCount) {
		this.articleCodeCount = articleCodeCount;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public int getArticleReplyCount() {
		return articleReplyCount;
	}

	public void setArticleReplyCount(int articleReplyCount) {
		this.articleReplyCount = articleReplyCount;
	}

	public int getArticleViewCount() {
		return articleViewCount;
	}

	public void setArticleViewCount(int articleViewCount) {
		this.articleViewCount = articleViewCount;
	}

	public int getArticleLikeCount() {
		return articleLikeCount;
	}

	public void setArticleLikeCount(int articleLikeCount) {
		this.articleLikeCount = articleLikeCount;
	}

	public String getArticleCategoryCv() {
		return articleCategoryCv;
	}

	public void setArticleCategoryCv(String articleCategoryCv) {
		this.articleCategoryCv = articleCategoryCv;
	}

	public void setUserInfo(HttpServletRequest request) {
		BigInteger userId = UserUtil.getUserId();
		this.createUserId = userId;
		this.updateUserId = userId;
		this.articleWriterId = userId;
		this.articleModifierId = userId;
	}

}