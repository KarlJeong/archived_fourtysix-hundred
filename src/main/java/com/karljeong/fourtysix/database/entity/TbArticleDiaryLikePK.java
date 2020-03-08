package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TB_ARTICLE_DIARY_LIKE database table.
 * 
 */
@Embeddable
public class TbArticleDiaryLikePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="USER_ID")
	private String userId;

	@Column(name="ARTICLE_ID", insertable=false, updatable=false)
	private String articleId;

	public TbArticleDiaryLikePK() {
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getArticleId() {
		return this.articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TbArticleDiaryLikePK)) {
			return false;
		}
		TbArticleDiaryLikePK castOther = (TbArticleDiaryLikePK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.articleId.equals(castOther.articleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.articleId.hashCode();
		
		return hash;
	}
}