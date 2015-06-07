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
        <ul>
            <li><s:a action="IniciarSolicitudForm">Iniciar solicitud</s:a></li>
            <li><s:a action="DatosCompletarForm">Completar Datos</s:a></li>
            <li><s:a action="ConfirmarSolicitudesForm">Confirmar solicitudes</s:a></li>
            <li><s:a action="HistorialSolicitudes">Historial solicitudes</s:a></li>
            <li><s:a action="ConsultarForm">Consultar Registro unico</s:a></li>
            <li><s:a action="/SedeCapital/SolicitudesIniciadasSedeInteriorForm">Solicitudes inicadas en sede interior</s:a></li>
            <li><s:a action="/SedeCapital/GenerarExpedienteForm">Generar expediente</s:a></li>
            <li><s:a action="/SedeCapital/ListarExpedientes">Expedientes</s:a></li>
            <li><s:a action="/Administrativo/ImptableAdministracionForm">Solicitudes devueltas</s:a></li>
            
        </ul>
    </body>
</html>
