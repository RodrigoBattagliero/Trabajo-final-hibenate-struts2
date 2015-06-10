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
            <h1>Seleccionar solicitud</h1>
            <hr />
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
        </div>
    </body>
</html>
