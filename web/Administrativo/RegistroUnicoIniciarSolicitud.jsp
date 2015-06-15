<%-- 
    Document   : RegistroUnicoInicarSolicitud
    Created on : May 17, 2015, 12:28:05 AM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../js/datetimepicker-master/jquery.datetimepicker.css"/>
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <h1>Registro único</h1>
            <s:actionerror />
            <s:fielderror />
            <s:form action="RegistroUnicoPrepare">
                <s:textfield name="entity.fechaEntrada" label="Fecha de entrada" class="fecha" />
                <s:select list="#@java.util.TreeMap@{'2':'Profesor'}" label="Area" />
                <s:select list="listEstados" listKey="id" listValue="nombre" name="idEstadoSelected" label="Estado" />
                <s:checkbox name="entity.confirmado" label="Confirmado" value="true" disabled="true" />
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
        
    

