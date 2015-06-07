<%-- 
    Document   : ExpedienteDetalle
    Created on : 02-Jun-2015, 22:53:20
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Expediente detalle</h1>
        <hr />
        <table>
            <thead>
                <td>N solicitud</td>
                <td>Fecha presentacion</td>
                <td>Docente</td>
            </thead>
            <tbody>
                <s:iterator value="entities" var="list">
                    <tr>
                        <td><s:property value="#list[0].solicitudes.numeroSolicitud" /></td>
                        <td><s:property value="#list[0].solicitudes.fechaAlta" /></td>
                        <td><s:property value="#list[1].nombre" /></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>        
    </body>
</html>
