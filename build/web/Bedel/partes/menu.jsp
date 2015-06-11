<%-- 
    Document   : menu
    Created on : 10-Jun-2015, 16:47:23
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3 class="text-musted">Proyecto</h3>
        <nav>
            <ul class="nav nav-justified">
                <li><s:a action="SolicitudesActividadDocenteForm">Solcitudes a completar</s:a></li>
                <li><s:a action="ConfirmarSolicitudesForm">Confirmar lote de solicitudes</s:a></li>
                <li><s:a action="SolicitudesDevueltasForm">Solicitudes devueltas</s:a></li>
                <li><s:a action="HistorialSolicitudes">Historial solicitudes</s:a></li>
                <li><s:a action="logout">Salir</s:a></li>
            </ul>
        </nav>
    </body>
</html>
