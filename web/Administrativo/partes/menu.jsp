<%-- 
    Document   : menu
    Created on : 10-Jun-2015, 03:46:54
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                 <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                      <span class="sr-only">Navegación</span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Abrir</a>
                  </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><s:a namespace="/Administrativo" action="IniciarSolicitudForm">Iniciar solicitud</s:a></li>
                        <li><s:a namespace="/Administrativo" action="ConfirmarSolicitudesForm">Confirmar solicitudes</s:a></li>
                        <li><s:a namespace="/Administrativo" action="HistorialSolicitudes">Historial solicitudes</s:a></li>
                        <li><s:a namespace="/Administrativo"  action="ConsultarForm">Consultar Registro unico</s:a></li>
                        <s:if test="#session.user.areas.id == 1">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Expedientes <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><s:a namespace="/Administrativo/SedeCapital" action="SolicitudesIniciadasSedeInteriorForm">Solicitudes inicadas en sede interior</s:a></li>
                                    <li><s:a namespace="/Administrativo/SedeCapital" action="GenerarExpedienteForm">Generar expediente</s:a></li>
                                    <li><s:a namespace="/Administrativo/SedeCapital" action="ListarExpedientes">Expedientes</s:a></li>
                                </ul>
                            </li>
                        </s:if>
                        <s:else>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Actividad docente <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><s:a namespace="/Administrativo/SedeInterior" action="SolicitudesActividadDocenteForm">Completar actividad</s:a></li>
                                    <li><s:a namespace="/Administrativo/SedeInterior" action="ConfirmarSolicitudesForm">Comfirmar actividades</s:a></li>
                                </ul>                            
                            </li>
                        </s:else>
                        <li><s:a namespace="/Administrativo" action="SolicitudesDevueltasForm">Solicitudes devueltas</s:a></li>
                        <li><s:a namespace="/Administrativo" action="logout">Salir</s:a></li>
                    </ul>
                </div>
            </div>
        </nav>
