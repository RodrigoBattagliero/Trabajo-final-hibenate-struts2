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
        <link rel="stylesheet" type="text/css" href="../js/datetimepicker-master/jquery.datetimepicker.css"/>
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <h1>Administrar registro único</h1>

            <s:form action="PreparedRegistroUnico">
                <s:textfield name="" value="%{entity.solicitudes.numeroSolicitud}" label="Número de solicitud" disabled="true"  />
                <s:select list="listAreas" listKey="id" listValue="nombre" name="idAreaSelected" label="Area" />
                <s:textfield name="entity.fechaEntrada" value="%{entity.fechaEntrada}" label="Fecha entrada" disabled="true" class="fecha" />
                <s:textfield name="entity.fechaSalida" value="%{entity.fechaSalida}" label="Fecha Salida" disabled="true" class="fecha" />
                <s:select list="#@java.util.TreeMap@{'4':'Imputable a la administración'}" name="idEstadoSelected" label="Estado" />
                <s:textarea name="entity.observaciones" label="Observaciones"  />

                <s:submit value="Guardar" class="btn" />
            </s:form>
        </div>
            
        <script src="../js/jquery-1.11.3.min.js"></script>
        <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
        <script>
            $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y'});
        </script>
    </body>
</html>
