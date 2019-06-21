package com.msg.web;

import java.io.IOException;
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
import com.msg.service.impl.MessageServiceImpl;

/**
 * Servlet implementation class MsgServlet
 */
@WebServlet("/MsgServlet")
public class MsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	private MessageService messageSercie= new MessageServiceImpl();
	
    public MsgServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		if(flag!=null) {
			if(flag.equals("toView")) {//跳转到查看短消息的页面
				//调用查看方法
				this.doQueryMsg(request, response);
			}else if(flag.equals("toBack")) {
				//调用返回办法
				this.doBack(request, response);
			}else if(flag.equals("toHuifu")) {
				//调用回复办法
				this.doHuiFu(request, response);
			}else if(flag.equals("deleteMsg")) {
				//调用删除方法
				this.deleteMsg(request, response);
			}else if(flag.equals("readMsg")) {
				//调用更新为已读的方法
				this.readMsg(request, response);
			}
		}
	}
	
	/**
	 * 把短消息的状态改为已读
	 * @param request
	 * @param response
	 * @throws ServletException 
	 * @throws IOException 
	 */
	private void readMsg(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int msgid = Integer.parseInt(request.getParameter("msgid"));
		messageSercie.readMsg(msgid);
		
		this.doBack(request, response);
	}

	/**
	 * 执行删除方法，删除后返回到列表页面
	 * @param request
	 * @param response
	 * @throws ServletException 
	 * @throws IOException 
	 */
	private void deleteMsg(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int msgid = Integer.parseInt(request.getParameter("msgid"));
		messageSercie.deleteMsg(msgid);
		
		this.doBack(request, response);
	}

	/**
	 * 跳转到回复页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doHuiFu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sendto  = request.getParameter("sendto");
		String username = request.getParameter("username");
		request.setAttribute("sendto", sendto);
		request.setAttribute("username", username);
		request.getRequestDispatcher("huifuMsg.jsp").forward(request, response);
	}

	/**
	 * 查看后，返回到列表页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doBack(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if(obj==null) {
			//如果sesion中user是空，重定向到登陆页面
			response.sendRedirect("login.jsp");
		}
		User user = (User)obj;
		//调用查询当前用户的收件箱的方法，查询收件内容
		List<Message> msgList = messageSercie.queryMsgBySendto(user.getUsername());
		//把收件箱内容中短消息封装到msgList
		//request.setAttribute("msgList", msgList);
		session.setAttribute("msgList", msgList);
		request.getRequestDispatcher("msgList.jsp").forward(request, response);
	}

	/**
	 * 查看短消息方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doQueryMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int msgId = Integer.parseInt(request.getParameter("msgid"));
		//调用service，执行查询
		Message msg = messageSercie.queryMsgById(msgId);
		//返回到页面上
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("viewMsg.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
