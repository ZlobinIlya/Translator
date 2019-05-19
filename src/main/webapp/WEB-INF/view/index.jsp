<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>

<h1>Hello!</h1><br />

<h2>Все ивенты: </h2><br />

<c:forEach var="event" items="${requestScope.events}">
    <ul>

        <li>IP   : <c:out value="${event.ip}"/></li>

        <li>Date : <c:out value="${event.date}"/></li>

        <li>Text 1: <c:out value="${event.firstText}"/></li>

        <li>Text 2: <c:out value="${event.secondText}"/></li>

    </ul>
    <hr />

</c:forEach>

<h2>Создание нового ивента</h2><br />

<form method="post" action="">

    <label>Переводимый текст: <input type="text" name="firstText"></label><br>

    <label>Укажите язык перевода: ru en ...<input type="text" name="language"></label><br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>

</body>
</html>
