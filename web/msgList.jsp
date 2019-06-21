<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>短消息列表</title>
	  <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="css/nprogress.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="css/custom.min.css" rel="stylesheet">
    
    <script type="text/javascript">
    	function logout(){
    		window.location="UserServlet?flag=logout";
    	}
    </script>
</head>
<body>
	<div class="x_panel">
                  <div class="x_title">
                    <div class="actionBar">
                    		<h2>${user.username }<small>欢迎你</small></h2>
                			<button type="button" class="btn btn-danger" onclick="logout()">注销</button>
                     </div>
                     <div class="clearfix"></div>
                  </div>
                  <div>
		                <a href="UserServlet?flag=sendMsg" class="btn btn-success">发送新的短信息</a>
                  </div> 
                  <div>${error }</div>
  				
                  <div class="x_content">
                    <h2>我的收件箱</h2>
                    <div class="table-responsive">
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings">
                            <th>
                              <div class="icheckbox_flat-green" style="position: relative;"><input id="check-all" class="flat" style="position: absolute; opacity: 0;" type="checkbox"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;"></ins></div>
                            </th>
                            <th class="column-title">发件人</th>
                            <th class="column-title">收件人 </th>
                            <th class="column-title">标题</th>
                            <th class="column-title">状态</th>
                            <th class="column-title no-link last"><span class="nobr">操作</span>
                            </th>
                            <th class="bulk-actions" colspan="7">
                              <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                            </th>
                          </tr>
                        </thead>

                        <tbody>
                          <c:forEach items="${msgList}" var="msg">
                          	 <tr class="odd pointer">
	                            <td class="a-center ">
	                              <div class="icheckbox_flat-green" style="position: relative;"><input class="flat" name="table_records" style="position: absolute; opacity: 0;" type="checkbox"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;"></ins></div>
	                            </td>
	                            <td class=" ">${msg.username }</td>
	                            <td class=" ">${msg.sendto}</td>
	                            <td class=" ">
	                            	<a href="MsgServlet?flag=toView&msgid=${msg.msgid }">${msg.title}</a>
	                            	</td>
	                            <td class=" ">
		                            <c:if test="${msg.state==0}">未读</c:if>
		                            <c:if test="${msg.state==1}">已读</c:if>
	                            </td>
	                            <td class=" ">
	                            
		                            <c:if test="${msg.state!=1}">
		                            	<a href="#" onclick="yidu(${msg.msgid })">已读</a>
		                            </c:if>
		                            <c:if test="${msg.state==1}">
		                            	<a href="#" onclick="deleteMsg(${msg.msgid })">删除</a>
		                            </c:if>
	                            </td>
	                          </tr>
                          </c:forEach>
                          
                          
                        </tbody>
                      </table>
                    </div>
                  </div>
                   <div class="x_content">
                    <h2>我的发件箱</h2>
                    <div class="table-responsive">
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings">
                            <th>
                              <div class="icheckbox_flat-green" style="position: relative;"><input id="check-all" class="flat" style="position: absolute; opacity: 0;" type="checkbox"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;"></ins></div>
                            </th>
                            <th class="column-title">发件人</th>
                            <th class="column-title">收件人 </th>
                            <th class="column-title">标题</th>
                            <th class="column-title">状态</th>
                            <th class="column-title no-link last"><span class="nobr">操作</span>
                            </th>
                            <th class="bulk-actions" colspan="7">
                              <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                            </th>
                          </tr>
                        </thead>

                        <tbody>
                          <tr class="even pointer">
                            <td class="a-center ">
                              <div class="icheckbox_flat-green" style="position: relative;"><input class="flat" name="table_records" style="position: absolute; opacity: 0;" type="checkbox"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;"></ins></div>
                            </td>
                            <td class=" ">zhangsan</td>
                            <td class=" ">lisi</td>
                            <td class=" ">最近咋样？</td>
                            <td class=" ">已读</td>
                            <td class=" ">
								<a href="#">已读</a>
								<a href="#">删除</a>
                            </td>
                            </td>
                          </tr>
                          <tr class="odd pointer">
                            <td class="a-center ">
                              <div class="icheckbox_flat-green" style="position: relative;"><input class="flat" name="table_records" style="position: absolute; opacity: 0;" type="checkbox"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;"></ins></div>
                            </td>
                            <td class=" ">zhangsan</td>
                            <td class=" ">wangwu</td>
                            <td class=" ">最近还行吧？</td>
                            <td class=" ">未读</td>
                            <td class=" ">
								<a href="#">已读</a>
								<a href="#">删除</a>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                  
                </div>
    
    <script type="text/javascript">
    	function yidu(id){
			var duFlag = window.confirm("是否确认已读？");   
			if(duFlag){
    			window.location.href="MsgServlet?flag=readMsg&msgid="+id;
			}
    	}
    	function deleteMsg(msgid){
    		var delFlag = window.confirm("是否删除");
    		if(delFlag){
    			window.location.href="MsgServlet?flag=deleteMsg&msgid="+msgid;
    		}else{
    			alert("取消删除！");
    		}
    	}
    </script>
    
</body>
</html>
