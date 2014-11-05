<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="/WEB-INF/tlds/message.tld"%>

<div class="page-header">
	<h3><m:message message="user.page.header"/> <small>${userObj.firstName} ${userObj.lastName}</small></h3>
</div>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">User info</h3>
  </div>
  <div class="panel-body">
  	<div class="col-md-6">
    <table class="table table-hover">
    	<tbody>
    		<tr>
    			<th><m:message message="user.page.login"/></th>
    			<td>${userObj.login}</td>
    		</tr>
    		<tr>
    			<th><m:message message="user.page.firstname"/></th>
    			<td>${userObj.firstName}</td>
    		</tr>
    		<tr>
    			<th><m:message message="user.page.lastname"/></th>
    			<td>${userObj.lastName}</td>
    		</tr>
    		<tr>
    			<th><m:message message="user.page.email"/></th>
    			<td>${userObj.email}</td>
    		</tr>
    		<tr>
    			<th><m:message message="user.page.phone"/></th>
    			<td>${userObj.phoneNumber}</td>
    		</tr>
    	</tbody>
    </table>
    </div>
    <div class="col-md-6">
    	<table class="table table-hover">
    		<tbody>
    			<tr>
    				<th><m:message message="user.page.manager"/></th>
    				<td>${userObj.manager.firstName} ${userObj.manager.lastName}</td>
    			</tr>
    			<tr>
    				<th><m:message message="user.page.created"/></th>
    				<td>${userObj.createdAt}</td>
    			</tr>
    			<tr>
    				<th><m:message message="user.page.updated"/></th>
    				<td>${userObj.updatedAt}</td>
    			</tr>
    		</tbody>
    	</table>
    </div>
  </div>
</div>