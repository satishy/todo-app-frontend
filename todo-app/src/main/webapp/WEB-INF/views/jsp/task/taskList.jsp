<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>
	<div class="container">
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>All Tasks</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Title</th>
					<th>Description</th>
					<th>Task Status</th>
                    <th>Assigned User</th>
				</tr>
			</thead>

			<c:forEach var="task" items="${tasks}">
				<tr>
					<td>
						${task.id}
					</td>
					<td>${task.title}</td>
					<td>${task.description}</td>
					<td>${task.taskStatus}</td>
                    <td>${task.assignedUser}</td>
                        <spring:url value="/task/${task.id}" var="taskUrl" />
						<spring:url value="/task/${task.id}/update" var="updateUrl" />
						<spring:url value="/task/${task.id}/delete" var="deleteUrl" />
                    <td><button class="btn btn-info" onclick="location.href='${taskUrl}'">Query</button></td>
					<td><button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button></td>
                    <c:choose>
                    <c:when test="${task.taskStatus == 'COMPLETED'}">
					<td><button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td>
                    </c:when>
                     <c:otherwise>
                         <td><button class="btn btn-danger" disabled="true">Delete</button></td>
                     </c:otherwise>
                    </c:choose>
				</tr>
			</c:forEach>
		</table>

	</div>
</body>
<jsp:include page="../fragments/footer.jsp" />
</html>