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

import com.karljeong.fourtysix.utils.DateUtil;


/**
 * The persistent class for the TB_COM_AUTH database table.
 *
 */
@Entity
@Table(name="TB_COM_AUTH")
@NamedQuery(name="TbComAuth.findAll", query="SELECT t FROM TbComAuth t")
public class TbComAuth implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AUTH_ID")
	private String authId;

	@Column(name="AUTH_CODE")
	private String authCode;

	@Lob
	@Column(name="AUTH_DESCRIPTION")
	private String authDescription;

	@Column(name="AUTH_NAME")
	private String authName;

	@Column(name = "CREATE_DATETIME", updatable = false)
	private Timestamp createDatetime = DateUtil.getTimestamp();

	@Column(name="CREATE_USER_ID", updatable = false)
	private BigInteger createUserId;

	@Column(name="UPDATE_DATETIME", insertable = false)
	private Timestamp updateDatetime = DateUtil.getTimestamp();

	@Column(name = "UPDATE_USER_ID", insertable = false)
	private BigInteger updateUserId;

    @Column(name = "USE_YN")
    private byte useYn;

	public TbComAuth() {
	}

	public String getAuthId() {
		return this.authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getAuthCode() {
		return this.authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthDescription() {
		return this.authDescription;
	}

	public void setAuthDescription(String authDescription) {
		this.authDescription = authDescription;
	}

	public String getAuthName() {
		return this.authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
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
        return useYn;
    }

    public void setUseYn(byte useYn) {
        this.useYn = useYn;
    }



}