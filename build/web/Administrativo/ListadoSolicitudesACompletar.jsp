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
        <title></title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <ol class="breadcrumb">
                <li><a href="#">Inicio</a></li>
                <li><a href="#">Solicitudes</a></li>
                <li class="active">Solicitudes a completar</li>
            </ol>
            <h1 class="page-header">Seleccionar solicitud</h1>
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
                    <s:iterator value="listSolicitudesACompletar" var="solicitud">
                        <tr>
                            <td><s:property value="#solicitud[0].numeroSolicitud" /></td>
                            <td><s:property value="#solicitud[1].nombre" /></td>
                            <td><s:property value="#solicitud[0].fechaAlta" /></td>
                            <td>
                                <s:url var="url" action="CompletarDatosCapital">
                                    <s:param name="idSolicitudSelected" value="%{#solicitud[0].id}"></s:param>
                                </s:url>
                                <s:a href="%{url}" class="btn" >Completar designación</s:a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
            
            <%@include file="partes/footer.jsp" %>
        </div>
    </body>
</html>
