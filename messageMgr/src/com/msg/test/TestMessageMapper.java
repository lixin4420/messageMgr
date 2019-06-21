package com.msg.test;

import com.msg.dao.MessageMapper;
import com.msg.entity.Message;
import com.msg.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestMessageMapper {
    SqlSession sqlSession = null;

    //1. 获取会话对象
    @Before
    public void beforeMethod(){
        sqlSession = MyBatisUtil.createSqlSession();
    }

    //3. 关闭会话对象
    @After
    public void afterMethod(){
        MyBatisUtil.closeSqlSession(sqlSession);
    }

    @Test
    public void testqueryMsgBySendto(){
        List<Message> list = sqlSession.getMapper(MessageMapper.class).queryMsgBySendto("zhangsan");
        for (Message message :list){
            System.out.println(message.getUsername());
        }
    }
    @Test
    public void testaddMessage(){
        Message message = new Message();
        message.setMsgContent("最近没事吧");
        message.setSendto("wangwu");
        message.setState(0);
        message.setTitle("你怎么样了");
//        Date date = message.setMsgCreateDate(2019-06-20);
        message.setUsername("zhangsan");
        int num = sqlSession.getMapper(MessageMapper.class).addMsg(message);
        if(num>0){
            System.out.println("新增成功");
        }else{
            System.out.println("新增失败");
        }
        sqlSession.commit();
    }
    @Test
    public void testqueryMsgById(){
        Message message = sqlSession.getMapper(MessageMapper.class).queryMsgById(1);
        System.out.println(message.getUsername());
    }
    @Test
    public void testUpdateMsgById(){
        int num = sqlSession.getMapper(MessageMapper.class).updateMsgById(1);
        if (num>0){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失败");
        }
        sqlSession.commit();
    }
    @Test
    public void testDeleteMsgById(){
        int num = sqlSession.getMapper(MessageMapper.class).deleteMsg(1);
        if(num>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
        sqlSession.commit();
    }
}
