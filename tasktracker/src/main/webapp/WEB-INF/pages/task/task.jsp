<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="/WEB-INF/tlds/message.tld"%>

<div class="panel panel-info">
  <div class="panel-heading">
    <h3 class="panel-title">
    	<label class="label label-primary">${task.category.name} #${task.id} &ndash; ${task.title}</label>
    </h3>
  </div>
  <div class="panel-body">
  	<div class="col-md-6">
    <table class="table table-hover">
    	<tbody>
    		<tr>
    			<th><m:message message="task.page.title"/></th>
    			<td>${task.title}</td>
    		</tr>
    		<tr>
    			<th><m:message message="task.page.category"/></th>
    			<td><label class="label label-default">${task.category.name}</label></td>
    		</tr>
    		<tr>
    			<th><m:message message="task.page.created.at"/></th>
    			<td>${task.createdAt}</td>
    		</tr>
    		<tr>
    			<th><m:message message="task.page.updated.at"/></th>
    			<td>${task.updatedAt}</td>
    		</tr>
    	</tbody>
    </table>
    </div>
    <div class="col-md-6">
    <table class="table table-hover">
    	<tbody>
    		<tr>
    			<th><m:message message="task.page.deadline"/></th>
    			<td>${task.deadline}</td>
    		</tr>
    		<tr>
    			<th><m:message message="task.page.creator"/></th>
    			<td><label class="label label-info">${task.creator.firstName} ${task.creator.lastName}</label></td>
    		</tr>
    		<tr>
    			<th><m:message message="task.page.assignee"/></th>
    			<td><label class="label label-success">${task.assignee.firstName} ${task.assignee.lastName}</label></td>
    		</tr>
    		<tr>
    			<th><m:message message="task.page.status"/></th>
    			<td><label class="label label-warning">${task.status.name}</label></td>
    		</tr>
    	</tbody>
    </table>
    </div>
    <table class="table table-hover">
    	<thead>
    		<tr>
    			<th>
    				<h3 class="panel-title"><label class="label label-primary"><m:message message="task.page.description"/></label></h3>
    			</th>
    		</tr>
    	</thead>
    	<tbody>
    		<tr>
    			<td>${task.description}</td>
    		</tr>
    	</tbody>
    </table>
   </div>
 </div>
 
 <div class="panel panel-info">
  <div class="panel-heading">
    <h3 class="panel-title">
    	<label class="label label-primary">Comments</label>
    </h3>
  </div>
  <div class="panel-body">
  	<div class="col-md-12">
    	<c:forEach items="${task.comments}" var="comment">
    		<div class="alert alert-success" role="alert">
    			<label class="label label-success">
    				changed by <i>${comment.user.firstName} ${comment.user.lastName}</i>
    			</label>
    			<label class="label label-success pull-right">
    				${comment.createdAt}
    			</label>
    			<p>${comment.comment}</p>
    		</div>
    	</c:forEach>
    	
    </div>
   </div>
 </div>