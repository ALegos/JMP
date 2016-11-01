<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lectures</title>

<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/css/general.css">

<script src="resources/js/jquery-3.1.1.min.js"></script>
<script src="resources/js/lecture.js"></script>
<script src="resources/js/bootstrap.js"></script>

</head>
<body>
	<div class="row">
		<div
			class="col-md-8 .col-sm-6 .col-xs-12 col-md-offset-2 .col-xs-offset-3 .col-sm-offset-2">
			<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#"> JMP Portal </a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="persons">Persons</a></li>
						<li><a href="programs">Programs</a></li>
						<li><a href="groups">Groups</a></li>
						<li class="active"><a href="lectures">Lectures<span
								class="sr-only">(current)</span></a></li>
					</ul>
					<div class="nav navbar-nav">
						<a href="lecture/create" class="btn btn-primary navbar-btn">Create
							Lecture</a>
					</div>
					<div class="navbar-right margin-right-reset">
						<button type="button" class="btn btn-default navbar-btn ">Sign
							in</button>
					</div>
				</div>
			</div>
			</nav>
			<h1 class="page-name-header">All Lectures</h1>

			<table class="table table-striped">
				<thead>
					<tr>
						<th>Domain Area</th>
						<th>Topic</th>
						<th>Lecturer</th>
						<th>Duration</th>
						<th>Birth date</th>
						<th>Planned Start</th>
						<th>Planned End</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>

				<c:forEach var="lecture" items="${lectures.elements}">
					<tr>
						<td>${lecture.domainArea}</td>
						<td>${lecture.topic}</td>
						<td>${lecture.lectorUid}</td>
						<td><fmt:formatDate pattern="${durationFormatPattern}"
								value="${lecture.duration}" /></td>
						<td><fmt:formatDate pattern="${dateTimeFormatPattern}"
								value="${lecture.plannedStart}" /></td>
						<td><fmt:formatDate pattern="${dateTimeFormatPattern}"
								value="${lecture.plannedEnd}" /></td>
						<td>${lecture.status}</td>
						<td><a href="lecture/${lecture.uid}" class="btn btn-info">Update</a>
							<button class="btn btn-danger"
								onclick="deletePerson(this,'${person.uid}');">Delete</button></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>





</body>
</html>
