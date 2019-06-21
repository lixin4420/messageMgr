package com.msg.dao.impl;

import com.msg.dao.BaseDao;
import com.msg.dao.MessageDao;
import com.msg.entity.Message;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageDaoImpl extends BaseDao implements MessageDao {

	public MessageDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<Message> queryMsgBySendto(String username) {
		String sql = "SELECT msgid, username, title, msg_content, state, sendto, msg_create_date FROM message WHERE sendto = ? ORDER BY msg_create_date DESC";
		Object[] params = {username};
		ResultSet rs = this.executeQuery(sql, params);
		
		List<Message> msgList = new ArrayList<Message>();
		//循环遍历，取出每一个数据
		try {
			while(rs.next()) {
				int id = rs.getInt("msgid");
				String name = rs.getString("username");
				String title = rs.getString("title");
				String msgContent = rs.getString("msg_content");
				int state = rs.getInt("state");
				String sendto = rs.getString("sendto");
				Date createDate = rs.getDate("msg_create_date");
				
				Message msg = new Message();
				msg.setMsgid(id);
				msg.setUsername(name);
				msg.setTitle(title);
				msg.setMsgContent(msgContent);
				msg.setState(state);
				msg.setSendto(sendto);
				msg.setMsgCreateDate(createDate);
				
				msgList.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return msgList;
	}

	@Override
	public int addMsg(Message msg) {
		String sql = "INSERT INTO message (username, title, msg_content, state, sendto, msg_create_date) VALUES(?, ?, ?, ?, ?, ?);";
		Object[] params = {msg.getUsername(), msg.getTitle(), msg.getMsgContent(), msg.getState(), msg.getSendto(), 
							msg.getMsgCreateDate()};
		int result = this.executeUpdate(sql, params);
		return result;
	}

	@Override
	public Message queryMsgById(int msgId) {
		String sql = "SELECT msgid, username, title, msg_content, state, sendto, msg_create_date FROM message WHERE msgid = ?";
		Object[] params = {msgId};
		ResultSet rs = this.executeQuery(sql, params);
		Message msg = new Message();
		//循环遍历，取出每一个数据
		try {
			while(rs.next()) {
				int id = rs.getInt("msgid");
				String name = rs.getString("username");
				String title = rs.getString("title");
				String msgContent = rs.getString("msg_content");
				int state = rs.getInt("state");
				String sendto = rs.getString("sendto");
				Date createDate = rs.getDate("msg_create_date");
				
				msg.setMsgid(id);
				msg.setUsername(name);
				msg.setTitle(title);
				msg.setMsgContent(msgContent);
				msg.setState(state);
				msg.setSendto(sendto);
				msg.setMsgCreateDate(createDate);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return msg;
	}

	@Override
	public int updateMsgById(int msgId) {
		String sql = "UPDATE message SET state = 1 WHERE msgid = ?";
		Object[] params = {msgId};
		int result = this.executeUpdate(sql, params);
		return result;
	}

	@Override
	public void deleteMsg(int msgid) {
		String sql = "DELETE FROM message WHERE msgid = ?";
		Object[] params = {msgid};
		this.executeUpdate(sql, params);
	}

}
