<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>

<jsp:include page="../fragments/header.jsp" />

<div class="container">

        <center> <h1>TODO FORM</h1></center>
        <spring:url value="/task" var="taskActionUrl" />

        <form:form class="form-horizontal" method="post" modelAttribute="taskForm" action="${taskActionUrl}">

            <form:hidden path="id" />

        <spring:bind path="title">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">Title</label>
            <div class="col-sm-10">
                <form:input path="title" type="text" class="form-control " id="title" placeholder="Title" />
                <form:errors path="title" class="control-label" />
            </div>
        </div>
        </spring:bind>

        <spring:bind path="description">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">Description</label>
            <div class="col-sm-10">
                <form:input path="description" type="text" class="form-control " id="description" placeholder="Description" />
                <form:errors path="description" class="control-label" />
            </div>
        </div>
        </spring:bind>

       <spring:bind path="taskStatus">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Task Status</label>
                    <div class="col-sm-5">
                        <form:select path="taskStatus" class="form-control" id="taskStatus">
                            <form:option value="NONE" label="--- Select ---" />
                            <form:options items="${taskStatusList}" />
                        </form:select>
                        <form:errors path="taskStatus" class="control-label" />
                    </div>
                    <div class="col-sm-5"></div>
                </div>
       </spring:bind>

        <spring:bind path="priority">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Priority</label>
                    <div class="col-sm-5">
                        <form:select path="priority" class="form-control" id="priority">
                            <form:option value="NONE" label="--- Select ---" />
                            <form:options items="${priorityList}" />
                        </form:select>
                        <form:errors path="priority" class="control-label" />
                    </div>
                    <div class="col-sm-5"></div>
                </div>
        </spring:bind>

            <spring:bind path="assignedUser">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Assigned User</label>
                    <div class="col-sm-10">
                        <form:input path="assignedUser" type="text" class="form-control " id="assignedUser" placeholder="assignedUser" />
                        <form:errors path="assignedUser" class="control-label" />
                    </div>
                </div>
            </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${taskForm['new']}">
                        <button type="submit" class="btn-lg btn-primary pull-right">Add</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn-lg btn-primary pull-right">Update</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

    </form:form>
    </div>

<jsp:include page="../fragments/footer.jsp" />
    </body>
</html>