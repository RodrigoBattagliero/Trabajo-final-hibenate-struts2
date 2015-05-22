<%-- 
    Document   : DesignacionesSolicitudForm
    Created on : May 21, 2015, 11:17:16 PM
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
        <h1>Designaciones</h1>
        <hr />
        <table>
            <thead>
                <tr>
                    <td>Número Resolución</td>
                    <td>Categoria</td>
                    <td>Desde</td>
                    <td>Hasta</td>
                    <td>Dedicación</td>
                    <td>Observaciones</td>
                    <td>Acciones</td>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="entities" var="designacion">
                    <tr>
                        <td><s:property value="#designacion.numeroResolucion" /></td>
                        <td><s:property value="#designacion.categoria" /></td>
                        <td><s:property value="#designacion.desde" /></td>
                        <td><s:property value="#designacion.hasta" /></td>
                        <td><s:property value="#designacion.dedicacion" /></td>
                        <td><s:property value="#designacion.observaciones" /></td>
                        <td>
                            <s:url action="ActividadDocenteForm">
                                <s:param name="idDesignacionSelected" value="#designacion.id"></s:param>
                            </s:url>
                        </td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </body>
</html>
