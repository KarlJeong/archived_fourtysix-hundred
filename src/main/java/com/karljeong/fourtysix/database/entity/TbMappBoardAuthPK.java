package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the TB_MAPP_BOARD_AUTH database table.
 *
 */
@Embeddable
public class TbMappBoardAuthPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "BOARD_ID", insertable = false, updatable = false)
	private BigInteger boardId;

	@Column(name = "AUTH_ID", insertable = false, updatable = false)
	private BigInteger authId;

	public TbMappBoardAuthPK() {
	}

	public BigInteger getBoardId() {
		return boardId;
	}

	public void setBoardId(BigInteger boardId) {
		this.boardId = boardId;
	}

	public BigInteger getAuthId() {
		return authId;
	}

	public void setAuthId(BigInteger authId) {
		this.authId = authId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TbMappBoardAuthPK)) {
			return false;
		}
		TbMappBoardAuthPK castOther = (TbMappBoardAuthPK) other;
		return this.boardId.equals(castOther.boardId) && this.authId.equals(castOther.authId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.boardId.hashCode();
		hash = hash * prime + this.authId.hashCode();

		return hash;
	}
}