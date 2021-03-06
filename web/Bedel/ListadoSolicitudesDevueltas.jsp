<%-- 
    Document   : CompletarDatos
    Created on : May 16, 2015, 3:48:06 AM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <title>Dirección académica administrativa</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
                <ol class="breadcrumb">
                    <li><a href="#">Inicio</a></li>
                    <li class="active">Solicitudes devueltas</li>
                </ol>
                <div class="row">
                    <div class="col-sm-12">
                        <h1 class="page-header">Solicitudes devueltas</h1>
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr class="success">
                                    <th>N° Solicitud</th>
                                    <th>Docente</th>
                                    <th>Fecha de presentación</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="entities" var="solicitud">
                                    <tr>
                                        <td><s:property value="#solicitud[0].solicitudes.numeroSolicitud" /></td>
                                        <td><s:property value="#solicitud[1].apellido" />, <s:property value="#solicitud[1].nombre" /></td>
                                        <td><s:property value="#solicitud[0].solicitudes.fechaAlta" /></td>
                                        <td>
                                            <s:url var="url1" action="SolicitudesDevueltasAdministrar">
                                                <s:param name="idSolicitudSelected" value="%{#solicitud[0].solicitudes.id}"></s:param>
                                            </s:url>
                                            <s:url var="url2" action="ConsultarDetalle">
                                                <s:param name="idSolicitudSelected" value="%{#solicitud[0].solicitudes.id}"></s:param>
                                            </s:url>
                                            <s:a href="%{url1}" class="btn" >Administrar</s:a>
                                            <s:a href="%{url2}" class="btn" >Detalle</s:a>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
                <%@include file="partes/footer.jsp" %>
        </div>
    </body>
</html>
