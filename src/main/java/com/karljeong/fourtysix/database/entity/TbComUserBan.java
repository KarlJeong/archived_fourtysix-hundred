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
 * The persistent class for the TB_COM_USER_BAN database table.
 *
 */
@Entity
@Table(name="TB_COM_USER_BAN")
@NamedQuery(name="TbComUserBan.findAll", query="SELECT t FROM TbComUserBan t")
public class TbComUserBan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BAN_ID")
	private BigInteger banId;

	@Column(name="BAN_END_DATE")
	private Timestamp banEndDate;

	@Lob
	@Column(name="BAN_REASON")
	private String banReason;

	@Column(name="BAN_REF_ARTICLE_ID")
	private BigInteger banRefArticleId;

	@Column(name="BAN_START_DATE")
	private Timestamp banStartDate;

	@Column(name="BAN_TYPE")
	private String banType;

	@Column(name="BAN_USER_ID")
	private BigInteger banUserId;

	@Column(name="BAN_YN")
	private byte banYn;

	@Column(name = "CREATE_DATETIME", updatable = false)
    private Timestamp createDatetime = DateUtil.getTimestamp();

    @Column(name="CREATE_USER_ID", updatable = false)
    private BigInteger createUserId;

    @Column(name="UPDATE_DATETIME", insertable = false)
    private Timestamp updateDatetime = DateUtil.getTimestamp();

    @Column(name = "UPDATE_USER_ID", insertable = false)
    private BigInteger updateUserId;

	public TbComUserBan() {
	}


	public BigInteger getBanId() {
        return banId;
    }

    public void setBanId(BigInteger banId) {
        this.banId = banId;
    }

    public Timestamp getBanEndDate() {
		return this.banEndDate;
	}

	public void setBanEndDate(Timestamp banEndDate) {
		this.banEndDate = banEndDate;
	}

	public String getBanReason() {
		return this.banReason;
	}

	public void setBanReason(String banReason) {
		this.banReason = banReason;
	}

	public BigInteger getBanRefArticleId() {
		return this.banRefArticleId;
	}

	public void setBanRefArticleId(BigInteger banRefArticleId) {
		this.banRefArticleId = banRefArticleId;
	}

	public Timestamp getBanStartDate() {
		return this.banStartDate;
	}

	public void setBanStartDate(Timestamp banStartDate) {
		this.banStartDate = banStartDate;
	}

	public String getBanType() {
		return this.banType;
	}

	public void setBanType(String banType) {
		this.banType = banType;
	}

	public BigInteger getBanUserId() {
		return this.banUserId;
	}

	public void setBanUserId(BigInteger banUserId) {
		this.banUserId = banUserId;
	}

	public byte getBanYn() {
		return this.banYn;
	}

	public void setBanYn(byte banYn) {
		this.banYn = banYn;
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

}