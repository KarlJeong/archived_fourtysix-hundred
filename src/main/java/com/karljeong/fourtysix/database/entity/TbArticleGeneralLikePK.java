package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.servlet.http.HttpServletRequest;

import com.karljeong.fourtysix.utils.UserUtil;

/**
 * The primary key class for the TB_ARTICLE_GENERAL_LIKE database table.
 *
 */
@Embeddable
public class TbArticleGeneralLikePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "USER_ID", insertable = false, updatable = false)
	private BigInteger userId;

	@Column(name = "ARTICLE_ID", insertable = false, updatable = false)
	private BigInteger articleId;

	public TbArticleGeneralLikePK() {
	}

	public BigInteger getUserId() {
		return this.userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public BigInteger getArticleId() {
		return this.articleId;
	}

	public void setArticleId(BigInteger articleId) {
		this.articleId = articleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TbArticleGeneralLikePK)) {
			return false;
		}
		TbArticleGeneralLikePK castOther = (TbArticleGeneralLikePK) other;
		return this.userId.equals(castOther.userId) && this.articleId.equals(castOther.articleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.articleId.hashCode();

		return hash;
	}

	public void setUserInfo(HttpServletRequest request) {
		BigInteger userId = UserUtil.getUserId();
		this.userId = userId;
	}
}