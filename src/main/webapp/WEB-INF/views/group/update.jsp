
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
<title>Update Group</title>

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

			<cust:header active="group" prefix="../" buttonName="Create Group" />

			<h1 class="page-name-header">Update Group</h1>

			<form:form method="POST" action="${groupDTO.uid}"
				modelAttribute="groupDTO" class="form-horizontal">
				<div class="form-group">
					<form:label path="menteeUid" class="col-sm-2 control-label">Mentee</form:label>
					<div class="col-sm-5">
						<form:select path="menteeUid" class="form-control">
							<form:option value="" label="---Please Select---" />
							<form:options items="${mentees}" itemLabel="name" itemValue="uid" />
						</form:select>
					</div>
					<div class="col-sm-5">
						<form:errors path="menteeUid" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="mentorUid" class="col-sm-2 control-label">Mentor</form:label>
					<div class="col-sm-5">
						<form:select path="mentorUid" class="form-control">
							<form:option value="" label="---Please Select---" />
							<form:options items="${mentors}" itemLabel="name" itemValue="uid" />
						</form:select>
					</div>
					<div class="col-sm-5">
						<form:errors path="mentorUid" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="mentorshipProgramUid"
						class="col-sm-2 control-label">Mentorship Program</form:label>
					<div class="col-sm-5">
						<form:select path="mentorshipProgramUid" class="form-control">
							<form:option value="" label="---Please Select---" />
							<form:options items="${programs}" itemLabel="name"
								itemValue="uid" />
						</form:select>
					</div>
					<div class="col-sm-5">
						<form:errors path="mentorshipProgramUid" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="plannedStart" class="col-sm-2 control-label">Planned Start</form:label>
					<div class="col-sm-5">
						<form:input path="plannedStart" class="form-control"
							placeholder="Planned Start" />
					</div>
					<div class="col-sm-5">
						<form:errors path="plannedStart" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="plannedEnd" class="col-sm-2 control-label">Planned End</form:label>
					<div class="col-sm-5">
						<form:input path="plannedEnd" class="form-control"
							placeholder="Planned End" />
					</div>
					<div class="col-sm-5">
						<form:errors path="plannedEnd" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="actualStart" class="col-sm-2 control-label">Actual Start</form:label>
					<div class="col-sm-5">
						<form:input path="actualStart" class="form-control"
							placeholder="Actual Start" />
					</div>
					<div class="col-sm-5">
						<form:errors path="actualStart" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="actualEnd" class="col-sm-2 control-label">Actual End</form:label>
					<div class="col-sm-5">
						<form:input path="actualEnd" class="form-control"
							placeholder="Actual End" />
					</div>
					<div class="col-sm-5">
						<form:errors path="actualEnd" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="status" class="col-sm-2 control-label">Status</form:label>
					<div class="col-sm-5">
						<form:input path="status" class="form-control"
							placeholder="Status" />
					</div>
					<div class="col-sm-5">
						<form:errors path="status" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-5">
						<div class="navbar-right margin-right-reset">
							<button type="submit" class="btn btn-primary text-rigth">Update
								Group</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>



</body>
</html>