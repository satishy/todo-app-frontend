<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Spring MVC TODO Application</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/" var="urlNewTask" />
<spring:url value="/tasks" var="urlTaskList" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}"><b>TODO FrontEnd Application</b></a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlNewTask}">New Task</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlTaskList}">Maintain Tasks</a></li>
			</ul>
		</div>
	</div>
</nav>