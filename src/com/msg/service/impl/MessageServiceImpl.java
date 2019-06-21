package com.msg.service.impl;

import com.msg.dao.MessageMapper;
import com.msg.entity.Message;
import com.msg.service.MessageService;
import com.msg.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.msg.util.MyBatisUtil.closeSqlSession;

public class MessageServiceImpl implements MessageService {

	@Override
	public List<Message> queryMsgBySendto(String username) {
		SqlSession sqlSession = MyBatisUtil.createSqlSession();
		List<Message> list = sqlSession.getMapper(MessageMapper.class).queryMsgBySendto(username);
		closeSqlSession(sqlSession);
		return list;
	}

	@Override
	public boolean addMsg(Message msg) {
		boolean flag = false;
		SqlSession sqlSession = MyBatisUtil.createSqlSession();
		sqlSession.getMapper(MessageMapper.class).addMsg(msg);
		closeSqlSession(sqlSession);
		sqlSession.commit();
		return flag;
	}

	@Override
	public Message queryMsgById(int msgId) {
		SqlSession sqlSession = MyBatisUtil.createSqlSession();
		Message message = sqlSession.getMapper(MessageMapper.class).queryMsgById(msgId);
		MyBatisUtil.closeSqlSession(sqlSession);
		return message;
	}

	@Override
	public void deleteMsg(int msgid) {
		SqlSession sqlSession = MyBatisUtil.createSqlSession();
		
		sqlSession.getMapper(MessageMapper.class).deleteMsg(msgid);
		sqlSession.commit();
		MyBatisUtil.closeSqlSession(sqlSession);
	}

	@Override
	public void readMsg(int msgid) {
        SqlSession sqlSession = MyBatisUtil.createSqlSession();

        sqlSession.getMapper(MessageMapper.class).updateMsgById(msgid);
        sqlSession.commit();
        MyBatisUtil.closeSqlSession(sqlSession);
	}

}
