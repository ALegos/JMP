<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="cust" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Groups</title>

<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/css/general.css">

<script src="resources/js/jquery-3.1.1.min.js"></script>
<script src="resources/js/group.js"></script>
<script src="resources/js/bootstrap.js"></script>

</head>
<body>
	<div class="row">
		<div
			class="col-md-8 .col-sm-6 .col-xs-12 col-md-offset-2 .col-xs-offset-3 .col-sm-offset-2">
		
			<cust:header active="group" buttonName="Create Group" />

			<h1 class="page-name-header">All Groups</h1>

			<table class="table table-striped">
				<thead>
					<tr>
						<th>Mentor</th>
						<th>Mentee</th>
						<th>Planned Start</th>
						<th>Planned End</th>
						<th>Actual Start</th>
						<th>Actual Date</th>
						<th>Status</th>
					</tr>
				</thead>

				<c:forEach var="group" items="${groups.elements}">
					<tr>
						<td>${group.mentorUid}</td>
						<td>${group.menteeUid}</td>
						<td><fmt:formatDate pattern="${dateFormatPattern}"
								value="${group.plannedStart}" /></td>
						<td><fmt:formatDate pattern="${dateFormatPattern}"
								value="${group.plannedEnd}" /></td>
						<td><fmt:formatDate pattern="${dateFormatPattern}"
								value="${group.actualStart}" /></td>
						<td><fmt:formatDate pattern="${dateFormatPattern}"
								value="${group.actualEnd}" /></td>
						<td>${group.status}</td>
						<td><a href="group/${group.uid}" class="btn btn-info">Update</a>
							<button class="btn btn-danger"
								onclick="deletePerson(this,'${group.uid}');">Delete</button></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>





</body>
</html>
