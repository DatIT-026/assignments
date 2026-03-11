<%-- 
    Document   : CreateUser
    Created on : Jan 30, 2026, 9:10:38 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
    </head>
    <body>
        <h1>Create User</h1>
        <form action="UserController" method="post">
            UserName <input type="text" placeholder="Enter user name" name="txtUserName"/><br/>
            Password <input type="password" placeholder="Enter password" name="txtPassword"/><br/>
            LastName <input type="text" placeholder="Enter last name" name="txtLastName"/><br/>
            <input type="checkbox" name="chkIsAdmin" disabled="true" />isAdmin<br/>
            <input type="submit" value="Create" name="action" /><br/>
            <a href='Login.html'>Back</a><br/>
        </form>
    </body>
</html>
