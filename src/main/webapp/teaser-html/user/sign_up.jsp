<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 25/05/2023
  Time: 1:46 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Sign Up Page</title>
</head>
<body>
<h2>Sign Up</h2>
<form action="sign_up.jsp" method="post">
  <label for="name">Name:</label>
  <input type="text" id="name" name="name"><br><br>
  <label for="email">Email:</label>
  <input type="email" id="email" name="email"><br><br>
  <label for="username">Username:</label>
  <input type="text" id="username" name="username"><br><br>
  <label for="password">Password:</label>
  <input type="password" id="password" name="password"><br><br>
  <label for="confirm_password">Confirm Password:</label>
  <input type="password" id="confirm_password" name="confirm_password"><br><br>
  <input type="submit" value="Sign Up">
</form>
</body>
</html>
