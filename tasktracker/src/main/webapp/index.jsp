<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="/WEB-INF/tlds/message.tld"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.css" rel="stylesheet">
  </head>
<body>
    <div class="container">
		<div class="row">
			<div class="col-xs-6 col-sm-6 col-md-6 col-xs-offset-2 col-sm-offset-2 col-md-offset-2">
				<div class="page-header">
  					<h1>
  						Task Tracker
  						<span class="pull-right">
  							<h4>
  								<a href="${pageContext.request.contextPath}/messages/change?lang=RU">ru</a> | <a href="${pageContext.request.contextPath}/messages/change?lang=EN">en</a>
  							</h4>
  						</span>
  					</h1>    				
        		</div>
				<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/login/auth" method="POST">
        			<h3 class="form-signin-heading"><m:message message="auth.header"/></h3>		
        			
        			<c:if test="${not empty bad_auth}">
        				<div class="alert alert-danger" role="alert">
        					<m:message message="bad.auth"/>
        				</div>
        			</c:if>
        			
        			<div class="form-group">
        				<label class="col-xs-2 col-sm-2 col-md-2 control-label" for="login"><m:message message="login"/></label>
        				<div class="col-xs-6 col-sm-6 col-md-6">
        					<input type="text" class="form-control" id="login" name="login" placeholder="Login" required autofocus>
        				</div>
        			</div>
        			<div class="form-group">
        				<label class="col-xs-2 col-sm-2 col-md-2 control-label"><m:message message="password"/></label>
        				<div class="col-xs-6 col-sm-6 col-md-6">
        					<input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
        				</div>
        			</div>
        			<div class="form-group">
        				<div class="col-xs-6 col-sm-6 col-md-6">
        	    			<button class="btn btn-lg btn-primary btn-block" type="submit"><m:message message="auth.submit"/></button>
        	    		</div>
        	    	</div>
      			</form>
      		</div>
		</div>
    </div>
  </body>
</html>
