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

import org.hibernate.annotations.DynamicUpdate;

import com.van.framework.core.orm.domain.ProtoStringModel;

/** 
 * @className: Message.java<br/>
 * @classDescription: 站内信 消息<br/>
 * @createTime: 2015年6月29日
 * @author Van
 */

@Entity
@Table(name = "VAN_MESSAGE")
@DynamicUpdate(true)
public class Message extends ProtoStringModel {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_SENDER")
	private UserModel sender;//发送者
	
	@Column(name = "SENDER_NAME")
	private String senderName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_RECEIVER")
	private UserModel receiver;//接收者
	
	@Column(name = "RECEIVER_NAME")
	private String receiverName;
	
	@Column(name = "MESSAGE")
	private String message;//消息
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SEND_TIME")
	private Date sendTime;//发送时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RECEIVE_TIME")
	private Date receiveTime;//接收时间
	
	@Column(name = "IP")
	private String ip;//发送者IP
	
	@Column(name = "SENDER_EMAIL")
	private String senderEmail;//发送者Email
	
	@Column(name = "RECEIVER_EMAIL")
	private String receiverEmail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_STATUS")
	private EnumConst enumMessageStatus;//状态
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_REPLYTO")
	private Message replyTo;//回复给哪条信息

	
	public Message(){
		super();
	}
	
	public Message(ProtoStringModel src){
		super(src);
	}
	
	public Message(String id){
		this.id = id;
	}
	
	public UserModel getSender() {
		return sender;
	}

	public void setSender(UserModel sender) {
		this.sender = sender;
	}

	public UserModel getReceiver() {
		return receiver;
	}

	public void setReceiver(UserModel receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public EnumConst getEnumMessageStatus() {
		return enumMessageStatus;
	}

	public void setEnumMessageStatus(EnumConst enumMessageStatus) {
		this.enumMessageStatus = enumMessageStatus;
	}

	public Message getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(Message replyTo) {
		this.replyTo = replyTo;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
}
