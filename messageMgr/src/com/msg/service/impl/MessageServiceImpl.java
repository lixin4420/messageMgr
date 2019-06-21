package com.msg.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.msg.dao.MessageDao;
import com.msg.dao.impl.MessageDaoImpl;
import com.msg.entity.Message;
import com.msg.service.MessageService;
import com.msg.util.DataBaseUtil;

public class MessageServiceImpl implements MessageService {

	@Override
	public List<Message> queryMsgBySendto(String username) {
		List<Message> list = new ArrayList<Message>();
		//准备连接对象
		Connection conn = DataBaseUtil.getConnection();
		//准备dao层对象
		MessageDao msgDao = new MessageDaoImpl(conn);
		//调用dao层对象方法
		list = msgDao.queryMsgBySendto(username);
		DataBaseUtil.closeAll(conn, null, null);
		return list;
	}

	@Override
	public boolean addMsg(Message msg) {
		//定义返回值
		boolean flag = false;
		//处理开始
		//1. 获取连接对象
		Connection conn = DataBaseUtil.getConnection(); 
		//2. 准备dao层对象
		MessageDao msgDao = new MessageDaoImpl(conn);
		//3. 执行dao层方法
		int result = msgDao.addMsg(msg);
		if(result>=1) {//执行成功
			flag = true;
		}
		//4. 关闭连接对象
		DataBaseUtil.closeAll(conn, null, null);
		//处理结束
		return flag;
	}

	@Override
	public Message queryMsgById(int msgId) {
		//定义返回对象
		Message msg = null;
		//开始处理
		//1. 获取连接对象
		Connection conn = DataBaseUtil.getConnection(); 
		//2. 准备dao层对象
		MessageDao msgDao = new MessageDaoImpl(conn);
		//3. 执行dao层方法-查询查询
		msg = msgDao.queryMsgById(msgId);
		//3. 执行dao层方法-更新状态，改为1-已读
		int reuslt = msgDao.updateMsgById(msgId);
		//4. 关闭连接对象
		DataBaseUtil.closeAll(conn, null, null);
		//处理结束
		return msg;
	}

	@Override
	public void deleteMsg(int msgid) {
		Connection conn = DataBaseUtil.getConnection(); 
		
		MessageDao msgDao = new MessageDaoImpl(conn);
		msgDao.deleteMsg(msgid);
		
		DataBaseUtil.closeAll(conn, null, null);
	}

	@Override
	public void readMsg(int msgid) {
		Connection conn = DataBaseUtil.getConnection(); 
	
		MessageDao msgDao = new MessageDaoImpl(conn);
		msgDao.updateMsgById(msgid);
		
		DataBaseUtil.closeAll(conn, null, null);
	}

}
