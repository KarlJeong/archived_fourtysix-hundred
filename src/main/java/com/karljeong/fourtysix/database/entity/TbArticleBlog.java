package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import com.karljeong.fourtysix.utils.DateUtil;
import com.karljeong.fourtysix.utils.UserUtil;


/**
 * The persistent class for the TB_ARTICLE_BLOG database table.
 *
 */
@Entity
@Table(name="TB_ARTICLE_BLOG")
@NamedQuery(name="TbArticleBlog.findAll", query="SELECT t FROM TbArticleBlog t")
public class TbArticleBlog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ARTICLE_ID")
	private BigInteger articleId;

	@Column(name="ARTICLE_BAN_YN")
	private byte articleBanYn;

	@Column(name="ARTICLE_CATEGORY_CV")
	private String articleCategoryCv;

	@Lob
	@Column(name="ARTICLE_CONTENTS")
	private String articleContents;

	@Column(name="ARTICLE_DELETE_YN")
	private byte articleDeleteYn;

	@Column(name="ARTICLE_LIKE_COUNT")
	private int articleLikeCount;

	@Column(name = "ARTICLE_MODIFIER_ID", insertable = false)
	private BigInteger articleModifierId;

    @ManyToOne(targetEntity=TbComUser.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="ARTICLE_MODIFIER_ID", insertable = false, updatable = false)
    private TbComUser articleBlogModifier;

	@Column(name = "ARTICLE_MODIFY_DATETIME", insertable = false)
    private Timestamp articleModifyDatetime = DateUtil.getTimestamp();

	@Column(name="ARTICLE_REPLY_COUNT")
	private int articleReplyCount;

	@Column(name="ARTICLE_REPORT_COUNT")
	private int articleReportCount;

	@Column(name="ARTICLE_TITLE")
	private String articleTitle;

	@Column(name="ARTICLE_SUMMARY")
	private String articleSummary;

	@Column(name = "ARTICLE_VIEW_COUNT", updatable = false)
    private int articleViewCount = 0;

	@Column(name = "ARTICLE_WRITE_DATETIME", updatable = false)
    private Timestamp articleWriteDatetime = DateUtil.getTimestamp();

    @Column(name = "ARTICLE_WRITER_ID", updatable = false)
    private BigInteger articleWriterId;

    @ManyToOne(targetEntity=TbComUser.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="ARTICLE_WRITER_ID", insertable = false, updatable = false)
    private TbComUser articleBlogWriter;


	@Column(name="CREATE_DATETIME", updatable = false)
	private Timestamp createDatetime = DateUtil.getTimestamp();

	@Column(name="CREATE_USER_ID", updatable = false)
	private BigInteger createUserId;

	@Column(name="THUMBNAIL_FILE_ID")
	private BigInteger thumbnailFileId;

	@Column(name="UPDATE_DATETIME", insertable = false)
	private Timestamp updateDatetime;

	@Column(name="UPDATE_USER_ID", insertable = false)
	private BigInteger updateUserId;

    @Column(name="REQUEST_PUBLISH_YN")
    private byte requestPublishYn;

    @Column(name="PUBLISH_YN")
    private byte publishYn;

    @Transient
    private String articleCategoryName;

	public TbArticleBlog() {
	}

	public BigInteger getArticleId() {
		return this.articleId;
	}

	public void setArticleId(BigInteger articleId) {
		this.articleId = articleId;
	}

	public byte getArticleBanYn() {
		return this.articleBanYn;
	}

	public void setArticleBanYn(byte articleBanYn) {
		this.articleBanYn = articleBanYn;
	}

	public String getArticleCategoryCv() {
        return articleCategoryCv;
    }

    public void setArticleCategoryCv(String articleCategoryCv) {
        this.articleCategoryCv = articleCategoryCv;
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

	public Timestamp getArticleModifyDatetime() {
        return articleModifyDatetime;
    }

    public void setArticleModifyDatetime(Timestamp articleModifyDatetime) {
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

	public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
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

    public byte getRequestPublishYn() {
        return requestPublishYn;
    }

    public void setRequestPublishYn(byte requestPublishYn) {
        this.requestPublishYn = requestPublishYn;
    }

    public byte getPublishYn() {
        return publishYn;
    }

    public void setPublishYn(byte publishYn) {
        this.publishYn = publishYn;
    }

    public String getArticleCategoryName() {
        return articleCategoryName;
    }

    public void setArticleCategoryName(String articleCategoryName) {
        this.articleCategoryName = articleCategoryName;
    }
    
	public TbComUser getArticleBlogModifier() {
		return articleBlogModifier;
	}

	public void setArticleBlogModifier(TbComUser articleBlogModifier) {
		this.articleBlogModifier = articleBlogModifier;
	}

	public TbComUser getArticleBlogWriter() {
		return articleBlogWriter;
	}

	public void setArticleBlogWriter(TbComUser articleBlogWriter) {
		this.articleBlogWriter = articleBlogWriter;
	}

	public void setUserInfo(HttpServletRequest request) {
        BigInteger userId = UserUtil.getUserId();
        this.createUserId = userId;
        this.updateUserId = userId;
        this.articleWriterId = userId;
        this.articleModifierId = userId;
    }

}