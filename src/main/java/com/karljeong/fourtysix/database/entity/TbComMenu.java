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

import com.karljeong.fourtysix.utils.DateUtil;


/**
 * The persistent class for the TB_COM_MENU database table.
 *
 */
@Entity
@Table(name="TB_COM_MENU")
@NamedQuery(name="TbComMenu.findAll", query="SELECT t FROM TbComMenu t")
public class TbComMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MENU_ID")
	private BigInteger menuId;

    @Column(name = "CREATE_DATETIME", updatable = false)
    private Timestamp createDatetime = DateUtil.getTimestamp();

    @Column(name = "CREATE_USER_ID", updatable = false)
    private BigInteger createUserId;

	@Lob
	@Column(name="MENU_DESCRIPTION")
	private String menuDescription;

	@Column(name="MENU_LEVEL")
	private int menuLevel;

	@Column(name="MENU_NAME")
	private String menuName;

	@Column(name="MENU_ORDER")
	private int menuOrder;

	@Column(name="MENU_PATH")
	private String menuPath;

	@Column(name="MENU_TYPE")
	private String menuType;

	@Column(name="PRIOR_MENU_ID")
	private BigInteger priorMenuId;

    @Column(name = "UPDATE_DATETIME", insertable = false)
    private Timestamp updateDatetime = DateUtil.getTimestamp();

    @Column(name = "UPDATE_USER_ID", insertable = false)
    private BigInteger updateUserId;

	@Column(name="USE_YN")
	private byte useYn;

    @Transient
    private String priorMenuName;

    @Transient
    private String displayOrder;

	public TbComMenu() {
	}

	public BigInteger getMenuId() {
        return menuId;
    }

    public void setMenuId(BigInteger menuId) {
        this.menuId = menuId;
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

	public String getMenuDescription() {
		return this.menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}

	public int getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuOrder() {
		return this.menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getMenuPath() {
		return this.menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public String getMenuType() {
		return this.menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public BigInteger getPriorMenuId() {
		return this.priorMenuId;
	}

	public void setPriorMenuId(BigInteger priorMenuId) {
		this.priorMenuId = priorMenuId;
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

    public String getPriorMenuName() {
        return priorMenuName;
    }

    public void setPriorMenuName(String priorMenuName) {
        this.priorMenuName = priorMenuName;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }





}