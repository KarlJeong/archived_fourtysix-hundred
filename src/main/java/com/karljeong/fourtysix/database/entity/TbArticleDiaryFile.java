package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the TB_ARTICLE_DIARY_FILE database table.
 * 
 */
@Entity
@Table(name="TB_ARTICLE_DIARY_FILE")
@NamedQuery(name="TbArticleDiaryFile.findAll", query="SELECT t FROM TbArticleDiaryFile t")
public class TbArticleDiaryFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbArticleDiaryFilePK id;

	@Column(name="CREATE_DATETIME")
	private Timestamp createDatetime;

	@Column(name="CREATE_USER_ID")
	private BigInteger createUserId;

	@Column(name="DELETE_YN")
	private byte deleteYn;

	@Column(name="FILE_EXTENTION")
	private String fileExtention;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_ORIGINAL_NAME")
	private String fileOriginalName;

	@Column(name="FILE_PARAM_NAME")
	private String fileParamName;

	@Column(name="FILE_PATH")
	private String filePath;

	@Column(name="FILE_SIZE")
	private BigInteger fileSize;

	@Column(name="FILE_TYPE")
	private String fileType;

	@Column(name="UPDATE_DATETIME")
	private Timestamp updateDatetime;

	@Column(name="UPDATE_USER_ID")
	private BigInteger updateUserId;

	//bi-directional many-to-one association to TbArticleDiary
	@ManyToOne
	@JoinColumn(name="FILE_REF_ID", insertable = false, updatable = false)
	private TbArticleDiary tbArticleDiary;

	public TbArticleDiaryFile() {
	}

	public TbArticleDiaryFilePK getId() {
		return this.id;
	}

	public void setId(TbArticleDiaryFilePK id) {
		this.id = id;
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

	public byte getDeleteYn() {
		return this.deleteYn;
	}

	public void setDeleteYn(byte deleteYn) {
		this.deleteYn = deleteYn;
	}

	public String getFileExtention() {
		return this.fileExtention;
	}

	public void setFileExtention(String fileExtention) {
		this.fileExtention = fileExtention;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileOriginalName() {
		return this.fileOriginalName;
	}

	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}

	public String getFileParamName() {
		return this.fileParamName;
	}

	public void setFileParamName(String fileParamName) {
		this.fileParamName = fileParamName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public BigInteger getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(BigInteger fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
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

	public TbArticleDiary getTbArticleDiary() {
		return this.tbArticleDiary;
	}

	public void setTbArticleDiary(TbArticleDiary tbArticleDiary) {
		this.tbArticleDiary = tbArticleDiary;
	}

}