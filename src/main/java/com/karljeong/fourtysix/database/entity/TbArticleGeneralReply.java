package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import com.karljeong.fourtysix.utils.DateUtil;
import com.karljeong.fourtysix.utils.UserUtil;

/**
 * The persistent class for the TB_ARTICLE_DIARY_REPLY database table.
 *
 */
@Entity
@Table(name = "TB_ARTICLE_GENERAL_REPLY")
@NamedQuery(name = "TbArticleGeneralReply.findAll", query = "SELECT t FROM TbArticleGeneralReply t")
public class TbArticleGeneralReply implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REPLY_ID")
	private String replyId;

	@Column(name = "ARTICLE_ID")
	private BigInteger articleId;

	@Column(name = "CREATE_DATETIME", updatable = false)
	private Timestamp createDatetime = DateUtil.getTimestamp();

	@Column(name = "CREATE_USER_ID", updatable = false)
	private BigInteger createUserId;

	@Column(name = "UPDATE_DATETIME", insertable = false)
	private Timestamp updateDatetime;

	@Column(name = "UPDATE_USER_ID", insertable = false)
	private BigInteger updateUserId;

	@Column(name = "PRIOR_REPLY_ID")
	private BigInteger priorReplyId;

	@Column(name = "REPLY_BAN_YN")
	private byte replyBanYn = 0;

	@Lob
	@Column(name = "REPLY_CONTENTS")
	private String replyContents;

	@Column(name = "REPLY_DELETE_YN")
	private byte replyDeleteYn;

	@Column(name = "REPLY_LEVEL", updatable = false)
	private int replyLevel = 1;

	@Column(name = "REPLY_MODIFIER_ID", insertable = false)
	private BigInteger replyModifierId;

	@Column(name = "REPLY_MODIFY_DATETIME", insertable = false)
	private Timestamp replyModifyDatetime;

	@Column(name = "REPLY_ORDER", updatable = false)
	private int replyOrder = 1;

	@Column(name = "REPLY_REPORT_COUNT", updatable = false)
	private int replyReportCount = 0;

	@Column(name = "REPLY_WRITER_ID", updatable = false)
	private BigInteger replyWriterId;

	@Column(name = "REPLY_WRITE_DATETIME", updatable = false)
	private Timestamp replyWriteDatetime = DateUtil.getTimestamp();

	@Transient
	private String replyWriterUserName;

	@Transient
	private String path;

	public TbArticleGeneralReply() {
	}

	public String getReplyId() {
		return this.replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public BigInteger getArticleId() {
		return this.articleId;
	}

	public void setArticleId(BigInteger articleId) {
		this.articleId = articleId;
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

	public BigInteger getPriorReplyId() {
		return this.priorReplyId;
	}

	public void setPriorReplyId(BigInteger priorReplyId) {
		this.priorReplyId = priorReplyId;
	}

	public byte getReplyBanYn() {
		return this.replyBanYn;
	}

	public void setReplyBanYn(byte replyBanYn) {
		this.replyBanYn = replyBanYn;
	}

	public String getReplyContents() {
		return this.replyContents;
	}

	public void setReplyContents(String replyContents) {
		this.replyContents = replyContents;
	}

	public byte getReplyDeleteYn() {
		return this.replyDeleteYn;
	}

	public void setReplyDeleteYn(byte replyDeleteYn) {
		this.replyDeleteYn = replyDeleteYn;
	}

	public int getReplyLevel() {
		return this.replyLevel;
	}

	public void setReplyLevel(int replyLevel) {
		this.replyLevel = replyLevel;
	}

	public BigInteger getReplyModifierId() {
		return this.replyModifierId;
	}

	public void setReplyModifierId(BigInteger replyModifierId) {
		this.replyModifierId = replyModifierId;
	}

	public Timestamp getReplyModifyDatetime() {
		return this.replyModifyDatetime;
	}

	public void setReplyModifyDatetime(Timestamp replyModifyDatetime) {
		this.replyModifyDatetime = replyModifyDatetime;
	}

	public int getReplyOrder() {
		return this.replyOrder;
	}

	public void setReplyOrder(int replyOrder) {
		this.replyOrder = replyOrder;
	}

	public int getReplyReportCount() {
		return this.replyReportCount;
	}

	public void setReplyReportCount(int replyReportCount) {
		this.replyReportCount = replyReportCount;
	}

	public BigInteger getReplyWriterId() {
		return this.replyWriterId;
	}

	public void setReplyWriterId(BigInteger replyWriterId) {
		this.replyWriterId = replyWriterId;
	}

	public Timestamp getReplyWriteDatetime() {
		return replyWriteDatetime;
	}

	public void setReplyWriteDatetime(Timestamp replyWriteDatetime) {
		this.replyWriteDatetime = replyWriteDatetime;
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

	public String getReplyWriterUserName() {
		return replyWriterUserName;
	}

	public void setReplyWriterUserName(String replyWriterUserName) {
		this.replyWriterUserName = replyWriterUserName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setUserInfo(HttpServletRequest request) {
		BigInteger userId = UserUtil.getUserId();
		this.createUserId = userId;
		this.updateUserId = userId;
		this.replyWriterId = userId;
		this.replyModifierId = userId;
	}

}