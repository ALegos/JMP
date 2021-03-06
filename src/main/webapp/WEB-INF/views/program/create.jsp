
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="cust" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Mentorship Program</title>

<link rel="stylesheet" type="text/css"
	href="../resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="../resources/css/general.css">

<script src="../resources/js/jquery-3.1.1.min.js"></script>
<script src="../resources/js/bootstrap.js"></script>

</head>
<body>
	<div class="row">
		<div
			class="col-md-8 .col-sm-6 .col-xs-12 col-md-offset-2 .col-xs-offset-3 .col-sm-offset-2">

			<cust:header active="program" prefix="../"
				buttonName="Create Program" />

			<h1 class="page-name-header">Create Mentorship Program</h1>

			<form:form method="POST" action="create"
				modelAttribute="mentorshipProgramDTO" class="form-horizontal">
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
					<form:label path="officeLocation" class="col-sm-2 control-label">Office Location</form:label>
					<div class="col-sm-5">
						<form:input path="officeLocation" class="form-control"
							placeholder="Office Location" />
					</div>
					<div class="col-sm-5">
						<form:errors path="officeLocation" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="startDate" class="col-sm-2 control-label">Start Date</form:label>
					<div class="col-sm-5">
						<form:input path="startDate" class="form-control"
							placeholder="Start Date" />
					</div>
					<div class="col-sm-5">
						<form:errors path="startDate" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="endDate" class="col-sm-2 control-label">End Date</form:label>
					<div class="col-sm-5">
						<form:input path="endDate" class="form-control"
							placeholder="End Date" />
					</div>
					<div class="col-sm-5">
						<form:errors path="endDate" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-5">
						<div class="navbar-right margin-right-reset">
							<button type="submit" class="btn btn-primary text-rigth">Create
								Mentorship Program</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>



</body>
</html>