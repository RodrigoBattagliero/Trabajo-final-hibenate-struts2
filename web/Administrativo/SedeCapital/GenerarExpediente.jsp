<%-- 
    Document   : GenerarExpediente
    Created on : May 16, 2015, 3:48:45 AM
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
        <h1>Solicitudes</h1>
        <hr />
        <s:form action="GenerarExpediente" theme="simple" method="post">
        <table>
        <thead>
            <tr>
                <td>Numero de solicitud</td>
                <td>Docente</td>
                <td>Fecha de presentacion</td>
                <td>Estado</td>
                <td>Confirmado</td>
            </tr>
        </thead>
        <tbody>
            <s:iterator value="#session.RegistrosAConfirmar" var="list">
                <tr>
                    <td><s:property value="#list[2].numeroSolicitud" /></td>
                    <td><s:property value="#list[3].apellido" />, <s:property value="#list[3].nombre" /></td>
                    <td><s:property value="#list[2].fechaAlta" /></td>
                    <td><s:property value="#list[1].nombre" /></td>
                    <td>
                        <s:checkbox name="%{#list[2].id}" value="true" />
                    </td>
                </tr>
            </s:iterator>
                <tr>
                    <td><s:textfield name="fecha" label="Fecha" /></td>
                    <td><s:textfield name="numeroExpediente" label="N de expediente" /></td>
                    <td colspan="5"><s:submit value="Confirmar" /></td>
                </tr>
            </tbody>
            </table>
        </s:form>
    </body>
</html>
