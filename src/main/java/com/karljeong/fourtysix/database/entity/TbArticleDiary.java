package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the TB_ARTICLE_DIARY database table.
 *
 */
@Entity
@Table(name="TB_ARTICLE_DIARY")
@NamedQuery(name="TbArticleDiary.findAll", query="SELECT t FROM TbArticleDiary t")
public class TbArticleDiary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ARTICLE_ID")
	private String articleId;

	@Column(name="ARTICLE_BAN_YN")
	private byte articleBanYn;

	@Lob
	@Column(name="ARTICLE_CONTENTS")
	private String articleContents;

	@Column(name="ARTICLE_DELETE_YN")
	private byte articleDeleteYn;

	@Column(name="ARTICLE_LIKE_COUNT")
	private int articleLikeCount;

	@Column(name="ARTICLE_MODIFIER_ID")
	private BigInteger articleModifierId;

	@Column(name="ARTICLE_MODIFY_DATETIME")
	private String articleModifyDatetime;

	@Column(name="ARTICLE_REPLY_COUNT")
	private int articleReplyCount;

	@Column(name="ARTICLE_REPORT_COUNT")
	private int articleReportCount;

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

	@Column(name="THUMBNAIL_FILE_ID")
	private BigInteger thumbnailFileId;

	@Column(name="UPDATE_DATETIME")
	private Timestamp updateDatetime;

	@Column(name="UPDATE_USER_ID")
	private BigInteger updateUserId;

	//bi-directional many-to-one association to TbComCategory
	@ManyToOne
	@JoinColumn(name="ARTICLE_CATEGORY_ID", insertable = false, updatable = false)
	private TbComCategory tbComCategory;

	//bi-directional many-to-one association to TbArticleDiaryFile
	@OneToMany(mappedBy="tbArticleDiary")
	private List<TbArticleDiaryFile> tbArticleDiaryFiles;

	//bi-directional many-to-one association to TbArticleDiaryLike
	@OneToMany(mappedBy="tbArticleDiary")
	private List<TbArticleDiaryLike> tbArticleDiaryLikes;

	//bi-directional many-to-one association to TbArticleDiaryReply
	@OneToMany(mappedBy="tbArticleDiary")
	private List<TbArticleDiaryReply> tbArticleDiaryReplies;

	public TbArticleDiary() {
	}

	public String getArticleId() {
		return this.articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public byte getArticleBanYn() {
		return this.articleBanYn;
	}

	public void setArticleBanYn(byte articleBanYn) {
		this.articleBanYn = articleBanYn;
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

	public int getArticleLikeCount() {
		return this.articleLikeCount;
	}

	public void setArticleLikeCount(int articleLikeCount) {
		this.articleLikeCount = articleLikeCount;
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

	public int getArticleReplyCount() {
		return this.articleReplyCount;
	}

	public void setArticleReplyCount(int articleReplyCount) {
		this.articleReplyCount = articleReplyCount;
	}

	public int getArticleReportCount() {
		return this.articleReportCount;
	}

	public void setArticleReportCount(int articleReportCount) {
		this.articleReportCount = articleReportCount;
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

	public BigInteger getThumbnailFileId() {
		return this.thumbnailFileId;
	}

	public void setThumbnailFileId(BigInteger thumbnailFileId) {
		this.thumbnailFileId = thumbnailFileId;
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

	public TbComCategory getTbComCategory() {
		return this.tbComCategory;
	}

	public void setTbComCategory(TbComCategory tbComCategory) {
		this.tbComCategory = tbComCategory;
	}

	public List<TbArticleDiaryFile> getTbArticleDiaryFiles() {
		return this.tbArticleDiaryFiles;
	}

	public void setTbArticleDiaryFiles(List<TbArticleDiaryFile> tbArticleDiaryFiles) {
		this.tbArticleDiaryFiles = tbArticleDiaryFiles;
	}

	public TbArticleDiaryFile addTbArticleDiaryFile(TbArticleDiaryFile tbArticleDiaryFile) {
		getTbArticleDiaryFiles().add(tbArticleDiaryFile);
		tbArticleDiaryFile.setTbArticleDiary(this);

		return tbArticleDiaryFile;
	}

	public TbArticleDiaryFile removeTbArticleDiaryFile(TbArticleDiaryFile tbArticleDiaryFile) {
		getTbArticleDiaryFiles().remove(tbArticleDiaryFile);
		tbArticleDiaryFile.setTbArticleDiary(null);

		return tbArticleDiaryFile;
	}

	public List<TbArticleDiaryLike> getTbArticleDiaryLikes() {
		return this.tbArticleDiaryLikes;
	}

	public void setTbArticleDiaryLikes(List<TbArticleDiaryLike> tbArticleDiaryLikes) {
		this.tbArticleDiaryLikes = tbArticleDiaryLikes;
	}

	public TbArticleDiaryLike addTbArticleDiaryLike(TbArticleDiaryLike tbArticleDiaryLike) {
		getTbArticleDiaryLikes().add(tbArticleDiaryLike);
		tbArticleDiaryLike.setTbArticleDiary(this);

		return tbArticleDiaryLike;
	}

	public TbArticleDiaryLike removeTbArticleDiaryLike(TbArticleDiaryLike tbArticleDiaryLike) {
		getTbArticleDiaryLikes().remove(tbArticleDiaryLike);
		tbArticleDiaryLike.setTbArticleDiary(null);

		return tbArticleDiaryLike;
	}

	public List<TbArticleDiaryReply> getTbArticleDiaryReplies() {
		return this.tbArticleDiaryReplies;
	}

	public void setTbArticleDiaryReplies(List<TbArticleDiaryReply> tbArticleDiaryReplies) {
		this.tbArticleDiaryReplies = tbArticleDiaryReplies;
	}

	public TbArticleDiaryReply addTbArticleDiaryReply(TbArticleDiaryReply tbArticleDiaryReply) {
		getTbArticleDiaryReplies().add(tbArticleDiaryReply);
		tbArticleDiaryReply.setTbArticleDiary(this);

		return tbArticleDiaryReply;
	}

	public TbArticleDiaryReply removeTbArticleDiaryReply(TbArticleDiaryReply tbArticleDiaryReply) {
		getTbArticleDiaryReplies().remove(tbArticleDiaryReply);
		tbArticleDiaryReply.setTbArticleDiary(null);

		return tbArticleDiaryReply;
	}

}