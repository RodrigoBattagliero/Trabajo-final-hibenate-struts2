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
        <link rel="stylesheet" type="text/css" href="../css/style.css" />
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
    </head>
    <body>
        <h1>Solicitudes</h1>
        <hr />
        <table>
            <thead>
                <td>N solicitud</td>
                <td>Docente</td>
                <td>Fecha de presentación</td>
            </thead>
            <tbody>
                <s:iterator value="entities" var="list">
                    <tr class="asas">
                        <td><s:property value="#list[0].solicitudes.numeroSolicitud" /></td>
                        <td><s:property value="#list[1].nombre" /></td>
                        <td><s:property value="#list[0].solicitudes.fechaAlta" /></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </body>
</html>
