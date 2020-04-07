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
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.karljeong.fourtysix.utils.DateUtil;


/**
 * The persistent class for the TB_COM_PATTERN database table.
 *
 */
@Entity
@Table(name="TB_COM_PATTERN")
@NamedQuery(name="TbComPattern.findAll", query="SELECT t FROM TbComPattern t")
public class TbComPattern implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="PATTERN_ID")
    private BigInteger patternId;

    @Column(name = "CREATE_DATETIME", updatable = false)
    private Timestamp createDatetime = DateUtil.getTimestamp();

    @Column(name="CREATE_USER_ID", updatable = false)
    private BigInteger createUserId;

    @Column(name="UPDATE_DATETIME", insertable = false)
    private Timestamp updateDatetime = DateUtil.getTimestamp();

    @Column(name = "UPDATE_USER_ID", insertable = false)
    private BigInteger updateUserId;

    @Column(name = "URI_PATTERN")
    private String uriPattern;

    @Transient
    private List<TbComAuth> tbComAuths;

    @Transient
    private List<TbMappPatternAuth> tbMappPatternAuths;

    public TbComPattern() {
    }

    public BigInteger getPatternId() {
        return patternId;
    }

    public void setPatternId(BigInteger patternId) {
        this.patternId = patternId;
    }

    public Timestamp getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Timestamp createDatetime) {
        this.createDatetime = createDatetime;
    }

    public BigInteger getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(BigInteger createUserId) {
        this.createUserId = createUserId;
    }

    public Timestamp getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Timestamp updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public BigInteger getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(BigInteger updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUriPattern() {
        return uriPattern;
    }

    public void setUriPattern(String uriPattern) {
        this.uriPattern = uriPattern;
    }

    public List<TbComAuth> getTbComAuths() {
        return tbComAuths;
    }

    public void setTbComAuths(List<TbComAuth> tbComAuths) {
        this.tbComAuths = tbComAuths;
    }

    public List<TbMappPatternAuth> getTbMappPatternAuths() {
        return tbMappPatternAuths;
    }

    public void setTbMappPatternAuths(List<TbMappPatternAuth> tbMappPatternAuths) {
        this.tbMappPatternAuths = tbMappPatternAuths;
    }

}