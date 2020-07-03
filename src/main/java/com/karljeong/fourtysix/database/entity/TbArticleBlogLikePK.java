package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.servlet.http.HttpServletRequest;

import com.karljeong.fourtysix.utils.UserUtil;

/**
 * The primary key class for the TB_ARTICLE_BLOG_LIKE database table.
 *
 */
@Embeddable
public class TbArticleBlogLikePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "USER_ID", insertable = false, updatable = false)
    private BigInteger userId;

    @Column(name = "ARTICLE_ID", insertable = false, updatable = false)
    private BigInteger articleId;

	public TbArticleBlogLikePK() {
	}


	public BigInteger getUserId() {
        return userId;
    }


    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }


    public BigInteger getArticleId() {
        return articleId;
    }


    public void setArticleId(BigInteger articleId) {
        this.articleId = articleId;
    }


    @Override
    public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TbArticleBlogLikePK)) {
			return false;
		}
		TbArticleBlogLikePK castOther = (TbArticleBlogLikePK)other;
		return
			this.userId.equals(castOther.userId)
			&& this.articleId.equals(castOther.articleId);
	}

	@Override
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