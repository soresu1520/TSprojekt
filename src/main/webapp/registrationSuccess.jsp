<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>YEY</title>
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
                <a class="nav-link" href="index.html">Strona główna</a>
            </li>

            <li  style="float:right">
                <a class="nav-link" href="login.html">Zaloguj się</a>
            </li>
            <li  style="float:right">
                <a class="nav-link" href="makeAccount.html">Utwórz konto</a>
            </li>
            <li style="align-self: end">
                <a class="nav-link" href="adminLogin.html">Strona admina</a>
            </li>

        </ul>
    </div>
</nav>

<div class="container">
    <img src="Images/happyEgg.jpg" alt="eggHappy" style="width:100%;">
    <div class="bottom-left" >Rejestracja udana :)</div>

</div>



</body>
</html>
