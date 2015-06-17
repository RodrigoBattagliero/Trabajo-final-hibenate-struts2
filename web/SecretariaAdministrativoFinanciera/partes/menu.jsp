<%-- 
    Document   : menu
    Created on : 10-Jun-2015, 17:13:50
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
                        <li><s:a namespace="/SecretariaAdministrativoFinanciera" action="DatosCompletarForm">Solcitudes a completar</s:a></li>
                        <li><s:a namespace="/SecretariaAdministrativoFinanciera" action="ConfirmarSolicitudesForm">Confirmar lote de solicitudes</s:a></li>
                        <li><s:a namespace="/SecretariaAdministrativoFinanciera" action="HistorialSolicitudes">Historial solicitudes</s:a></li>
                        <li><s:a namespace="/SecretariaAdministrativoFinanciera" action="logout">Salir</s:a></li>
                    </ul>
                </div>
            </div>
        </nav>

