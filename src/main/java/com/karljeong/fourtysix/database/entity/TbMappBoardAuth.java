package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the TB_MAPP_BOARD_AUTH database table.
 *
 */
@Entity
@Table(name="TB_MAPP_BOARD_AUTH")
@NamedQuery(name="TbMappBoardAuth.findAll", query="SELECT t FROM TbMappBoardAuth t")
public class TbMappBoardAuth implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbMappBoardAuthPK id;

	@Column(name="READABLE_YN")
	private byte readableYn;

	@Column(name="WRITABLE_YN")
	private byte writableYn;

	//bi-directional many-to-one association to TbComAuth
	@ManyToOne
	@JoinColumn(name="AUTH_ID", insertable = false, updatable = false)
	private TbComAuth tbComAuth;

	//bi-directional many-to-one association to TbComBoard
	@ManyToOne
	@JoinColumn(name="BOARD_ID", insertable = false, updatable = false)
	private TbComBoard tbComBoard;

	public TbMappBoardAuth() {
	}

	public TbMappBoardAuthPK getId() {
		return this.id;
	}

	public void setId(TbMappBoardAuthPK id) {
		this.id = id;
	}

	public byte getReadableYn() {
		return this.readableYn;
	}

	public void setReadableYn(byte readableYn) {
		this.readableYn = readableYn;
	}

	public byte getWritableYn() {
		return this.writableYn;
	}

	public void setWritableYn(byte writableYn) {
		this.writableYn = writableYn;
	}

	public TbComAuth getTbComAuth() {
		return this.tbComAuth;
	}

	public void setTbComAuth(TbComAuth tbComAuth) {
		this.tbComAuth = tbComAuth;
	}

	public TbComBoard getTbComBoard() {
		return this.tbComBoard;
	}

	public void setTbComBoard(TbComBoard tbComBoard) {
		this.tbComBoard = tbComBoard;
	}

}