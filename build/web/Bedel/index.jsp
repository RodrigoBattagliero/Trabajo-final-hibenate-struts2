<%-- 
    Document   : index
    Created on : 30-May-2015, 22:46:32
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dirección académica administrativa</title>
    </head>
    <body>
        <ul>
            <li><s:a action="SolicitudesActividadDocenteForm">Solcitudes a completar</s:a></li>
            <li><s:a action="">Historial de solicitudes</s:a></li>
            <li><s:a action="ConfirmarSolicitudesForm">Confirmar lote de solicitudes</s:a></li>
            <li><s:a action="SolicitudesDevueltasForm">Solicitudes devueltas</s:a></li>
            <li><s:a action="HistorialSolicitudes">Historial solicitudes</s:a></li>
            <li><s:a action="logout">Salir</s:a></li>
        </ul>
        <s:if test="cantidadSolicitudesCompletar != 0">
            <p>Tiene <s:property value="cantidadSolicitudesCompletar" /> <s:a action="DatosCompletarForm">solicitudes para completar</s:a></p>
        </s:if>
        <s:if test="cantidadSolicitudesDevueltas != 0">
            <p>Tiene <s:property value="cantidadSolicitudesDevueltas" /> <s:a action="SolicitudesDevueltasForm">solicitudes devueltas</s:a></p>
        </s:if>
    </body>
</html>
