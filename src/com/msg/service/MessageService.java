package com.msg.service;

import java.util.List;

import com.msg.entity.Message;

public interface MessageService {

	/**
	 * 根据收件人查询短消息
	 * @param username
	 * @return
	 */
	List<Message> queryMsgBySendto(String username);

	/**
	 * 新增短消息
	 * @param msg
	 * @return
	 */
	boolean addMsg(Message message);

	/**
	 * 通过id查询短消息
	 * @param msgId
	 * @return
	 */
	Message queryMsgById(int msgId);

	/**
	 * 根据id删除短消息
	 * @param msgid
	 */
	void deleteMsg(int msgid);

	/**
	 * 更新短消息状态，改为已读
	 * @param msgid
	 */
	void readMsg(int msgid);

}
