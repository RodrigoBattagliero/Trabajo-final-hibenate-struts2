<%-- 
    Document   : Buscar
    Created on : 03-Jun-2015, 19:00:09
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
            <s:form action="Consultar">
                <s:textfield name="nombreDocente" label="Nombre del docente" />
                <s:textfield name="apellidoDocente" label="Apellido del docente" />
                <s:textfield name="fechaPresentacion" label="Fecha de presentación" />
                <s:submit value="Consultar" />
            </s:form>
            <table class="table table-striped">
                <thead>
                    <td>N solicitud</td>
                    <td>Docente</td>
                    <td>Fecha de presentación</td>
                    <td>Acciones</td>
                </thead>
                <tbody>
                    <s:iterator value="entities" var="list">
                        <tr>
                            <td><s:property value="#list[0].solicitudes.numeroSolicitud" /></td>
                            <td><s:property value="#list[1].nombre" /></td>
                            <td><s:property value="#list[0].solicitudes.fechaAlta" /></td>
                            <td>
                                <s:url var="url1" action="ConsultarDetalle">
                                    <s:param name="idSolicitudSelected" value="%{#list[0].solicitudes.id}"></s:param>
                                </s:url>
                                <s:url var="url2" action="EditarForm">
                                    <s:param name="idSolicitudSelected" value="%{#list[0].solicitudes.id}"></s:param>
                                </s:url>
                                <s:a href="%{url1}" >Detalle</s:a>
                                <s:a href="%{url2}" >Editar</s:a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>
    </body>
</html>

