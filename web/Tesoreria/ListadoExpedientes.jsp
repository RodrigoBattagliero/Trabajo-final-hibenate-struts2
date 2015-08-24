<%-- 
    Document   : listadoExpedientes
    Created on : 17-Jun-2015, 23:40:31
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../js/datetimepicker-master/jquery.datetimepicker.css"/>
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <title>Tesorería</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <ol class="breadcrumb">
                <li><a href="#">Inicio</a></li>
                <li class="active">Expedientes</li>
            </ol>
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-header">Expedientes</h1>
                    <s:fielderror />
                    <s:actionerror />
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr class="success">
                                <th>Fecha iniciada</th>
                                <th>Número expediente</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="expedientes" var="exp">
                                <tr>
                                    <td><s:property value="#exp.fecha" /></td>
                                    <td><s:property value="#exp.numeroExpediente" /></td>
                                    <td>
                                            <s:url var="url" action="ExpedienteDetalle">
                                                <s:param name="idExpedienteSelected" value="%{#exp.id}"></s:param>
                                            </s:url>
                                            <s:url var="url2" action="ReporteDescargarExcel">
                                                <s:param name="idExpedienteSelected" value="%{#exp.id}"></s:param>
                                            </s:url>
                                            <s:a href="%{url}" class="btn" >Solicitudes</s:a>
                                            <s:a href="%{url2}" class="btn" >Descargar EXCEL</s:a>
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
