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
        <title>Administrativo</title>
    </head>
    <body>
        <div class="container">
            <s:include value="../partes/menu.jsp" />
            <ol class="breadcrumb">
                <li><a href="#">Expedientes</a></li>
                <li class="active">Listado de expedientes</li>
            </ol>
            <h1 class="page-header">Expedientes</h1>
            <table class="table table-bordered table-hover">
                <thead>
                    <tr class="success">
                        <th>N° expediente</th>
                        <th>Fecha</th>
                        <th>Acciones</th>
                    </tr>
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
            
            <s:include value="../partes/footer.jsp" />
        </div>
    </body>
</html>