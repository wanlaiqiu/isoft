package com.van.products.entity.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.van.framework.core.orm.domain.ProtoLongModel;

/** 
 * @className: Site.java<br/>
 * @classDescription: 站点信息<br/>
 * @createTime: 2015年6月27日
 * @author Van
 */

@Entity
@Table(name = "VAN_SITE")
public class Site extends ProtoLongModel {
	private static final long serialVersionUID = 1L;

	@Column(name = "`NAME`")
	private String name;
	
	@Column(name = "`URL`")
	private String url;
	
	@Column(name = "`HOST`")
	private String host;
	
	@Column(name = "`PORT`")
	private int port;
	
	@Transient
	private String navigateName;
	
	@Transient
	private String navigateURI;
	
	@Transient
	private String time;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME")
	private Date updateTime;

	public Site(){
		super();
	}
	
	public Site(Long id){
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getNavigateName() {
		return navigateName;
	}

	public void setNavigateName(String navigateName) {
		this.navigateName = navigateName;
	}

	public String getNavigateURI() {
		return navigateURI;
	}

	public void setNavigateURI(String navigateURI) {
		this.navigateURI = navigateURI;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
