package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the TB_MAPP_BOARD_AUTH database table.
 *
 */
@Entity
@Table(name = "TB_MAPP_BOARD_AUTH")
@NamedQuery(name = "TbMappBoardAuth.findAll", query = "SELECT t FROM TbMappBoardAuth t")
public class TbMappBoardAuth implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbMappBoardAuthPK id;

	@Column(name = "READABLE_YN")
	private byte readableYn;

	@Column(name = "WRITABLE_YN")
	private byte writableYn;

    @Transient
    private BigInteger authId;

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

    public BigInteger getAuthId() {
        return authId;
    }

    public void setAuthId(BigInteger authId) {
        this.authId = authId;
    }



}