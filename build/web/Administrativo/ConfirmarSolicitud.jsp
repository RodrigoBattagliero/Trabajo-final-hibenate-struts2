<%-- 
    Document   : ConfirmarSolicitud
    Created on : May 16, 2015, 7:00:09 AM
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
        <h1>Confirmar Solicitud</h1>
        <hr />
        <s:a action="IniciarSolcitud">Guardar</s:a>
        <s:a action="Administrativo">Cancelar</s:a>
    </body>
</html>
