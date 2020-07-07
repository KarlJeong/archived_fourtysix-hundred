package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the TB_ARTICLE_BLOG_LIKE database table.
 *
 */
@Entity
@Table(name="TB_ARTICLE_BLOG_LIKE")
@NamedQuery(name="TbArticleBlogLike.findAll", query="SELECT t FROM TbArticleBlogLike t")
public class TbArticleBlogLike implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbArticleBlogLikePK id;

	@Column(name="CREATE_DATETIME")
	private Timestamp createDatetime;

	@Column(name="CREATE_USER_ID")
	private BigInteger createUserId;

	@Column(name="LIKE_YN")
	private byte likeYn;

	@Column(name="UPDATE_DATETIME")
	private Timestamp updateDatetime;

	@Column(name="UPDATE_USER_ID")
	private BigInteger updateUserId;

	public TbArticleBlogLike() {
	}

	public TbArticleBlogLikePK getId() {
		return this.id;
	}

	public void setId(TbArticleBlogLikePK id) {
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

}