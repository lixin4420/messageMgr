package com.msg.entity;

import java.util.Date;

public class Message {

	private int msgid;
	private String username;
	private String title;
	private String msgContent;
	/**
	 * 状态
	 * 0未读，1已读
	 */
	private int state;
	private String sendto;
	private Date msgCreateDate;
	
	public Message() {
		
	}
	//set、get
	public int getMsgid() {
		return msgid;
	}
	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getSendto() {
		return sendto;
	}
	public void setSendto(String sendto) {
		this.sendto = sendto;
	}
	public Date getMsgCreateDate() {
		return msgCreateDate;
	}
	public void setMsgCreateDate(Date msgCreateDate) {
		this.msgCreateDate = msgCreateDate;
	}
	
}
