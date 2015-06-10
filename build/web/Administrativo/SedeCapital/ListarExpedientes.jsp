<%-- 
    Document   : ListarExpedientes
    Created on : May 16, 2015, 3:48:54 AM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../../bootstrap/css/bootstrap.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <s:include value="../partes/menu.jsp" />
            <h1>Expedientes</h1>
            <hr />
            <table class="table table-striped">
                <thead>
                    <td>N° expediente</td>
                    <td>Fecha</td>
                    <td>Acciones</td>
                </thead>
                <tbody>
                    <s:iterator value="entities" var="exp">
                        <tr>
                            <td><s:property value="#exp.numeroExpediente" /></td>
                            <td><s:property value="#exp.fecha" /></td>
                            <td>
                                <s:url var="url" action="ExpedienteDetalle">
                                    <s:param name="idExpedienteSelected" value="%{#exp.id}"></s:param>
                                </s:url>
                                <s:a href="%{url}" class="btn" >Ver detalle</s:a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>
    </body>
</html>