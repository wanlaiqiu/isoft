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

import com.van.framework.core.orm.domain.ProtoStringModel;
import com.van.products.entity.bean.Article;
import com.van.products.entity.bean.EnumConst;
import com.van.products.entity.bean.UserModel;

/** 
 * @className: Content.java<br/>
 * @classDescription: 文章内容<br/>
 * @createTime: 2015年7月11日
 * @author Van
 */

@Entity
@Table(name = "VAN_CONTENT")
public class Content extends ProtoStringModel {
	private static final long serialVersionUID = 1L;

	@Column(name = "INDEX_ID")
	private String index;
	
	@Column(name = "`TITLE`")
	private String title;//标题
	
	@Column(name = "SHORT_TITLE")
	private String shortTitle;//简短标题
	
	@Column(name = "SUMMARY")
	private String summary;//摘要
	
	@Column(name = "THUMBNAIL")
	private String thumbnail;//缩略图
	
	@Column(name = "PHOTO")
	private String photo;//主题插图
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;//创建时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME")
	private Date updateTime;//最后修改时间
	
	@Column(name = "KEY_WORDS")
	private String keyWords;
	
	@Column(name = "CONTENT", length = 16777215)
	private String content;//内容
	
	@Column(name = "IS_DELETED")
	private boolean deleted;//是否删除
	
	@Column(name = "`COUNT`")
	private int count;//查看次数
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_AUTHOR")
	private UserModel author;//作者
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_ARTICLE")
	private Article article;//所属分类
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_VALID")
	private EnumConst enumContentValid;//有效性

	public Content(){
		super();
	}
	
	public Content(ProtoStringModel src){
		super(src);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public UserModel getAuthor() {
		return author;
	}

	public void setAuthor(UserModel author) {
		this.author = author;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public EnumConst getEnumContentValid() {
		return enumContentValid;
	}

	public void setEnumContentValid(EnumConst enumContentValid) {
		this.enumContentValid = enumContentValid;
	}
	
}
