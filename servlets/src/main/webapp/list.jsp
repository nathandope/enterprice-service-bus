<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Client List</title>
</head>
<body>
    <h2>Client List</h2>

    <form action="add">
        <p><input type="submit" value="Add">
        <input type="submit" form="client-list" formaction="edit" formmethod="get" value="Edit"></p>
    </form>

    <hr>

    <form id="client-list">
        <table border=1 cellpadding=5>
            <tr>
                <th></th>
                <th>Number</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Birth Date</th>
                <th>Login</th>
                <th>Password</th>
            </tr>

            <c:forEach items="${clients}" var="client" varStatus="counter">
                <tr>
                    <td><input name="id" type="radio" value=${client.id} required></td>
                    <td>${counter.count}</td>
                    <td>${client.firstName}</td>
                    <td>${client.lastName}</td>
                    <td>${client.birthDate}</td>
                    <td>${client.login}</td>
                    <td>${client.password}</td>
                </tr>
            </c:forEach>
        </table>
    </form>

</body>
</html>