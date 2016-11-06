package com.van.products.entity.bean.System;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.van.framework.core.orm.domain.ProtoStringModel;
import com.van.products.entity.bean.EnumConst;

/** 
 * @className: SystemUser.java<br/>
 * @classDescription: 系统用户 <br/>
 * @createTime: 2015年6月29日
 * @author Van
 */

@Entity
@Table(name = "SYSTEM_USER")
@DynamicUpdate(true)
public class SystemUser extends ProtoStringModel {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "LOGIN")
	private String login;
	
	@Column(name = "`PASSWORD`")
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "USER_VALID")
	private EnumConst enumUserValid;
	
	@Column(name = "`COUNT`")
	private int count;
	
	@ManyToOne
	@JoinColumn(name = "USER_TYPE")
	private EnumConst enumUserType;
	
	@Column(name = "PRE_LOGIN_IP")
	private String preLoginIp;
	
	@Column(name = "LAST_LOGIN_IP")
	private String lastLoginIp;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PRE_LOGIN_TIME")
	private Date preLoginTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_LOGIN_TIME")
	private Date lastLoginTime;
	
	public SystemUser(String id){
		this.id = id;
	}
	
	public SystemUser(ProtoStringModel src){
		super(src);
	}
	
	public SystemUser(){
		super();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EnumConst getEnumUserValid() {
		return enumUserValid;
	}

	public void setEnumUserValid(EnumConst enumUserValid) {
		this.enumUserValid = enumUserValid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public EnumConst getEnumUserType() {
		return enumUserType;
	}

	public void setEnumUserType(EnumConst enumUserType) {
		this.enumUserType = enumUserType;
	}

	public String getPreLoginIp() {
		return preLoginIp;
	}

	public void setPreLoginIp(String preLoginIp) {
		this.preLoginIp = preLoginIp;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getPreLoginTime() {
		return preLoginTime;
	}

	public void setPreLoginTime(Date preLoginTime) {
		this.preLoginTime = preLoginTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

}
