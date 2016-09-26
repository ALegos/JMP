<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="../resources/css/person.css">


<script src="../resources/js/person.js"></script>

</head>
<body>
	
	<h1>All Users</h1>
	<br/>
	<br/>
	<a href="create">CREATE NEW USER</a>
	<br/>
	<br/>	
	<table class="table table-striped">
		<thead>
			<tr>
				<th>#ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Level</th>
				<th>Primary skill</th>
				<th>Birth date</th>
			</tr>
		</thead>

		<c:forEach var="person" items="${persons}">
		    <tr>
			<td>
				${person.uid}
			</td>
			<td>${person.name}</td>
			<td>${person.email}</td>
			<td>${person.level}</td>
			<td>${person.primarySkill}</td>
			<td>${person.birthDate}</td>
			<td><button onclick="deletePerson(this,'${person.uid}');">DELETE</button></td>
		    </tr>
		</c:forEach>
	</table>
</body>
</html>
