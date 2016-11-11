<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="cust" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="resources/css/person.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/css/general.css">

<script src="resources/js/jquery-3.1.1.min.js"></script>
<script src="resources/js/program.js"></script>
<script src="resources/js/bootstrap.js"></script>

</head>
<body>
	<div class="row">
		<div
			class="col-md-8 .col-sm-6 .col-xs-12 col-md-offset-2 .col-xs-offset-3 .col-sm-offset-2">

			<cust:header active="program" buttonName="Create Program" />

			<h1 class="page-name-header">All Programs</h1>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Start date</th>
						<th>End date</th>
						<th>Location</th>
						<th>Created</th>
						<th>Updated</th>
						<th>Action</th>
					</tr>
				</thead>

				<c:forEach var="program" items="${programs.elements}">
					<tr>
						<td>${program.name}</td>
						<td><fmt:formatDate pattern="${dateFormatPattern}"
								value="${program.startDate}" /></td>
						<td><fmt:formatDate pattern="${dateFormatPattern}"
								value="${program.endDate}" /></td>
						<td>${program.officeLocation}</td>
						<td><fmt:formatDate pattern="${dateTimeFormatPattern}"
								value="${program.metaDataCreationDate}" /></td>
						<td><fmt:formatDate pattern="${dateTimeFormatPattern}"
								value="${program.metaDataModificationDate}" /></td>
						<td><a href="program/${program.uid}" class="btn btn-info">Update</a>
							<button class="btn btn-danger"
								onclick="deleteProgram(this,'${program.uid}');">Delete</button></td>
					</tr>
				</c:forEach>
			</table>


		</div>
	</div>

</body>
</html>
