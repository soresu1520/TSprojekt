<%--
  Created by IntelliJ IDEA.
  User: Roksana
  Date: 2021-05-05
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
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
                <a class="nav-link" href="login.html">Wyloguj się</a>
            </li>
            <li  style="float:right">
                <a class="nav-link" href="takeVacation.jsp">Dodaj urlop</a>
            </li>

        </ul>
    </div>
</nav>

<div style="display: inline-block">
    <br/>
    <form action="LoginServlet" method="get">

        <h2>Zmień dane</h2>
        <input type="hidden" name="command" value="UPDATE"/>
        <input type="hidden" name="id" value="${WORKLEAVE.id}"/>


        <div>
            <label for="start">Data początkowa</label>
            <input type="date" id="start" name="start"
                   value="today"
                   min="today" max="2021-12-31"
                   required >
        </div>

        <fieldset class="form-group">
            <label for="time">Ilość dni</label>
            <input type="range" min="1" max="26" value="10" name="time" class="custom-range" id="time" oninput="this.nextElementSibling.value = this.value"
                   required>
            <output>10</output>
        </fieldset>

        <div class="form-group">
            <label  for="metric" >Rodzaj</label>
            <select class="form-control" id="metric" name="metric" type="text" >
                <option>Płatny</option>
                <option>Niepłatny</option>
                <option>Zdrowotny</option>
            </select>
        </div>

        <c:if test="${not empty msg}">
            <p class="text-center" style="font-family: Arial">${msg}</p>
        </c:if>


        <button type="submit" class="btn btn-primary" style="margin: 10px">Zmień</button>
    </form>
</div>


<div class="card border-primary mb-3" style="width: 400px;  display: inline-block; vertical-align: top; margin: 20px; margin-left: 240px">
    <div class="card-header">Dane urlopu</div>
    <div class="card-body">
        <p class="card-text">Początek: ${WORKLEAVE.startDate}</p>
        <p class="card-text">Koniec: ${WORKLEAVE.endDate}</p>
        <p class="card-text">Czas trwania: ${WORKLEAVE.days}</p>
    </div>
</div>




</body>
</html>
