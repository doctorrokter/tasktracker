<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="/WEB-INF/tlds/message.tld"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-combined.min.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-tree.js"></script>

<div class="page-header">
	<h3>Tasks
		<div class="pull-right">
			<a href="${pageContext.request.contextPath}/tasks/create?categoryId=1" type="button" role="button" class="btn btn-default">
				<img src="${pageContext.request.contextPath}/resources/images/project.png"/> <m:message message="task.create.project.btn"/>
			</a>
  			<a href="${pageContext.request.contextPath}/tasks/create?categoryId=2&parentId=${task.id}" type="button" role="button" class="btn btn-default">
  				<img src="${pageContext.request.contextPath}/resources/images/task.png"/> <m:message message="task.create.task.btn"/>
  			</a>
  			<a href="${pageContext.request.contextPath}/tasks/create?categoryId=3&parentId=${task.id}" type="button" role="button" class="btn btn-default">
  				<img src="${pageContext.request.contextPath}/resources/images/bug.png"/> <m:message message="task.create.bug.btn"/>
  			</a>
  			
  			<c:if test="${taskInfo}">
  				<a href="${pageContext.request.contextPath}/tasks/update/${task.id}" type="button" role="button" class="btn btn-default">
  					<img src="${pageContext.request.contextPath}/resources/images/update.png"/> <m:message message="task.update.def.btn"/>
  				</a>
  				<a href="${pageContext.request.contextPath}/tasks/delete/${task.id}" type="button" role="button" class="btn btn-default">
  					<img src="${pageContext.request.contextPath}/resources/images/delete.png"/> <m:message message="task.delete.def.btn"/>
  				</a>
  			</c:if>
  		</div>
	</h3>	
</div>

<div class="row-fluid">
	<c:if test="${not empty error}">
		<div class="alert alert-danger" role="alert">${error}</div>
	</c:if>
	<section id="demonstration" role="main" class="col-md-4">
		<div class="tree">
			<ul>
				<li>
					<span><i class="icon-folder-open"></i>Projects</span>
					<c:forEach items="${tasksTree}" var="tree">
						<ul>
							<li>
								<span><img src="${pageContext.request.contextPath}/resources/images/circle.png"/></span> <a href="${pageContext.request.contextPath}/tasks/info/${tree.key.id}"><label class="label label-primary"> ${tree.key.title}</label></a>
								<ul>
									<c:forEach items="${tree.value}" var="subTask">
										<li>
											<span><i class="icon-time"></i>#${subTask.id}</span> &ndash; 
											<c:choose>
												<c:when test="${subTask.category.id == 2}">
													<label class="label label-success">${subTask.category.name}</label>
												</c:when>
												<c:otherwise>
													<label class="label label-danger">${subTask.category.name}</label>
												</c:otherwise>
											</c:choose> &ndash; <a href="${pageContext.request.contextPath}/tasks/info/${subTask.id}"><label class="label label-warning">${subTask.title}</label></a>
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
		<c:when test="${taskInfo}">
			<section class="col-md-8">
				<jsp:include page="task.jsp"/>
			</section>
		</c:when>
		<c:otherwise>
			<section class="col-md-8">
				<div class="jumbotron">
  					<h1>No tasks choosed...</h1>
  					<h2><small>Try to choose any task from list to watch info.</small></h2>
				</div>
			</section>
		</c:otherwise>
	</c:choose>
</div>