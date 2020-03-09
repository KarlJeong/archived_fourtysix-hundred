package com.karljeong.fourtysix.database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


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
	private String menuId;

	@Column(name="CREATE_DATETIME")
	private Timestamp createDatetime;

	@Column(name="CREATE_USER_ID")
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

	@Column(name="UPDATE_DATETIME")
	private Timestamp updateDatetime;

	@Column(name="UPDATE_USER_ID")
	private BigInteger updateUserId;

	@Column(name="USE_YN")
	private byte useYn;

	public TbComMenu() {
	}

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
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

}