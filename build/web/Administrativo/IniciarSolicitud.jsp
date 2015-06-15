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
        <link rel="stylesheet" type="text/css" href="../js/datetimepicker-master/jquery.datetimepicker.css"/>
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <s:if test="entity.id" >
                <h1>Editar solicitud</h1>
                <s:set var="url">SolicitudUpdate</s:set>
            </s:if>
            <s:else>
                <h1>Iniciar solicitud</h1>
                <s:set var="url">SolicitudPreparar</s:set>
            </s:else>
            <s:actionerror />
            <s:fielderror />
            <s:form action="%{url}">
                <s:select list="sedesList" listKey="id" listValue="nombre" name="idSelectedSede" value="entity.sedes.id" label="Sede"></s:select>
                <s:textfield name="entity.tipo" readonly="true" label="Tipo" />
                <s:textfield name="entity.numeroSolicitud" label="Número de solicitud" />
                <s:textfield name="entity.fechaAlta" class="fecha" label="Fecha alta" />
                <s:textarea name="entity.observaciones" label="Observaciones" />
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

