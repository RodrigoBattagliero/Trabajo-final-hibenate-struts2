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
                    <a class="navbar-brand" href="#">Abrir</a>
                  </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><s:a namespace="/SuperUser" action="index">Inicio</s:a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Perfiles <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><s:a namespace="/Administrativo" action="index">Sede interior</s:a></li>
                                <li><s:a namespace="/Administrativo" action="index">OGAGTD</s:a></li>
                                <li><s:a namespace="/Bedel" action="index">Bedelía</s:a></li>
                                <li><s:a namespace="/RendicionDeCuentas" action="index">Rendición de cuentas</s:a></li>
                                <li><s:a namespace="/SecretariaAdministrativoFinanciera" action="index">SAF</s:a></li>
                                <li><s:a namespace="/DireccionEconomicoFinanciera" action="index">Direccion economico financiera</s:a></li>
                                <li><s:a namespace="/Tesoreria" action="index">Tesorería</s:a></li>
                                <li><s:a namespace="/LiquidacionDeHaberes" action="index">Liquidación de haberes</s:a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Administración <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><s:a namespace="/SuperUser" action="newPasswordForm">Cambiar contraseña</s:a></li>
                            <li><s:a namespace="/SuperUser" action="logout">Salir</s:a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

