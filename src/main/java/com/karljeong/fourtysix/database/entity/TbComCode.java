package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the TB_COM_CODE database table.
 *
 */
@Entity
@Table(name = "TB_COM_CODE")
@NamedQuery(name = "TbComCode.findAll", query = "SELECT t FROM TbComCode t")
public class TbComCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODE_ID")
	private BigInteger codeId;

	@Lob
	@Column(name = "CODE_DESCRIPTION")
	private String codeDescription;

	@Column(name = "CODE_GROUP_ID")
	private BigInteger codeGroupId;

	@Column(name = "CODE_NAME")
	private String codeName;

	@Column(name = "CODE_ORDER")
	private byte codeOrder;

	@Column(name = "CODE_VALUE")
	private String codeValue;

	@Column(name = "CREATE_DATETIME", insertable = false, updatable = false)
	private Timestamp createDatetime;

	@Column(name = "CREATE_USER_ID")
	private BigInteger createUserId;

	@Column(name = "UPDATE_DATETIME", insertable = false, updatable = false)
	private Timestamp updateDatetime;

	@Column(name = "UPDATE_USER_ID")
	private BigInteger updateUserId;

	@Column(name = "USE_YN")
	private byte useYn;

	public TbComCode() {
	}

	public BigInteger getCodeId() {
		return codeId;
	}

	public void setCodeId(BigInteger codeId) {
		this.codeId = codeId;
	}

	public String getCodeDescription() {
		return this.codeDescription;
	}

	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}

	public BigInteger getCodeGroupId() {
		return codeGroupId;
	}

	public void setCodeGroupId(BigInteger codeGroupId) {
		this.codeGroupId = codeGroupId;
	}

	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public byte getCodeOrder() {
		return this.codeOrder;
	}

	public void setCodeOrder(byte codeOrder) {
		this.codeOrder = codeOrder;
	}

	public String getCodeValue() {
		return this.codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
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

	public byte getUseYn() {
		return this.useYn;
	}

	public void setUseYn(byte useYn) {
		this.useYn = useYn;
	}

}