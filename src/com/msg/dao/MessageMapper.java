package com.msg.dao;

import com.msg.entity.Message;

import java.util.List;

public interface MessageMapper {
    /**
     * 根据收件人姓名查询短消息集合
     * @param username
     * @return
     */
    List<Message> queryMsgBySendto(String username);

    /**
     * 新增短消息
     * @param msg
     * @return
     */
    int addMsg(Message msg);

    /**
     * 根据id查询短消息
     * @param msgId
     * @return
     */
    Message queryMsgById(int msgId);

    /**
     * 更新指定id的短消息，状态改为已读
     * @param msgId
     * @return
     */
    int updateMsgById(int msgId);

    /**
     * 根据id删除短消息
     * @param msgid
     */
    int deleteMsg(int msgid);
}
