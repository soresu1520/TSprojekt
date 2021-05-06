<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Your Account</title>
    <link rel="stylesheet" href="bootstrap.css">
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

<div style="margin: 20px">
    <table class="table table-hover" >
        <thead>
        <tr  class="table-primary">
            <th class="col">Data początkowa</th>
            <th class="col" >Data końcowa</th>
            <th class="col">Ilość wykorzystanych dni</th>
            <th class="col">Status</th>
            <th class="col"></th>
            <th class="col"></th>
        </tr>
        </thead>

        <tbody>


        <td>text</td>
        <td>text</td>
        <td>text</td>
        <td>text</td>
        <td><a href="${updateLink}">
            <button type="button" class="btn btn-success">Modyfikuj</button>
        </a></td>
        <td><a href="${updateLink}">
            <button type="button" class="btn btn-success">Modyfikuj</button>
        </a></td>
        </tbody>

    </table>
</div>

<div style="width: 94%">
    <button  class="btn btn-primary btn-lg btn-block" type="button" style="margin: 40px" onclick="location.href='takeVacation.jsp'"> Zgłoś urlop</button>
</div>




</html>
