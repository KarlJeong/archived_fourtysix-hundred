package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.karljeong.fourtysix.utils.DateUtil;

/**
 * The persistent class for the TB_COM_BOARD database table.
 *
 */
@Entity
@Table(name = "TB_COM_BOARD")
@NamedQuery(name = "TbComBoard.findAll", query = "SELECT t FROM TbComBoard t")
public class TbComBoard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOARD_ID")
	private BigInteger boardId;

	@Column(name = "ARTICLE_COUNT", insertable = false, updatable = false)
	private BigInteger articleCount;

	@Column(name = "BOARD_CODE")
	private String boardCode;

	@Lob
	@Column(name = "BOARD_DESCRIPTION")
	private String boardDescription;

	@Column(name = "BOARD_NAME")
	private String boardName;

	@Column(name = "CREATE_DATETIME", updatable = false)
	private Timestamp createDatetime = DateUtil.getTimestamp();

	@Column(name = "CREATE_USER_ID", updatable = false)
	private BigInteger createUserId;

	@Column(name = "SYS_BOARD_YN")
	private byte sysBoardYn;

	@Column(name = "UPDATE_DATETIME", insertable = false)
	private Timestamp updateDatetime = DateUtil.getTimestamp();

	@Column(name = "UPDATE_USER_ID", insertable = false)
	private BigInteger updateUserId;

	@Column(name = "USE_YN")
	private byte useYn;

	@Transient
	private List<TbComAuth> tbComAuthWritable;

	@Transient
	private List<TbComAuth> tbComAuthReadable;

	public TbComBoard() {
	}

	public BigInteger getBoardId() {
		return this.boardId;
	}

	public void setBoardId(BigInteger boardId) {
		this.boardId = boardId;
	}

	public BigInteger getArticleCount() {
		return this.articleCount;
	}

	public void setArticleCount(BigInteger articleCount) {
		this.articleCount = articleCount;
	}

	public String getBoardCode() {
		return this.boardCode;
	}

	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}

	public String getBoardDescription() {
		return this.boardDescription;
	}

	public void setBoardDescription(String boardDescription) {
		this.boardDescription = boardDescription;
	}

	public String getBoardName() {
		return this.boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
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

	public byte getSysBoardYn() {
		return this.sysBoardYn;
	}

	public void setSysBoardYn(byte sysBoardYn) {
		this.sysBoardYn = sysBoardYn;
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

	public List<TbComAuth> getTbComAuthWritable() {
		return tbComAuthWritable;
	}

	public void setTbComAuthWritable(List<TbComAuth> tbComAuthWritable) {
		this.tbComAuthWritable = tbComAuthWritable;
	}

	public List<TbComAuth> getTbComAuthReadable() {
		return tbComAuthReadable;
	}

	public void setTbComAuthReadable(List<TbComAuth> tbComAuthReadable) {
		this.tbComAuthReadable = tbComAuthReadable;
	}

}