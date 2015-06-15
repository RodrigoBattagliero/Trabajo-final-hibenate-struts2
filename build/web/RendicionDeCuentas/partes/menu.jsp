<%-- 
    Document   : menu
    Created on : 10-Jun-2015, 17:05:01
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="masthead">
            <h3 class="text-musted">Proyecto</h3>
            <nav>
                <ul class="nav nav-justified">
                    <li><s:a namespace="/RendicionDeCuentas" action="RendicionDeCuentasForm">Solcitudes a completar</s:a></li>
                    <li><s:a namespace="/RendicionDeCuentas" action="ConfirmarSolicitudesForm">Confirmar lote de solicitudes</s:a></li>
                    <li><s:a namespace="/RendicionDeCuentas" action="SolicitudesDevueltasForm">Solicitudes devueltas</s:a></li>
                    <li><s:a namespace="/RendicionDeCuentas" action="HistorialSolicitudes">Historial solicitudes</s:a></li>
                    <li><s:a namespace="/RendicionDeCuentas" action="logout">Salir</s:a></li>
                </ul>
            </nav>
        </div>
    </body>
</html>
