
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

			<cust:header active="person" prefix="../" buttonName="Create Person" />

			<h1 class="page-name-header">Update Person</h1>

			<form:form method="POST" action="${personDTO.uid}"
				modelAttribute="personDTO" class="form-horizontal">
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
					<form:label path="isManager" class="col-sm-2 control-label">Is Manager</form:label>
					<div class="col-sm-5">
						<form:checkbox path="isManager" />
					</div>
					<div class="col-sm-5">
						<form:errors path="isManager" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="excluded" class="col-sm-2 control-label">Excluded</form:label>
					<div class="col-sm-5">
						<form:checkbox path="excluded" />
					</div>
					<div class="col-sm-5">
						<form:errors path="excluded" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="managerDTO.uid" class="col-sm-2 control-label">Manager</form:label>
					<div class="col-sm-5">
						<form:select path="managerDTO.uid" class="form-control">
							<form:option value="" label="---Please Select---" />
							<form:options items="${managers}" itemLabel="name"
								itemValue="uid" />
						</form:select>
					</div>
					<div class="col-	sm-5">
						<form:errors path="managerDTO.uid" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-5">
						<button class="btn grey" type="button" data-toggle="collapse"
							data-target="#collapseExample" aria-expanded="false"
							aria-controls="collapseExample">Mentorship Program
							Assignment</button>
					</div>
				</div>

				<div class="collapse" id="collapseExample">
					<form:hidden path="assignmentDTO.uid" />
					<div class="form-group">
						<form:label path="assignmentDTO.mentorshipProgramUid"
							class="col-sm-2 control-label">Mentorship Program</form:label>
						<div class="col-sm-5">
							<form:select path="assignmentDTO.mentorshipProgramUid"
								class="form-control">
								<form:option value="" label="---Please Select---" />
								<form:options items="${programs}" itemLabel="name"
									itemValue="uid" />
							</form:select>
						</div>
						<div class="col-	sm-5">
							<form:errors path="assignmentDTO.mentorshipProgramUid"
								class="bg-danger" />
						</div>
					</div>
					<div class="form-group">
						<form:label path="assignmentDTO.role"
							class="col-sm-2 control-label">Role</form:label>
						<div class="col-sm-5">
							<form:select path="assignmentDTO.role" class="form-control"
								items="${roles}" />
						</div>
						<div class="col-sm-5">
							<form:errors path="assignmentDTO.role" class="bg-danger" />
						</div>
					</div>
					<div class="form-group">
						<form:label path="assignmentDTO.status"
							class="col-sm-2 control-label">Status</form:label>
						<div class="col-sm-5">
							<form:select path="assignmentDTO.status" class="form-control"
								items="${statuses}" />
						</div>
						<div class="col-sm-5">
							<form:errors path="assignmentDTO.status" class="bg-danger" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-5">
						<div class="navbar-right margin-right-reset">
							<button type="submit" class="btn btn-primary text-rigth">Update
								Person</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>



</body>
</html>