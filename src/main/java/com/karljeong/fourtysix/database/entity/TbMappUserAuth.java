package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TB_MAPP_USER_AUTH database table.
 * 
 */
@Entity
@Table(name="TB_MAPP_USER_AUTH")
@NamedQuery(name="TbMappUserAuth.findAll", query="SELECT t FROM TbMappUserAuth t")
public class TbMappUserAuth implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbMappUserAuthPK id;

	public TbMappUserAuth() {
	}

	public TbMappUserAuthPK getId() {
		return this.id;
	}

	public void setId(TbMappUserAuthPK id) {
		this.id = id;
	}

}