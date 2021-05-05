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
    <title>Your view</title>
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
                <a class="nav-link" href="bossView.jsp">Twoje konto</a>
            </li>
            <li  style="float:right">
                <a class="nav-link" href="index.html">Wyloguj się</a>
            </li>

        </ul>
    </div>
</nav>

<h2 style="margin: 20px">Urlopy do akceptacji</h2>

<div style="margin: 20px">
    <table class="table table-hover" >
        <thead>
        <tr  class="table-primary">
            <th class="col">Imię i nazwisko</th>
            <th class="col">Dział</th>
            <th class="col">Data początkowa</th>
            <th class="col">Data końcowa</th>
            <th class="col">Dni</th>
            <th class="col">Status</th>
            <th class="col">Dostępne dni</th>
            <th class="col"></th>
            <th class="col"></th>
        </tr>
        </thead>


        <tbody>
        <td>text</td>
        <td>text</td>
        <td>text</td>
        <td>text</td>
        <td>text</td>
        <td>text</td>
        <td>text</td>
        <td><a href="${updateLink}">
            <button type="button" class="btn btn-success">Zatwierdź</button>
        </a></td>
        <td><a href="${updateLink}">
            <button type="button" class="btn btn-danger">Odrzuć</button>
        </a></td>

        </tbody>

    </table>
</div>

<h2 style="margin: 20px">Zatwierdzone urlopu</h2>

<div style="margin: 20px">
    <table class="table table-hover" >
        <thead>
        <tr  class="table-primary">
            <th class="col">Imię i nazwisko</th>
            <th class="col">Dział</th>
            <th class="col">Data początkowa</th>
            <th class="col">Data końcowa</th>
            <th class="col">Dni</th>
            <th class="col">Dostępne dni</th>
            <th class="col">Status</th>


        </tr>
        </thead>


        <tbody>
        <td>text</td>
        <td>text</td>
        <td>text</td>
        <td>text</td>
        <td>text</td>
        <td>text</td>
        <td>text</td>
        </tbody>

    </table>
</div>





</body>
</html>
