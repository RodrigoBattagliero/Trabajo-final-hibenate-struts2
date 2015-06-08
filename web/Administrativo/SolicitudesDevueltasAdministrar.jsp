<%-- 
    Document   : AdministrarRegistroUnico
    Created on : May 19, 2015, 7:20:22 PM
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
        <h1>Administrar registro único</h1>
        <hr />
        
        <s:form action="PreparedRegistroUnico">
            <s:textfield name="" value="%{entity.solicitudes.numeroSolicitud}" label="Número de solicitud" disabled="true"  />
            <s:select list="listAreas" listKey="id" listValue="nombre" name="idAreaSelected" label="Area" />
            <s:textfield name="entity.fechaEntrada" value="%{entity.fechaEntrada}" label="Fecha entrada" disabled="true"  />
            <s:textfield name="entity.fechaSalida" value="%{entity.fechaSalida}" label="Fecha Salida" disabled="true"  />
            <s:select list="listEstados" listKey="id" listValue="nombre" name="idEstadoSelected" label="Estado" />
            <s:textarea name="entity.observaciones" label="Observaciones"  />
            
            <s:submit value="Guardar" />
        </s:form>
    </body>
</html>
