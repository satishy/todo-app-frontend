<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>Task Details</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${task.id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Title</label>
		<div class="col-sm-10">${task.title}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Description</label>
		<div class="col-sm-10">${task.description}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Task Status</label>
		<div class="col-sm-10">${task.taskStatus}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Priority</label>
		<div class="col-sm-10">${task.priority}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Created Date</label>
		<div class="col-sm-10">${task.createdDate}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Updated Date</label>
		<div class="col-sm-10">${task.updatedDate}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Assigned User</label>
		<div class="col-sm-10">${task.assignedUser}</div>
	</div>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>