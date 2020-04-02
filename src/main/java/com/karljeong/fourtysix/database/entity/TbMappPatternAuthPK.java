package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the TB_MAPP_PATTERN_AUTH database table.
 *
 */
@Embeddable
public class TbMappPatternAuthPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PATTERN_ID", insertable=false, updatable=false)
	private BigInteger patternId;

	@Column(name="AUTH_ID", insertable=false, updatable=false)
	private BigInteger authId;

	public TbMappPatternAuthPK() {
	}

	public BigInteger getPatternId() {
        return patternId;
    }

    public void setPatternId(BigInteger patternId) {
        this.patternId = patternId;
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
		if (!(other instanceof TbMappPatternAuthPK)) {
			return false;
		}
		TbMappPatternAuthPK castOther = (TbMappPatternAuthPK)other;
		return
			this.patternId.equals(castOther.patternId)
			&& this.authId.equals(castOther.authId);
	}

	@Override
    public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.patternId.hashCode();
		hash = hash * prime + this.authId.hashCode();

		return hash;
	}
}