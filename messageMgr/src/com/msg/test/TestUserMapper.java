package com.msg.test;

import com.msg.dao.UserMapper;
import com.msg.entity.User;
import com.msg.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestUserMapper {
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
    public void testgetUserList(){
        List<User> list = sqlSession.getMapper(UserMapper.class).getUserList("zhangsan");
        for (User user:list){
            System.out.println(user.getUsername());
        }
    }
    @Test
    public void testaddUser(){
        User user= new User();
        user.setUsername("lixin");
        user.setEmail("2273337383@qq.com");
        user.setPassword("123123");
        int num = sqlSession.getMapper(UserMapper.class).addUser(user);
        if(num>0){
            System.out.println("新增成功");
        }else{
            System.out.println("新增失败");
        }
        sqlSession.commit();
    }
    @Test
    public void testcountUserByName(){
        int num = sqlSession.getMapper(UserMapper.class).countUserByName("zhangsan");
        System.out.println("用户数量"+num);
    }

    @Test
    public void testLoginUser(){
        SqlSession sqlSession = MyBatisUtil.createSqlSession();
        User user = new User();
        user.setPassword("123123");
        user.setUsername("zhangsan");
        User loginUser = sqlSession.getMapper(UserMapper.class).doLogin(user);
        System.out.println(loginUser.getUsername());
        MyBatisUtil.closeSqlSession(sqlSession);
    }


}
