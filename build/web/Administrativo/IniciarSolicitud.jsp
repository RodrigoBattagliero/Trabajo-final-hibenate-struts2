<%-- 
    Document   : IniciarSolicitud
    Created on : May 9, 2015, 2:14:13 AM
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
        <h1>Iniciar solicitud</h1>
        <s:form action="SolicitudPreparar">
                <s:select list="sedesList" listKey="id" listValue="nombre" name="idSelectedSede"></s:select>
                <tr>
                    <td><label for="tipo">tipo</label></td>
                    <td>
                        <select name="entity.tipo" id="">
                            <option value="1">Capital</option>
                            <option value="2">Interior</option>
                        </select>
                    </td>
                </tr>
            <s:textfield name="entity.numeroSolicitud" label="numeroSolicitud" />
            <s:textfield name="entity.fechaAlta" label="fechaAlta" />
            <s:textarea name="entity.observaciones" label="observaciones" />
            <s:submit value="Guardar" />
        </s:form>
    </body>
</html>

