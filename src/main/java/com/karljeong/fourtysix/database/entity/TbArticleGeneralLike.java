package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

import com.karljeong.fourtysix.utils.DateUtil;
import com.karljeong.fourtysix.utils.UserUtil;

/**
 * The persistent class for the TB_ARTICLE_GENERAL_LIKE database table.
 *
 */
@Entity
@Table(name = "TB_ARTICLE_GENERAL_LIKE")
@NamedQuery(name = "TbArticleGeneralLike.findAll", query = "SELECT t FROM TbArticleGeneralLike t")
public class TbArticleGeneralLike implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbArticleGeneralLikePK id;

	@Column(name = "CREATE_DATETIME", updatable = false)
	private Timestamp createDatetime = DateUtil.getTimestamp();

	@Column(name = "CREATE_USER_ID", updatable = false)
	private BigInteger createUserId;

	@Column(name = "UPDATE_DATETIME", insertable = false)
	private Timestamp updateDatetime;

	@Column(name = "UPDATE_USER_ID", insertable = false)
	private BigInteger updateUserId;

	@Column(name = "LIKE_YN")
	private byte likeYn;

	public TbArticleGeneralLike() {
	}

	public TbArticleGeneralLikePK getId() {
		return this.id;
	}

	public void setId(TbArticleGeneralLikePK id) {
		this.id = id;
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

	public byte getLikeYn() {
		return this.likeYn;
	}

	public void setLikeYn(byte likeYn) {
		this.likeYn = likeYn;
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

	public void setUserInfo(HttpServletRequest request) {
		BigInteger userId = UserUtil.getUserId();
		this.createUserId = userId;
		this.updateUserId = userId;
	}

}