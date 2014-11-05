<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="/WEB-INF/tlds/message.tld"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-combined.min.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-tree.js"></script>

<div class="page-header">
	<h3><m:message message="user.index.page.header"/>
		<div class="pull-right">
			<a href="${pageContext.request.contextPath}/users/create" role="button" class="btn btn-default">
				<img src="${pageContext.request.contextPath}/resources/images/user.png"/> <m:message message="user.create.user.btn"/>
			</a>
  			<button type="button" class="btn btn-default">
  				<img src="${pageContext.request.contextPath}/resources/images/delete.png"/> <m:message message="user.delete.user.btn"/>
  			</button>
  		</div>
	</h3>	
</div>

<c:forEach items="${usersList}" var="user">
	${user.login}<br>
</c:forEach>
<div class="row-fluid">
	<section id="demonstration" role="main" class="col-md-4">
		<div class="tree">
			<ul>
				<li>
					<span><i class="icon-folder-open"></i>Projects</span>
					<ul>
						<li>
						<span class="badge badge-success"><i class="icon-minus-sign"></i> Monday, January 7: 8.00 hours</span>
							<ul>
								<li><a href="">
									<span><i class="icon-time"></i>8.00</span> &ndash; Changed CSS to accomodate...</a>
								</li>
							</ul>
						</li>
						<li>
							<span class="badge badge-success"><i class="icon-minus-sign"></i> Tuesday, January 8: 8.00 hours</span>
							<ul>
								<li>
									<span><i class="icon-time"></i> 6.00</span> &ndash; <a href="">Altered code...</a>
								</li>
								<li>
									<span><i class="icon-time"></i> 2.00</span> &ndash; <a href="">Simplified our approach to...</a>
								</li>
							</ul>
						</li>
						<li>
							<span class="badge badge-warning"><i class="icon-minus-sign"></i> Wednesday, January 9: 6.00 hours</span>
							<ul>
								<li>
									<a href=""><span><i class="icon-time"></i>3.00</span> &ndash; Fixed bug caused by...</a>
								</li>
								<li>
									<a href=""><span><i class="icon-time"></i>3.00</span> &ndash; Comitting latest code to Git...</a>
								</li>
							</ul>
						</li>
						<li>
							<span class="badge badge-important"><i class="icon-minus-sign"></i> Wednesday, January 9: 4.00 hours</span>
							<ul>
								<li>
									<a href=""><span><i class="icon-time"></i>2.00</span> &ndash; Create component that...</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				<li>
					<span><i class="icon-folder-open"></i> 2013, Week 3</span>
					<ul>
						<li>
							<span class="badge badge-success"><i class="icon-minus-sign"></i> Monday, January 14: 8.00 hours</span>
							<ul>
								<li>
									<span><i class="icon-time"></i> 7.75</span> &ndash; <a href="">Writing documentation...</a>
								</li>
								<li>
									<span><i class="icon-time"></i> 0.25</span> &ndash; <a href="">Reverting code back to...</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				<li>
					<span><i class="icon-folder-open"></i> 2013, Week 3</span>
					<ul>
						<li>
							<span class="badge badge-success"><i class="icon-minus-sign"></i> Monday, January 14: 8.00 hours</span>
							<ul>
								<li>
									<span><i class="icon-time"></i> 7.75</span> &ndash; <a href="">Writing documentation...</a>
								</li>
								<li>
									<span><i class="icon-time"></i> 0.25</span> &ndash; <a href="">Reverting code back to...</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</section>
</div>