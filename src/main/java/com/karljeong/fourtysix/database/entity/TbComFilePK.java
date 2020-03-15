package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the TB_COM_FILE database table.
 *
 */
@Embeddable
public class TbComFilePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FILE_ID")
	private BigInteger fileId;

	@Column(name="FILE_REF_ID")
	private int fileRefId;

	@Column(name="FILE_ORDER")
	private int fileOrder;

	public TbComFilePK() {
	}
	public BigInteger getFileId() {
		return this.fileId;
	}
	public void setFileId(BigInteger fileId) {
		this.fileId = fileId;
	}
	public int getFileRefId() {
		return this.fileRefId;
	}
	public void setFileRefId(int fileRefId) {
		this.fileRefId = fileRefId;
	}
	public int getFileOrder() {
		return this.fileOrder;
	}
	public void setFileOrder(int fileOrder) {
		this.fileOrder = fileOrder;
	}

	@Override
    public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TbComFilePK)) {
			return false;
		}
		TbComFilePK castOther = (TbComFilePK)other;
		return
			this.fileId.equals(castOther.fileId)
			&& (this.fileRefId == castOther.fileRefId)
			&& (this.fileOrder == castOther.fileOrder);
	}

	@Override
    public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fileId.hashCode();
		hash = hash * prime + this.fileRefId;
		hash = hash * prime + this.fileOrder;

		return hash;
	}
}