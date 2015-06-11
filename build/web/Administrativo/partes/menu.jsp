<%-- 
    Document   : menu
    Created on : 10-Jun-2015, 03:46:54
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
        <div class="masthead">
			<h3 class="text-musted">Proyecto</h3>
			<nav>
				<ul class="nav nav-justified">
					<li><s:a action="IniciarSolicitudForm">Iniciar solicitud</s:a></li>
                                        <li><s:a action="DatosCompletarForm">Completar Datos</s:a></li>
                                        <li><s:a action="ConfirmarSolicitudesForm">Confirmar solicitudes</s:a></li>
                                        <li><s:a action="HistorialSolicitudes">Historial solicitudes</s:a></li>
                                        <li><s:a action="ConsultarForm">Consultar Registro unico</s:a></li>
                                        <s:if test="#session.user.areas.id == 1">
                                            <li><s:a action="SedeCapital/SolicitudesIniciadasSedeInteriorForm">Solicitudes inicadas en sede interior</s:a></li>
                                            <li><s:a action="SedeCapital/GenerarExpedienteForm">Generar expediente</s:a></li>
                                            <li><s:a action="SedeCapital/ListarExpedientes">Expedientes</s:a></li>
                                        </s:if>
                                        <s:else>
                                            <li><s:a action="SedeInterior/SolicitudesActividadDocenteForm">Completar actividad</s:a></li>
                                        </s:else>
                                        <li><s:a action="SolicitudesDevueltasForm">Solicitudes devueltas</s:a></li>
                                        <li><s:a action="logout">Salir</s:a></li>
				</ul>
			</nav>
		</div>
    </body>
</html>
