package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TB_MAPP_BOARD_CATEGORY database table.
 * 
 */
@Entity
@Table(name="TB_MAPP_BOARD_CATEGORY")
@NamedQuery(name="TbMappBoardCategory.findAll", query="SELECT t FROM TbMappBoardCategory t")
public class TbMappBoardCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbMappBoardCategoryPK id;

	public TbMappBoardCategory() {
	}

	public TbMappBoardCategoryPK getId() {
		return this.id;
	}

	public void setId(TbMappBoardCategoryPK id) {
		this.id = id;
	}

}