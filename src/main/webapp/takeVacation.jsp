<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reserve free time</title>
    <link href="bootstrap.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="#">Navbar</a>
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

<div style="margin: 20px">
    <h2 style="margin: 20px">Formularz zgłoszeniowy</h2>
    <form action="LoginServlet" method="get">
        <c:if test="${not empty msg}">
            <p class="text-center">${msg}</p>
        </c:if>
        <input type="hidden" name="command" value="ADD">

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


        <button type="submit" class="btn btn-primary">Potwierdź</button>


    </form>
</div>



</body>
</html>
