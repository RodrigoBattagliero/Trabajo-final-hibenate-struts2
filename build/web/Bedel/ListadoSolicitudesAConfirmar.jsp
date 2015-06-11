<%-- 
    Document   : ListadoSolicitudesAConfirmar
    Created on : May 20, 2015, 11:21:13 PM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
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
            <h1>Confirmar solicitudes</h1>
            <s:form action="ConfirmarSolicitudesPrepared" theme="simple" method="post">
            <table class="table table-striped">
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
                                <s:checkbox name="%{#list[0].id}" value="true" />
                            </td>
                        </tr>
                    </s:iterator>
                        <tr>
                            <td colspan="5"><s:submit value="Confirmar" /></td>
                        </tr>
                    </tbody>
            </table>
            </s:form>
        </div>
    </body>
</html>
