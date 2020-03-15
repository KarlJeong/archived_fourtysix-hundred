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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID")
    private BigInteger fileId;

    @Column(name = "FILE_REF_ID")
    private int fileRefId;

    @Column(name = "FILE_ORDER")
    private int fileOrder;

    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;

    @Column(name = "CREATE_USER_ID")
    private BigInteger createUserId;

    @Column(name = "DELETE_YN")
    private byte deleteYn;

    @Column(name = "FILE_EXTENTION")
    private String fileExtention;

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

    @Column(name = "UPDATE_DATETIME")
    private Timestamp updateDatetime;

    @Column(name = "UPDATE_USER_ID")
    private BigInteger updateUserId;

    public TbComFile() {}

    public BigInteger getFileId() {
        return this.fileId;
    }

    public void setFileId(BigInteger fileId) {
        this.fileId = fileId;
    }

    public int getFileRefId() {
        return this.fileRefId;
    }

    public void setFileRefId(int fileRefId) {
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
