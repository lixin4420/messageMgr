<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>用户注册</title>
	  <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="css/nprogress.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="css/custom.min.css" rel="stylesheet">
</head>
<body>
	<div class="x_panel">
                 
                  <div class="x_content">

                    <form class="form-horizontal form-label-left" novalidate="" method="post" action="UserServlet?flag=addUser" onsubmit="return jiaoyan2()">
                      <span class="section">用户注册</span>

                      <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="username">用户名 <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input id="username" name="username" required="required" class="form-control col-md-7 col-xs-12" type="text" value="${username }" onblur="jiaoyan()">
                          <c:if test="${jieguo>=1 }">
                          	<span style="color:red;">用户名已存在</span>
                          	<input type="hidden" id="biaozhi" value="yicunzai">
                          </c:if> 
                          <c:if test="${jieguo==0 }">
                          	<span style="color:green;">用户名可以使用</span>
                          	<input type="hidden" id="biaozhi" value="bucunzai">
                          </c:if>
                        </div>
                      </div>
                      
                       <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="password">密码 <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input id="password" name="password" required="required" class="form-control col-md-7 col-xs-12" type="password">
                        </div>
                      </div>
                      
                       <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">邮箱 <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input id="email" name="email" required="required" class="form-control col-md-7 col-xs-12" type="email">
                        </div>
                      </div>
                      
                      <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-6 col-md-offset-3">
                          <button type="reset" class="btn btn-primary">重置</button>
                          <button id="send" type="submit" class="btn btn-success">注册</button>
                        </div>
                      </div>
                    </form>
                    <script type="text/javascript">
                    	function jiaoyan(){
							var username = document.getElementById("username").value;
							window.location.href="UserServlet?flag=jiaoyan&username="+username;
                    	}
                    	function jiaoyan2(){
                    		var biaozhi = document.getElementById("biaozhi").value;
                    		if(biaozhi=="yicunzai"){
                    			alert("该用户名已经存在，不能提交");
                    			return false;
                    		}else {
                    			return true;
                    		}
                    	}
                    </script>
                  </div>
                </div>
</body>
</html>