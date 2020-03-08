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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the TB_ARTICLE_DIARY_REPLY database table.
 *
 */
@Entity
@Table(name="TB_ARTICLE_DIARY_REPLY")
@NamedQuery(name="TbArticleDiaryReply.findAll", query="SELECT t FROM TbArticleDiaryReply t")
public class TbArticleDiaryReply implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="REPLY_ID")
	private String replyId;

	@Column(name="CREATE_DATETIME")
	private Timestamp createDatetime;

	@Column(name="CREATE_USER_ID")
	private BigInteger createUserId;

	@Column(name="REPLY_BAN_YN")
	private byte replyBanYn;

	@Lob
	@Column(name="REPLY_CONTENTS")
	private String replyContents;

	@Column(name="REPLY_DELETE_YN")
	private byte replyDeleteYn;

	@Column(name="REPLY_LEVEL")
	private int replyLevel;

	@Column(name="REPLY_MODIFIER_ID")
	private BigInteger replyModifierId;

	@Column(name="REPLY_MODIFY_DATETIME")
	private Timestamp replyModifyDatetime;

	@Column(name="REPLY_ORDER")
	private int replyOrder;

	@Column(name="REPLY_REPORT_COUNT")
	private int replyReportCount;

	@Column(name="REPLY_WRITER_ID")
	private BigInteger replyWriterId;

	@Column(name="REPYL_WRITE_DATETIME")
	private Timestamp repylWriteDatetime;

	@Column(name="UPDATE_DATETIME")
	private Timestamp updateDatetime;

	@Column(name="UPDATE_USER_ID")
	private BigInteger updateUserId;

	//bi-directional many-to-one association to TbArticleDiary
	@ManyToOne
	@JoinColumn(name="ARTICLE_ID", insertable = false, updatable = false)
	private TbArticleDiary tbArticleDiary;

	//bi-directional many-to-one association to TbArticleDiaryReply
	@ManyToOne
	@JoinColumn(name="PRIOR_REPLY_ID", insertable = false, updatable = false)
	private TbArticleDiaryReply tbArticleDiaryReply;

	//bi-directional many-to-one association to TbArticleDiaryReply
	@OneToMany(mappedBy="tbArticleDiaryReply")
	private List<TbArticleDiaryReply> tbArticleDiaryReplies;

	public TbArticleDiaryReply() {
	}

	public String getReplyId() {
		return this.replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
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

	public Timestamp getRepylWriteDatetime() {
		return this.repylWriteDatetime;
	}

	public void setRepylWriteDatetime(Timestamp repylWriteDatetime) {
		this.repylWriteDatetime = repylWriteDatetime;
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

	public TbArticleDiaryReply getTbArticleDiaryReply() {
		return this.tbArticleDiaryReply;
	}

	public void setTbArticleDiaryReply(TbArticleDiaryReply tbArticleDiaryReply) {
		this.tbArticleDiaryReply = tbArticleDiaryReply;
	}

	public List<TbArticleDiaryReply> getTbArticleDiaryReplies() {
		return this.tbArticleDiaryReplies;
	}

	public void setTbArticleDiaryReplies(List<TbArticleDiaryReply> tbArticleDiaryReplies) {
		this.tbArticleDiaryReplies = tbArticleDiaryReplies;
	}

	public TbArticleDiaryReply addTbArticleDiaryReply(TbArticleDiaryReply tbArticleDiaryReply) {
		getTbArticleDiaryReplies().add(tbArticleDiaryReply);
		tbArticleDiaryReply.setTbArticleDiaryReply(this);

		return tbArticleDiaryReply;
	}

	public TbArticleDiaryReply removeTbArticleDiaryReply(TbArticleDiaryReply tbArticleDiaryReply) {
		getTbArticleDiaryReplies().remove(tbArticleDiaryReply);
		tbArticleDiaryReply.setTbArticleDiaryReply(null);

		return tbArticleDiaryReply;
	}

}