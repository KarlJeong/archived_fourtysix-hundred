package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.karljeong.fourtysix.utils.DateUtil;

/**
 * The persistent class for the TB_COM_FILE database table.
 *
 */
@Entity
@Table(name = "TB_COM_FILE")
@NamedQuery(name = "TbComFile.findAll", query = "SELECT t FROM TbComFile t")
public class TbComFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FILE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger fileId;

	@Column(name = "FILE_REF_ID")
	private BigInteger fileRefId;

	@Column(name = "FILE_ORDER")
	private int fileOrder;

	@Column(name = "CREATE_DATETIME", updatable = false)
	private Timestamp createDatetime = DateUtil.getTimestamp();

	@Column(name = "CREATE_USER_ID", updatable = false)
	private BigInteger createUserId;

	@Column(name = "UPDATE_DATETIME", insertable = false)
	private Timestamp updateDatetime = DateUtil.getTimestamp();

	@Column(name = "UPDATE_USER_ID", insertable = false)
	private BigInteger updateUserId;

	@Column(name = "DELETE_YN")
	private byte deleteYn = 0;

	@Column(name = "FILE_EXTENSION")
	private String fileExtension;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FILE_ORGINAL_NAME")
	private String fileOrginalName;

	@Column(name = "FILE_PARAM_NAME")
	private String fileParamName;

	@Column(name = "FILE_PATH")
	private String filePath;

	@Column(name = "FILE_SIZE")
	private BigInteger fileSize;

	@Column(name = "FILE_TYPE")
	private String fileType;

	public TbComFile() {
	}

	public BigInteger getFileId() {
		return this.fileId;
	}

	public void setFileId(BigInteger fileId) {
		this.fileId = fileId;
	}

	public BigInteger getFileRefId() {
		return this.fileRefId;
	}

	public void setFileRefId(BigInteger fileRefId) {
		this.fileRefId = fileRefId;
	}

	public int getFileOrder() {
		return this.fileOrder;
	}

	public void setFileOrder(int fileOrder) {
		this.fileOrder = fileOrder;
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

	public String getFileExtension() {
		return this.fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileOrginalName() {
		return this.fileOrginalName;
	}

	public void setFileOrginalName(String fileOrginalName) {
		this.fileOrginalName = fileOrginalName;
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

}
