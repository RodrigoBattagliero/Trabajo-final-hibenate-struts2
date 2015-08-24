<%-- 
    Document   : menu
    Created on : May 16, 2015, 3:59:00 AM
    Author     : rodrigo
--%>

<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" cont    ent="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../../bootstrap/css/bootstrap.css" />
        <title>Administrativo</title>
    </head>
    <body>
        <div class="container">
            
            <s:include value="../partes/menu.jsp" />
            <ol class="breadcrumb">
                <li class="active">Inicio</li>
            </ol>
            <p>
                ERROR.
            </p>
            <s:actionerror />
            <%@include file="../partes/footer.jsp" %>
        </div>
    </body>
</html>