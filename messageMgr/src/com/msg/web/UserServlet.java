package com.msg.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.msg.entity.Message;
import com.msg.entity.User;
import com.msg.service.MessageService;
import com.msg.service.UserService;
import com.msg.service.impl.MessageServiceImpl;
import com.msg.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserService userService = new UserServiceImpl();
	private MessageService messageService = new MessageServiceImpl();
    
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收标志，进入相应的功能模块
		String flag = request.getParameter("flag");
		if(flag.equals("login")) {
			//调用登录功能
			this.doLogin(request, response);
		}else if(flag.equals("logout")) {
			//调用退出功能
			this.doLogout(request, response);
		}else if(flag.equals("sendMsg")) {
			//调用跳转到 发送短消息页面的功能
			this.toSendMsg(request, response);
		}else if(flag.equals("addMsg")) {
			//跳转到新增短信的功能
			this.doSendMsg(request, response);
		}else if(flag.equals("addUser")) {
			//跳转到新增用户功能
			this.doAddUser(request, response);
		}else if(flag.equals("jiaoyan")) {
			//跳转到用户校验
			this.doJiaoYan(request, response);
		}
		
	}

	/**
	 * 执行校验，并把结果返回到页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doJiaoYan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户名
		String username = request.getParameter("username");
		//调用sevice层查询该用户是否存在
		int x = userService.countUserByName(username);
		//封装查询结果
		request.setAttribute("jieguo", x);
		request.setAttribute("username", username);
		//返回到页面
		request.getRequestDispatcher("addUser.jsp").forward(request, response);
		
	}

	/**
	 * 执行新增用户，完毕返回到登录页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doAddUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取用户数据
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		String email = request.getParameter("email");
		User user = new User();
		user.setUsername(name);
		user.setPassword(pwd);
		user.setEmail(email);
		//调用service
		userService.addUser(user);
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * 新增短消息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doSendMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户名-发送人
		String username = request.getParameter("username");
		//获取标题
		String title = request.getParameter("title");
		//获取内容
		String msgContent = request.getParameter("msgContent");
		//获取接收人
		String sendto = request.getParameter("sendto");
		//封装数据
		Message msg = new Message();
		msg.setMsgContent(msgContent);
		msg.setMsgCreateDate(new Date());
		msg.setSendto(sendto);
		msg.setState(0);//新发消息，0未读 1已读
		msg.setTitle(title);
		msg.setUsername(username);
		//调用service的保存方法
		boolean flag = messageService.addMsg(msg);
		if(flag==false) {
			request.setAttribute("error", "错误提示：保存失败！");
		}
		
		//调用方法，返回列表页面
		//调用查询当前用户的收件箱的方法，查询收件内容
		HttpSession session= request.getSession();
		User user = (User) session.getAttribute("user");
		String name = user.getUsername();
		List<Message> msgList = messageService.queryMsgBySendto(name);
		//把收件箱内容中短消息封装到msgList
		//request.setAttribute("msgList", msgList);

		session.setAttribute("msgList", msgList);
		//转发到msgList.jsp页面上
		request.getRequestDispatcher("msgList.jsp").forward(request, response);
	}

	/**
	 * 跳转到发送短消息页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toSendMsg(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if(obj==null) {
			//如果sesion中user是空，重定向到登陆页面
			response.sendRedirect("login.jsp");
		}
		User user = (User)obj;
		//获取用户列表(除了当前用户)
		List<User> userList = userService.getUserList(user.getUsername());
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("sendMsg.jsp").forward(request, response);
	}

	/**
	 * 执行退出方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//从session中去除用户对象
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		//如果是30分钟后，obj肯定是空，这时候是不能调用 session.removeAttribute("user");
		if(obj!=null) {
			session.removeAttribute("user");
		}
		//重定向到登陆页面
		response.sendRedirect("login.jsp");
	}

	/**
	 * 执行登录方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户输入的数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User loginUser = new User();
		loginUser.setUsername(username);
		loginUser.setPassword(password);
		//调用Service对象，执行登录业务操作
		User user = userService.doLogin(loginUser);
		if(user==null) {//账号或密码错误，查不到用户
			request.setAttribute("errorInfo", "账号或密码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {//查到了用户
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			//调用查询当前用户的收件箱的方法，查询收件内容
			List<Message> msgList = messageService.queryMsgBySendto(user.getUsername());
			//把收件箱内容中短消息封装到msgList
			//request.setAttribute("msgList", msgList);
			session.setAttribute("msgList", msgList);
			//转发到msgList.jsp页面上
			request.getRequestDispatcher("msgList.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
