package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TB_MAPP_BOARD_CATEGORY database table.
 * 
 */
@Embeddable
public class TbMappBoardCategoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ARTICLE_CATEGORY_ID", insertable=false, updatable=false)
	private String articleCategoryId;

	@Column(name="BOARD_ID", insertable=false, updatable=false)
	private String boardId;

	public TbMappBoardCategoryPK() {
	}
	public String getArticleCategoryId() {
		return this.articleCategoryId;
	}
	public void setArticleCategoryId(String articleCategoryId) {
		this.articleCategoryId = articleCategoryId;
	}
	public String getBoardId() {
		return this.boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TbMappBoardCategoryPK)) {
			return false;
		}
		TbMappBoardCategoryPK castOther = (TbMappBoardCategoryPK)other;
		return 
			this.articleCategoryId.equals(castOther.articleCategoryId)
			&& this.boardId.equals(castOther.boardId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.articleCategoryId.hashCode();
		hash = hash * prime + this.boardId.hashCode();
		
		return hash;
	}
}