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
                <li class="active">Reportes</li>
            </ol>
            <h1 class="page-header">Reportes</h1>
            <table class="table table-bordered table-hover">
                <thead>
                    <tr class="success">
                        <th>N° Reporte</th>
                        <th>Fecha</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="entities" var="reporte">
                        <tr>
                            <td><s:property value="#reporte.id" /></td>
                            <td><s:property value="#reporte.fecha" /></td>
                            <td>
                                <s:url var="url1" action="ReporteDetalle">
                                    <s:param name="idReporteSelected" value="%{#reporte.id}"></s:param>
                                </s:url>
                                <s:url var="url2" action="ReporteDescargar">
                                    <s:param name="idReporteSelected" value="%{#reporte.id}"></s:param>
                                </s:url>
                                <s:a href="%{url1}" class="btn" >Detalle</s:a>
                                <s:a href="%{url2}" class="btn" >Descargar</s:a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
             </table>
            
            <%@include file="partes/footer.jsp" %>
        </div>
    </body>
</html>
