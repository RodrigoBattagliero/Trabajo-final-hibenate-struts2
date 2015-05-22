<%-- 
    Document   : menu
    Created on : May 16, 2015, 3:59:00 AM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" cont    ent="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Administrativo</h1>
        <hr>
        <s:a action="IniciarSolicitudForm">Iniciar solicitud</s:a>
        <s:a action="DatosCompletarCapitalForm">Completar Datos Capital</s:a>
        <s:a action="ConfirmarSolicitudesForm">Confirmar solicitudes</s:a>
        <s:a action="SolicitudesIniciadasSedeInteriorForm">Confirmar solicitudes</s:a>
    </body>
</html>
