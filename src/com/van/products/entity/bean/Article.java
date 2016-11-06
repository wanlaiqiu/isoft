package com.van.products.entity.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.van.framework.core.orm.domain.ProtoStringModel;

/** 
 * @className: Article.java<br/>
 * @classDescription: 文章分类<br/>
 * @createTime: 2015年6月27日
 * @author Van
 */

@Entity
@Table(name = "VAN_ARTICLE")
public class Article extends ProtoStringModel {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "`NAME`")
	private String name;//名称
	
	@Column(name = "CHANNEL_ID")
	private String channelId;//索引号
	
	@Column(name = "DESCRIPTION")
	private String description;//描述
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;//创建时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME")
	private Date updateTime;//修改时间
	
	@Column(name = "IMAGE")
	private String image;//分类图片
	
	@Column(name = "SORT")
	private int sort;
	
	@Column(name = "KEY_WORDS")
	private String keyWords;
	
	@Column(name = "LABEL")
	private String label;
	
	public Article(){
		super();
	}
	
	public Article(ProtoStringModel src){
		super(src);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
