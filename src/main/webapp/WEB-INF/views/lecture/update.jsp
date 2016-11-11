
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
<title>Update Lecture</title>

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

			<cust:header active="lecture" prefix="../"
				buttonName="Create Lecture" />

			<h1 class="page-name-header">Update Lecture</h1>

			<form:form method="POST" action="${lectureDTO.uid}"
				modelAttribute="lectureDTO" class="form-horizontal">
				<div class="form-group">
					<form:label path="domainArea" class="col-sm-2 control-label">Domain Area</form:label>
					<div class="col-sm-5">
						<form:input path="domainArea" class="form-control"
							placeholder="Domain Area" />
					</div>
					<div class="col-sm-5">
						<form:errors path="domainArea" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="topic" class="col-sm-2 control-label">Topic</form:label>
					<div class="col-sm-5">
						<form:input path="topic" class="form-control" placeholder="Topic" />
					</div>
					<div class="col-sm-5">
						<form:errors path="topic" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="lectorUid" class="col-sm-2 control-label">Lecturer</form:label>
					<div class="col-sm-5">
						<form:select path="lectorUid" class="form-control">
							<form:options items="${lecturers}" itemLabel="name"
								itemValue="uid" />
						</form:select>
					</div>
					<div class="col-sm-5">
						<form:errors path="lectorUid" class="bg-danger" />
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
					<form:label path="duration" class="col-sm-2 control-label">Duration</form:label>
					<div class="col-sm-5">
						<form:input path="duration" class="form-control"
							placeholder="Duration" />
					</div>
					<div class="col-sm-5">
						<form:errors path="duration" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="status" class="col-sm-2 control-label">Status</form:label>
					<div class="col-sm-5">
						<form:select path="status" class="form-control"
							items="${statuses}" />
					</div>
					<div class="col-sm-5">
						<form:errors path="status" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="attendeesUids" class="col-sm-2 control-label">Lecturer</form:label>
					<div class="col-sm-5">
						<form:select multiple="true" path="attendeesUids"
							class="form-control">
							<form:options items="${attendees}" itemLabel="name"
								itemValue="uid" />
						</form:select>
					</div>
					<div class="col-sm-5">
						<form:errors path="attendeesUids" class="bg-danger" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-5">
						<div class="navbar-right margin-right-reset">
							<button type="submit" class="btn btn-primary text-rigth">Update
								Lecture</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>



</body>
</html>