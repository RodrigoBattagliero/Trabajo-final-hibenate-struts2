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
                     <a class="navbar-brand" href="#">SSGTD</a>
                  </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><s:a namespace="/Administrativo" action="index" >Inicio</s:a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Solicitudes <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><s:a namespace="/Administrativo" action="IniciarSolicitudForm">Iniciar solicitud</s:a></li>
                                <li><s:a namespace="/Administrativo" action="DatosCompletarForm">Solicitudes a completar</s:a></li>
                                <li><s:a namespace="/Administrativo" action="ConfirmarSolicitudesForm">Confirmar solicitudes</s:a></li>
                                <li><s:a namespace="/Administrativo" action="HistorialSolicitudes">Historial solicitudes</s:a></li>
                            </ul>
                        </li>
                        <s:if test="#session.user.areas.id == 1">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Expedientes <span class="caret"></span></a>
                                <ul class="dropdown-menu">
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
                                    <li><s:a namespace="/Administrativo/SedeInterior" action="ConfirmarSolicitudesForm">Confirmar actividades</s:a></li>
                                </ul>                            
                            </li>
                        </s:else>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Soliciudes devueltas <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <s:if test="#session.user.areas.id == 1">
                                    <li><s:a namespace="/Administrativo" action="SolicitudesDevueltasForm">Reintegrar solicitudes</s:a></li>
                                </s:if>
                                <li><s:a namespace="/Administrativo" action="setReintegrar">Solicitudes devueltas</s:a></li>
                            </ul>
                        </li>
                        <li><s:a namespace="/Administrativo" action="Reportes">Reportes</s:a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><s:property value="#session.user.user" ></s:property><span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><s:a namespace="/Administrativo" action="newPasswordForm">Cambiar contraseña</s:a></li>
                            <li><s:a namespace="/Administrativo" action="logout">Salir</s:a></li>
                            </ul>
                        </li>
                    </ul>
                    <s:form action="Consultar" id="searchSolicitud" namespace="/Administrativo" cssClass="navbar-form navbar-right" theme="simple">
                        <div class="form-group input-group">
                            <input type="number" name="numeroSol" class="form-control" placeholder="N° solicitud">
                            <span class="input-group-btn" >
                                <button class="btn btn-default" type="submit">
                                    <i class="fa fa-search"></i>
                                </button>
                             </span>
                        </div>
                    </s:form>
                </div>
            </div>
        </nav>
