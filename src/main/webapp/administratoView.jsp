<%--
  Created by IntelliJ IDEA.
  User: Piero
  Date: 04.05.2021
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="bootstrap.css" rel="stylesheet">
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
                <a class="nav-link" href="administratoView.jsp">Twoje konto</a>
            </li>
            <li>
                <a class="nav-link" href="index.html">Wyloguj się</a>
            </li>


        </ul>

    </div>
</nav>


<h2 style="margin: 20px">Modyfikowane urlopy</h2>

<div style="margin: 20px">
    <table class="table table-hover" >
        <thead>
        <tr  class="table-primary">
            <th class="col">ID</th>
            <th class="col">Data początkowa</th>
            <th class="col">Data końcowa</th>
            <th class="col">Dni</th>
            <th class="col">Dostępne dni</th>
            <th class="col">Status</th>
            <th class="col">ID pracownika</th>
            <th class="col"></th>
            <th class="col"></th>

        </tr>
        </thead>
<c:forEach var="tmpWorkLeave" items="${TO_MODIFY}">

    <%-- definiowanie linkow--%>
    <c:url var="updateLink" value="AdminServlet">
        <c:param name="command" value="UPDATE"></c:param>
        <c:param name="id" value="${tmpWorkLeave.id}"></c:param>
    </c:url>

    <c:url var="deleteLink" value="AdminServlet">
        <c:param name="command" value="CANCEL"></c:param>
        <c:param name="id" value="${tmpWorkLeave.id}"></c:param>
    </c:url>

        <tbody>
        <td>${tmpWorkLeave.id}</td>
        <td>${tmpWorkLeave.startDate}</td>
        <td>${tmpWorkLeave.endDate}</td>
        <td>${tmpWorkLeave.days}</td>
        <td>${tmpWorkLeave.leaveType}</td>
        <td>${tmpWorkLeave.leaveStatus}</td>
        <td>${tmpWorkLeave.employeeId}</td>
        <td><a href="${updateLink}">
            <button type="button" class="btn btn-success">Zatwierdź</button>
        </a>
        <td><a href="${updateLink}">
            <button type="button" class="btn btn-danger">Odrzuć</button>
        </a></td>
        </tbody>
    </c:forEach>
    </table>
</div>

<h2 style="margin: 20px">Urlopy do usunięcia</h2>

<div style="margin: 20px">
    <table class="table table-hover" >
        <thead>
        <tr  class="table-primary">
            <th class="col">ID</th>
            <th class="col">Data początkowa</th>
            <th class="col">Data końcowa</th>
            <th class="col">Dni</th>
            <th class="col">Dostępne dni</th>
            <th class="col">Status</th>
            <th class="col">ID pracownika</th>
            <th class="col"></th>
            <th class="col"></th>

        </tr>
        </thead>

        <c:forEach var="tmpWorkLeave" items="${TO_DELETE}">

            <%-- definiowanie linkow--%>
            <c:url var="updateLink" value="AdminServlet">
                <c:param name="command" value="CANCEL"></c:param>
                <c:param name="id" value="${tmpWorkLeave.id}"></c:param>
            </c:url>

            <c:url var="deleteLink" value="AdminServlet">
                <c:param name="command" value="DELETE"></c:param>
                <c:param name="id" value="${tmpWorkLeave.id}"></c:param>
            </c:url>

            <tbody>
            <td>${tmpWorkLeave.id}</td>
            <td>${tmpWorkLeave.startDate}</td>
            <td>${tmpWorkLeave.endDate}</td>
            <td>${tmpWorkLeave.days}</td>
            <td>${tmpWorkLeave.leaveType}</td>
            <td>${tmpWorkLeave.leaveStatus}</td>
            <td>${tmpWorkLeave.employeeId}</td>
            <td><a href="${deleteLink}">
                <button type="button" class="btn btn-success">Zatwierdź</button>
            </a>
            <td><a href="${updateLink}">
                <button type="button" class="btn btn-danger">Odrzuć</button>
            </a></td>
            </tbody>
        </c:forEach>

    </table>
</div>




</body>
</html>
