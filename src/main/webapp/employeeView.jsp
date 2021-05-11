<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Your Account</title>
    <link rel="stylesheet" href="bootstrap.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="#">Firma</a>
    <button aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler" data-target="#navbarColor01" data-toggle="collapse" type="button">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor01">

        <ul class="navbar-nav mr-auto">

            <li>
                <a class="nav-link" href="employeeView.jsp">Twoje konto</a>
            </li>

            <li  style="float:right">
                <a class="nav-link" href="index.html">Wyloguj się</a>
            </li>

            <li  style="float:right">
                <a class="nav-link" href="takeVacation.jsp">Zgłoś urlop</a>
            </li>


        </ul>
    </div>
</nav>
</body>

<h2 style="margin: 20px">Zaplanowane urlopy</h2>


<c:forEach var="tmpEmployee" items="${USER}">
<h5 style="margin-left: 20px">Przysługuje ci: ${tmpEmployee.leaveDays} dni urlopu</h5>
<h5 style="margin-left: 20px">Zostało ci: ${tmpEmployee.availableDays} dni urlopu</h5>
</c:forEach>


<div style="margin: 20px">
    <table class="table table-hover" >
        <thead>
        <tr  class="table-primary">

            <th class="col">Id</th>
            <th class="col">Data początkowa</th>
            <th class="col">Data końcowa</th>
            <th class="col">Ilość wykorzystanych dni</th>
            <th class="col">Typ</th>
            <th class="col">Status</th>
            <th class="col"></th>
            <th class="col"></th>
        </tr>
        </thead>
        <c:forEach var="tmpWorkLeave" items="${WORK_LEAVE}">

            <c:url var="updateLink" value="LoginServlet">

                <c:param name="command" value="UPDATE_EMPLOYEE"></c:param>
                <c:param name="leaveID" value="${tmpWorkLeave.id}"></c:param>
            </c:url>

            <c:url var="deleteLink" value="LoginServlet">
                <c:param name="command" value="DELETE_EMPLOYEE"></c:param>
                <c:param name="leaveID" value="${tmpWorkLeave.id}"></c:param>
            </c:url>

        <tbody>

        <td>${tmpWorkLeave.id}</td>
        <td>${tmpWorkLeave.startDate}</td>
        <td>${tmpWorkLeave.endDate}</td>
        <td>${tmpWorkLeave.days}</td>
        <td>${tmpWorkLeave.leaveType}</td>
        <td>${tmpWorkLeave.leaveStatus}</td>


        <td><a href="${updateLink}">
            <button type="button" class="btn btn-success">Modyfikuj</button>
        </a></td>
        <td><a href="${deleteLink}">
            <button type="button" class="btn btn-danger" >Usuń</button></a></td>

        </tbody>
    </c:forEach>
    </table>
</div>

<div style="width: 94%">
    <button  class="btn btn-primary btn-lg btn-block" type="button" style="margin: 40px" onclick="location.href='takeVacation.jsp'"> Zgłoś urlop</button>
</div>

<h2 style="margin: 20px">Odrzucone urlopy</h2>

<div style="margin: 20px">
    <table class="table table-hover" >
        <thead>
        <tr  class="table-primary">

            <th class="col">Id</th>
            <th class="col">Data początkowa</th>
            <th class="col">Data końcowa</th>
            <th class="col">Dni</th>
            <th class="col">Typ</th>


        </tr>
        </thead>
        <c:forEach var="tmpLeavesArchive" items="${LEAVE_ARCHIVE}">
        <tbody>

            <td>${tmpLeavesArchive.id}</td>
            <td>${tmpLeavesArchive.startDate}</td>
            <td>${tmpLeavesArchive.endDate}</td>
            <td>${tmpLeavesArchive.days}</td>
            <td>${tmpLeavesArchive.leaveType}</td>


        </tbody>
        </c:forEach>

    </table>
</div>


</html>
