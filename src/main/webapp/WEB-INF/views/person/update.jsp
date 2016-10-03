
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Person</title>

<link rel="stylesheet" type="text/css"
	href="../resources/css/person.css">
<link rel="stylesheet" type="text/css"
	href="../resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="../resources/css/general.css">

<script src="../resources/js/jquery-3.1.1.min.js"></script>
<script src="../resources/js/person.js"></script>
<script src="../resources/js/bootstrap.js"></script>

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
						<li class="active"><a href="../persons">Persons<span
								class="sr-only">(current)</span></a></li>
						<li><a href="../programs">Programs</a></li>
					</ul>
					<div class="navbar-right margin-right-reset">
						<button type="button" class="btn btn-default navbar-btn ">Sign
							in</button>
					</div>
				</div>
			</div>
			</nav>
			<h1 class="page-name-header">Update Person</h1>

			<form:form method="POST" action="${person.uid}" modelAttribute="person"
				class="form-horizontal">
				<div class="form-group">
					<form:label path="name" class="col-sm-2 control-label">Name</form:label>
					<div class="col-sm-5">
						<form:input path="name" class="form-control" placeholder="Name" />
					</div>
					<div class="col-sm-5">
						<form:errors path="name" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="email" class="col-sm-2 control-label">Email</form:label>
					<div class="col-sm-5">
						<form:input path="email" class="form-control" placeholder="Email" />
					</div>
					<div class="col-sm-5">
						<form:errors path="email" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="level" class="col-sm-2 control-label">Level</form:label>
					<div class="col-sm-5">
						<form:input path="level" class="form-control" placeholder="Level" />
					</div>
					<div class="col-sm-5">
						<form:errors path="level" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="primarySkill" class="col-sm-2 control-label">Primary Skill</form:label>
					<div class="col-sm-5">
						<form:input path="primarySkill" class="form-control"
							placeholder="Primary Skill" />
					</div>
					<div class="col-sm-5">
						<form:errors path="primarySkill" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="birthDate" class="col-sm-2 control-label">Birth Date</form:label>
					<div class="col-sm-5">
						<form:input path="birthDate" class="form-control"
							placeholder="Birth Date" />
					</div>
					<div class="col-sm-5">
						<form:errors path="birthDate" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-5">
						<div class="navbar-right margin-right-reset">
							<button type="submit" class="btn btn-primary text-rigth">Update Person</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>



</body>
</html>