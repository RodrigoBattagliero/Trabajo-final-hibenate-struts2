<%-- 
    Document   : historialSolicitudes
    Created on : 03-Jun-2015, 00:30:17
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap-theme.css" />
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <ol class="breadcrumb">
                <li><a href="#">Inicio</a></li>
                <li><a href="#">Solicitudes</a></li>
                <li class="active">Historial de solicitudes procesadas</li>
            </ol>
            <h1 class="page-header">Historial solicitudes</h1>
            <table class="table table-bordered table-hover">
            <thead>
                <tr class="success">
                    <th>N solicitud</th>
                    <th>Docente</th>
                    <th>Fecha de presentación</th>
                </tr>
            </thead>
            <tbody> 
                <s:iterator value="historial" var="list">
                    <tr>
                        <td><s:property value="#list[0].solicitudes.numeroSolicitud" /></td>
                        <td><s:property value="#list[1].nombre" /></td>
                        <td><s:property value="#list[0].solicitudes.fechaAlta" /></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
            
        <%@include file="partes/footer.jsp" %>
        </div>
    </body>
</html>
