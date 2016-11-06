package com.van.products.entity.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

import com.van.framework.core.orm.domain.ProtoStringModel;
import com.van.products.entity.bean.System.SystemUser;


/** 
 * @className: UserModel.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年4月25日
 * @author Van
 */

@Entity
@Table(name = "VAN_USERMODEL")
@DynamicUpdate(true)
public class UserModel extends ProtoStringModel {
	private static final long serialVersionUID = 1L;

	@Column(name = "`NAME`")
	private String name;
	
	@Column(name = "AGE")
	private String age;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY")
	private Date birthday;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_GENDER")
	private EnumConst enumGender;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_FOLK")
	private EnumConst enumFolk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_SYSTEMUSER")
	private SystemUser systemUser;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "MOBILE")
	private String mobile;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "QQ")
	private String qq;
	
	@Column(name = "WECHAT")
	private String weChat;
	
	@Column(name = "MSN")
	private String msn;
	
	@Transient
	private NullEntity province;
	
	@Transient
	private NullEntity city;
	
	@Transient
	private NullEntity area;

	public UserModel(){
		super();
	}
	
	public UserModel(ProtoStringModel src){
		super(src);
	}
	
	public UserModel(String id){
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public EnumConst getEnumFolk() {
		return enumFolk;
	}

	public void setEnumFolk(EnumConst enumFolk) {
		this.enumFolk = enumFolk;
	}

	public EnumConst getEnumGender() {
		return enumGender;
	}

	public void setEnumGender(EnumConst enumGender) {
		this.enumGender = enumGender;
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public NullEntity getProvince() {
		return province;
	}

	public void setProvince(NullEntity province) {
		this.province = province;
	}

	public NullEntity getCity() {
		return city;
	}

	public void setCity(NullEntity city) {
		this.city = city;
	}

	public NullEntity getArea() {
		return area;
	}

	public void setArea(NullEntity area) {
		this.area = area;
	}
	

	
}
