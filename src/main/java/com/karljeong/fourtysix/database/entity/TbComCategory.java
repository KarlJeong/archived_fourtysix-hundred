package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the TB_COM_CATEGORY database table.
 * 
 */
@Entity
@Table(name="TB_COM_CATEGORY")
@NamedQuery(name="TbComCategory.findAll", query="SELECT t FROM TbComCategory t")
public class TbComCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ARTICLE_CATEGORY_ID")
	private String articleCategoryId;

	@Lob
	@Column(name="CATEGORY_DESCRIPTION")
	private String categoryDescription;

	@Column(name="CATEGORY_NAME")
	private String categoryName;

	@Column(name="CREATE_DATETIME")
	private Timestamp createDatetime;

	@Column(name="CREATE_USER_ID")
	private BigInteger createUserId;

	@Column(name="UPDATE_DATETIME")
	private Timestamp updateDatetime;

	@Column(name="UPDATE_USER_ID")
	private BigInteger updateUserId;

	@Column(name="USE_YN")
	private byte useYn;

	//bi-directional many-to-one association to TbArticleDiary
	@OneToMany(mappedBy="tbComCategory")
	private List<TbArticleDiary> tbArticleDiaries;

	//bi-directional many-to-many association to TbComBoard
	@ManyToMany
	@JoinTable(
		name="TB_MAPP_BOARD_CATEGORY"
		, joinColumns={
			@JoinColumn(name="ARTICLE_CATEGORY_ID", insertable = false, updatable = false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="BOARD_ID", insertable = false, updatable = false)
			}
		)
	private List<TbComBoard> tbComBoards;

	public TbComCategory() {
	}

	public String getArticleCategoryId() {
		return this.articleCategoryId;
	}

	public void setArticleCategoryId(String articleCategoryId) {
		this.articleCategoryId = articleCategoryId;
	}

	public String getCategoryDescription() {
		return this.categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public List<TbArticleDiary> getTbArticleDiaries() {
		return this.tbArticleDiaries;
	}

	public void setTbArticleDiaries(List<TbArticleDiary> tbArticleDiaries) {
		this.tbArticleDiaries = tbArticleDiaries;
	}

	public TbArticleDiary addTbArticleDiary(TbArticleDiary tbArticleDiary) {
		getTbArticleDiaries().add(tbArticleDiary);
		tbArticleDiary.setTbComCategory(this);

		return tbArticleDiary;
	}

	public TbArticleDiary removeTbArticleDiary(TbArticleDiary tbArticleDiary) {
		getTbArticleDiaries().remove(tbArticleDiary);
		tbArticleDiary.setTbComCategory(null);

		return tbArticleDiary;
	}

	public List<TbComBoard> getTbComBoards() {
		return this.tbComBoards;
	}

	public void setTbComBoards(List<TbComBoard> tbComBoards) {
		this.tbComBoards = tbComBoards;
	}

}