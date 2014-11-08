<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="/WEB-INF/tlds/message.tld"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Task Tracker</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap-theme.css" type="text/css">
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Task Tracker</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">Settings</a></li>
            <li class="dropdown">
          		<a href="#" class="dropdown-toggle" data-toggle="dropdown"><m:message message="def.nav.lang"/><span class="caret"></span></a>
          		<ul class="dropdown-menu" role="menu">
            		<li><a href="${pageContext.request.contextPath}/messages/change?lang=RU">ru</a></li>
            		<li><a href="${pageContext.request.contextPath}/messages/change?lang=EN">en</a></li>
          		</ul>
        	</li>
            <li>
            	<a href="${pageContext.request.contextPath}/login/logout" class="navbar-link"><span class="label label-primary">${logged_user.firstName} ${logged_user.lastName}</span></a>
            </li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row">
      
      	<!-- SIDE MENU -->
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class='<c:if test="${tasksView || taskCreate || taskInfo || taskUpdate}">active</c:if>'>
            	<a href="${pageContext.request.contextPath}/tasks"><m:message message="def.side.menu.all.tasks"/></a>
            </li>
            <li>
            	<a href="#"><m:message message="def.side.menu.my.tasks"/></a>
            </li>
          </ul>
          <ul class="nav nav-sidebar">
            <li class='<c:if test="${usersView || userInfo || userCreate || userUpdate}">active</c:if>'>
            	<a href="${pageContext.request.contextPath}/users"><m:message message="def.side.menu.users"/></a>
            </li>
          </ul>
        </div>
        
        <!-- MAIN CONTENT -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <c:if test="${tasksView}">
          	<jsp:include page="../task/index.jsp"/>	
          </c:if>
          <c:if test="${taskCreate}">
          	<jsp:include page="../task/create.jsp"/>	
          </c:if>
          <c:if test="${taskUpdate}">
          	<jsp:include page="../task/update.jsp"/>	
          </c:if>
          <c:if test="${usersView}">
          	<jsp:include page="../user/index.jsp"/>	
          </c:if>
          <c:if test="${userCreate}">
          	<jsp:include page="../user/create.jsp"/>	
          </c:if>
          <c:if test="${userUpdate}">
          	<jsp:include page="../user/update.jsp"/>	
          </c:if>
        </div>
        
      </div>
    </div>
	
	<!-- FOOTER -->
	<nav class="navbar navbar-inverse navbar-fixed-bottom" role="navigation">
  		<div class="container">
    		<div class="navbar-header">
      			<a class="navbar-brand" href="#">
        			All rights reserved
      			</a>
    		</div>
  		</div>
	</nav>
</body>
</html>