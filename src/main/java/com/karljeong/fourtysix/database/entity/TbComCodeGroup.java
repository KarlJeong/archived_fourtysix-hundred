package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.karljeong.fourtysix.utils.DateUtil;

/**
 * The persistent class for the TB_COM_CODE_GROUP database table.
 *
 */
@Entity
@Table(name = "TB_COM_CODE_GROUP")
@NamedQuery(name = "TbComCodeGroup.findAll", query = "SELECT t FROM TbComCodeGroup t")
public class TbComCodeGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODE_GROUP_ID")
	private BigInteger codeGroupId;

	@Lob
	@Column(name = "CODE_GROUP_DESCRIPTION")
	private String codeGroupDescription;

	@Column(name = "CODE_GROUP_NAME")
	private String codeGroupName;

	@Column(name = "CODE_GROUP_TYPE")
	private String codeGroupType;

	@Column(name = "CODE_GROUP_VALUE")
	private String codeGroupValue;

    @Column(name = "CREATE_DATETIME", updatable = false)
    private Timestamp createDatetime = DateUtil.getTimestamp();

    @Column(name = "CREATE_USER_ID", updatable = false)
    private BigInteger createUserId;

    @Column(name = "UPDATE_DATETIME", insertable = false)
    private Timestamp updateDatetime = DateUtil.getTimestamp();

    @Column(name = "UPDATE_USER_ID", insertable = false)
    private BigInteger updateUserId;

	@Column(name = "USE_YN")
	private byte useYn;

	@Transient
	private List<TbComCode> tbComCodes;

	public TbComCodeGroup() {
	}

	public BigInteger getCodeGroupId() {
		return codeGroupId;
	}

	public void setCodeGroupId(BigInteger codeGroupId) {
		this.codeGroupId = codeGroupId;
	}

	public String getCodeGroupDescription() {
		return this.codeGroupDescription;
	}

	public void setCodeGroupDescription(String codeGroupDescription) {
		this.codeGroupDescription = codeGroupDescription;
	}

	public String getCodeGroupName() {
		return this.codeGroupName;
	}

	public void setCodeGroupName(String codeGroupName) {
		this.codeGroupName = codeGroupName;
	}

	public String getCodeGroupType() {
		return this.codeGroupType;
	}

	public void setCodeGroupType(String codeGroupType) {
		this.codeGroupType = codeGroupType;
	}

	public String getCodeGroupValue() {
		return this.codeGroupValue;
	}

	public void setCodeGroupValue(String codeGroupValue) {
		this.codeGroupValue = codeGroupValue;
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

	public List<TbComCode> getTbComCodes() {
		return tbComCodes;
	}

	public void setTbComCodes(List<TbComCode> tbComCodes) {
		this.tbComCodes = tbComCodes;
	}

}