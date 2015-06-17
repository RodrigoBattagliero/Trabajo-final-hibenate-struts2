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
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <h1 class="page-header">Seleccionar solicitud</h1>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <td>N° Solicitud</td>
                        <td>Docente</td>
                        <td>Fecha de presentación</td>
                        <td>Acciones</td>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="entities" var="solicitud">
                        <tr>
                            <td><s:property value="#solicitud.solicitudes.numeroSolicitud" /></td>
                            <td><s:property value="#solicitud.solicitudes.docenteses[0].nombre" /></td>
                            <td><s:property value="#solicitud.solicitudes.fechaAlta" /></td>
                            <td>
                                <s:url var="url1" action="SolicitudesDevueltasAdministrar">
                                    <s:param name="idSolicitudSelected" value="%{#solicitud.solicitudes.id}"></s:param>
                                </s:url>
                                <s:url var="url2" action="ConsultarDetalle">
                                    <s:param name="idSolicitudSelected" value="%{#solicitud.solicitudes.id}"></s:param>
                                </s:url>
                                <s:a href="%{url1}" class="btn" >Administrar</s:a>
                                <s:a href="%{url2}" class="btn" >Detalle</s:a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
             </table>
            
            <%@include file="partes/footer.jsp" %>
        </div>
    </body>
</html>
