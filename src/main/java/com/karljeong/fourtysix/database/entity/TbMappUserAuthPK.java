package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the TB_MAPP_USER_AUTH database table.
 *
 */
@Embeddable
public class TbMappUserAuthPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="USER_ID", insertable=false, updatable=false)
	private BigInteger userId;

	@Column(name="AUTH_ID", insertable=false, updatable=false)
	private BigInteger authId;

	public TbMappUserAuthPK() {
	}

	public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getAuthId() {
        return authId;
    }

    public void setAuthId(BigInteger authId) {
        this.authId = authId;
    }

    @Override
    public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TbMappUserAuthPK)) {
			return false;
		}
		TbMappUserAuthPK castOther = (TbMappUserAuthPK)other;
		return
			this.userId.equals(castOther.userId)
			&& this.authId.equals(castOther.authId);
	}

	@Override
    public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.authId.hashCode();

		return hash;
	}
}