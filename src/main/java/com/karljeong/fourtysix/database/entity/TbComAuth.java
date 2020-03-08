package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.List;


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

	@Column(name="CREATE_DATETIME")
	private Timestamp createDatetime;

	@Column(name="CREATE_USER_ID")
	private BigInteger createUserId;

	@Column(name="UPDATE_DATETIME")
	private Timestamp updateDatetime;

	@Column(name="UPDATE_USER_ID")
	private BigInteger updateUserId;

	//bi-directional many-to-many association to TbComUser
	@ManyToMany(mappedBy="tbComAuths")
	private List<TbComUser> tbComUsers;

	//bi-directional many-to-one association to TbMappBoardAuth
	@OneToMany(mappedBy="tbComAuth")
	private List<TbMappBoardAuth> tbMappBoardAuths;

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

	public List<TbComUser> getTbComUsers() {
		return this.tbComUsers;
	}

	public void setTbComUsers(List<TbComUser> tbComUsers) {
		this.tbComUsers = tbComUsers;
	}

	public List<TbMappBoardAuth> getTbMappBoardAuths() {
		return this.tbMappBoardAuths;
	}

	public void setTbMappBoardAuths(List<TbMappBoardAuth> tbMappBoardAuths) {
		this.tbMappBoardAuths = tbMappBoardAuths;
	}

	public TbMappBoardAuth addTbMappBoardAuth(TbMappBoardAuth tbMappBoardAuth) {
		getTbMappBoardAuths().add(tbMappBoardAuth);
		tbMappBoardAuth.setTbComAuth(this);

		return tbMappBoardAuth;
	}

	public TbMappBoardAuth removeTbMappBoardAuth(TbMappBoardAuth tbMappBoardAuth) {
		getTbMappBoardAuths().remove(tbMappBoardAuth);
		tbMappBoardAuth.setTbComAuth(null);

		return tbMappBoardAuth;
	}

}