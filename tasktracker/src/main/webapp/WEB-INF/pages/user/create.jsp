<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="/WEB-INF/tlds/message.tld"%>

<div class="page-header">
	<h3><m:message message="user.create.page.header"/></h3>
</div>

<form role="form" class="form-horizontal" action="${pageContext.request.contextPath}/users/doCreate" method="POST">
	<div class="form-group">
    	<label for="login" class="col-sm-2 control-label"><m:message message="user.attr.login"/></label>
    	<div class="col-sm-4">
    		<input type="text" class="form-control" id="login" name="login" required="required">
    	</div>
  	</div>
  	<div class="form-group">
    	<label for="password" class="col-sm-2 control-label"><m:message message="user.attr.password"/></label>
    	<div class="col-sm-4">
    		<input type="password" class="form-control" id="password" name="password" required="required">
    	</div>
  	</div>
  	<div class="form-group">
    	<label for="firstName" class="col-sm-2 control-label"><m:message message="user.attr.firstname"/></label>
    	<div class="col-sm-4">
    		<input type="text" class="form-control" id="firstName" name="firstName" required="required">
    	</div>
  	</div>
  	<div class="form-group">
    	<label for="lastName" class="col-sm-2 control-label"><m:message message="user.attr.lastname"/></label>
    	<div class="col-sm-4">
    		<input type="text" class="form-control" id="lastName" name="lastName" required="required">
    	</div>
  	</div>
  	<div class="form-group">
    	<label for="email" class="col-sm-2 control-label"><m:message message="user.attr.email"/></label>
    	<div class="col-sm-4">
    		<input type="email" class="form-control" id="email" name="email">
    	</div>
  	</div>
  	<div class="form-group">
    	<label for="phoneNumber" class="col-sm-2 control-label"><m:message message="user.attr.phone"/></label>
    	<div class="col-sm-4">
    		<input type="text" class="form-control" id="phoneNumber" name="phoneNumber">
    	</div>
  	</div>
  	<div class="form-group">
    	<label for="role" class="col-sm-2 control-label"><m:message message="user.attr.role"/></label>
    	<div class="col-sm-4">
    		<c:forEach items="${rolesList}" var="role">
				<div class="checkbox">
					<label><input type="checkbox" name="role" value="${role.id}">${role.name}</label>
				</div>
			</c:forEach>
    	</div>
  	</div>
  	<div class="form-group">
  		<div class="col-sm-4 col-sm-offset-2">
    		<button type="submit" class="btn btn-primary"><m:message message="user.create.page.formbtn"/></button>
    	</div>
    </div>
</form>