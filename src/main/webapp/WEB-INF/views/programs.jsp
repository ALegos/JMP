<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="resources/css/person.css">
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/css/general.css">

<script src="resources/js/jquery-3.1.1.min.js"></script>
<script src="resources/js/program.js"></script>
<script src="resources/js/bootstrap.js"></script>

</head>
<body>
	<div class="row">
	  <div class="col-md-8 .col-sm-6 .col-xs-12 col-md-offset-2 .col-xs-offset-3 .col-sm-offset-2">
		  <nav class="navbar navbar-inverse">
			  <div class="container-fluid">
			    <div class="navbar-header">
			      <a class="navbar-brand" href="#">
			        JMP Portal
			      </a>
			    </div>
			    <div class="collapse navbar-collapse">
			      <ul class="nav navbar-nav">
			        <li><a href="persons">Persons</a></li>
			        <li class="active"><a href="programs">Programs<span class="sr-only">(current)</span></a></li>
			      </ul>
			      <div class="navbar-right margin-right-reset">
						  <button type="button" class="btn btn-default navbar-btn ">Sign in</button>
				    </div>
			    </div>  
			  </div>
	    </nav>
			<h1 class="page-name-header">All Programs</h1>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#ID</th>
						<th>Name</th>
						<th>Location</th>
						<th>Start date</th>
						<th>End date</th>
						<th>Action</th>
					</tr>
				</thead>
		
				<c:forEach var="program" items="${programs}">
				    <tr>
					<td>${program.uid}</td>
					<td>${program.name}</td>
					<td>${program.officeLocation}</td>
					<td><fmt:formatDate pattern="${dateFormatPattern}" value="${program.startDate}"/></td>
					<td><fmt:formatDate pattern="${dateFormatPattern}" value="${program.endDate}"/></td>
					<td><button class="btn btn-info" onclick="">Update</button>
					<button class="btn btn-danger" onclick="deleteProgram(this,'${program.uid}');">Delete</button></td>
				    </tr>
				</c:forEach>
			</table>
	  
	  
	  </div>
	</div>
	
</body>
</html>
