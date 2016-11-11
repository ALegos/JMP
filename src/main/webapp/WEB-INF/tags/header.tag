<%@ attribute name="active" required="true"%>
<%@ attribute name="prefix" required="false"%>
<%@ attribute name="buttonName" required="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"> JMP Portal </a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<c:choose>
					<c:when test="${active eq 'person'}">
						<li class="active"><a href="${prefix}persons">Persons<span
								class="sr-only">(current)</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${prefix}persons">Persons</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${active eq 'program'}">
						<li class="active"><a href="${prefix}programs">Programs<span
								class="sr-only">(current)</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${prefix}programs">Programs</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${active eq 'group'}">
						<li class="active"><a href="${prefix}groups">Groups<span
								class="sr-only">(current)</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${prefix}groups">Groups</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${active eq 'lecture'}">
						<li class="active"><a href="${prefix}lectures">Lectures<span
								class="sr-only">(current)</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${prefix}lectures">Lectures</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<c:if test="${!empty buttonName}">
				<div class="nav navbar-nav">
					<a href="${prefix}${active}/create" class="btn btn-primary navbar-btn"><c:out
							value="${buttonName}"/></a>
				</div>
			</c:if>
			<div class="navbar-right margin-right-reset">
				<button type="button" class="btn btn-default navbar-btn ">Sign
					in</button>
			</div>
		</div>
	</div>
</nav>