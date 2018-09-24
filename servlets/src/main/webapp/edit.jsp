<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client List</title>
</head>
<body>
<h2>Client List</h2>

<form action="list">
    <p><input type="submit" value="Back">
        <input type="submit" form="editing-client" formaction="edit" formmethod="post" value="Save"></p>
</form>

<hr>

<form id="editing-client">
    <table border=1 cellpadding=5>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Birth Date</th>
            <th>Login</th>
            <th>Password</th>
        </tr>

        <tr>
            <input type="hidden" id="id" value="${client.id}" name="id" />
            <td>
                <input type="text" id="first-name" value="${client.firstName}" name="firstName" />
            </td>
            <td>
                <input type="text" id="last-name" value="${client.lastName}" name="lastName" />
            </td>
            <td>
                <input type="text" id="birth-date" value="${client.birthDate}" name="birthDate" />
            </td>
            <td>
                <input type="text" id="lgn" value="${client.login}" name="login" />
            </td>
            <td>
                <input type="text" id="passwd" value="${client.password}" name="password" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
