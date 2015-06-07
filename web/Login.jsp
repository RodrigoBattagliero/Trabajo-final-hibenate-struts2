<%-- 
    Document   : Login
    Created on : 27-May-2015, 22:18:34
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <hr />
        <s:fielderror />
        <s:actionerror />
        <s:form action="LoginAction">
            <s:textfield name="user" label="Usuario" />
            <s:password name="password" label="Password" />
            <s:submit value="Ingresar" />
        </s:form>
    </body>
</html>
