<%-- 
    Document   : menu
    Created on : 10-Jun-2015, 16:47:23
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
                        <li><s:a namespace="/LiquidacionDeHaberes" action="index">Inicio</s:a></li>
                        <li><s:a namespace="/LiquidacionDeHaberes" action="ExpedientesList">Expedintes a completar</s:a></li>
                        <li><s:a namespace="/LiquidacionDeHaberes" action="ExpedientesListPendientes">Expedintes pendientes de pago</s:a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><s:property value="#session.user.user" ></s:property><span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><s:a namespace="/LiquidacionDeHaberes" action="newPasswordForm">Cambiar contraseña</s:a></li>
                            <li><s:a namespace="/LiquidacionDeHaberes" action="logout">Salir</s:a></li>
                            </ul>
                        </li>
                    </ul>
                    <s:form action="Consultar" id="searchSolicitud" namespace="/LiquidacionDeHaberes" cssClass="navbar-form navbar-right" theme="simple">
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

