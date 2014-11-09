<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="/WEB-INF/tlds/message.tld"%>

<div class="panel panel-info">
  <div class="panel-heading">
    <h3 class="panel-title">
    	<label class="label label-primary"><m:message message="user.page.header"/> - ${user.firstName} ${user.lastName}</label>
    </h3>
  </div>
  <div class="panel-body">
  	<div class="col-md-6">
    <table class="table table-hover">
    	<tbody>
    		<tr>
    			<th><m:message message="user.attr.login"/></th>
    			<td>${user.login}</td>
    		</tr>
    		<tr>
    			<th><m:message message="user.attr.firstname"/></th>
    			<td>${user.firstName}</td>
    		</tr>
    		<tr>
    			<th><m:message message="user.attr.lastname"/></th>
    			<td>${user.lastName}</td>
    		</tr>
    		<tr>
    			<th><m:message message="user.attr.email"/></th>
    			<td>${user.email}</td>
    		</tr>
    		<tr>
    			<th><m:message message="user.attr.phone"/></th>
    			<td>${user.phoneNumber}</td>
    		</tr>
    	</tbody>
    </table>
    </div>
    <div class="col-md-6">
    	<table class="table table-hover">
    		<tbody>
    			<tr>
    				<th><m:message message="user.attr.created"/></th>
    				<td>${user.createdAt}</td>
    			</tr>
    			<tr>
    				<th><m:message message="user.attr.updated"/></th>
    				<td>${user.updatedAt}</td>
    			</tr>
    		</tbody>
    	</table>
    </div>
  </div>
</div>