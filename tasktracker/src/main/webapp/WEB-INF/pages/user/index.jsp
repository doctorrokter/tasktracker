<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="/WEB-INF/tlds/message.tld"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-combined.min.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-tree.js"></script>

<div class="page-header">
	<h3><m:message message="user.index.page.header"/>
		<div class="pull-right">
			<c:forEach items="${logged_user.roles}" var="role">
				<c:if test="${role.name eq 'admin'}">
					<a href="${pageContext.request.contextPath}/users/create" role="button" class="btn btn-default">
						<img src="${pageContext.request.contextPath}/resources/images/user.png"/> <m:message message="user.create.user.btn"/>
					</a>
					<a href="${pageContext.request.contextPath}/users/update/${user.id}" type="button" role="button" class="btn btn-default">
  						<img src="${pageContext.request.contextPath}/resources/images/update.png"/> <m:message message="user.update.user.btn"/>
  					</a>
  					<a href="${pageContext.request.contextPath}/users/delete/${user.id}" role="button" class="btn btn-default">
  						<img src="${pageContext.request.contextPath}/resources/images/delete.png"/> <m:message message="user.delete.user.btn"/>
  					</a>
  				</c:if>
  			</c:forEach>
  		</div>
	</h3>	
</div>

<div class="row-fluid">
	<section role="main" class="col-md-4">
		<div class="tree">
			<ul>
				<li>
					<span><i class="icon-folder-open"></i>Users</span>
					<c:forEach items="${usersTree}" var="tree">
						<ul>
							<li>
								<span><img src="${pageContext.request.contextPath}/resources/images/circle.png"/></span> <label class="label label-primary"> ${tree.key.name}</label>
								<ul>
									<c:forEach items="${tree.value}" var="user">
										<li>
											<a href="${pageContext.request.contextPath}/users/info/${user.id}"><label class="label label-success">${user.firstName} ${user.lastName}</label></a>
										</li>
									</c:forEach>
								</ul>
							</li>
						</ul>
					</c:forEach>
				</li>
			</ul>
		</div>
	</section>
	<c:choose>
		<c:when test="${userInfo}">
			<section class="col-md-8">
				<jsp:include page="user.jsp"/>
			</section>
		</c:when>
		<c:otherwise>
			<section class="col-md-8">
				<div class="jumbotron">
  					<h1><m:message message="no.users.chosen"/></h1>
  					<h2><small><m:message message="choose.user.from.list"/></small></h2>
				</div>
			</section>
		</c:otherwise>
	</c:choose>
</div>