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
        <title></title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <ol class="breadcrumb">
                <li><a href="#">Inicio</a></li>
                <li><a href="#">Solicitudes devueltas</li>
                <li class="active">Registro unico</li>
            </ol>
            <h1 class="page-header">Administrar registro único</h1>
            <s:form action="PreparedRegistroUnico" theme="simple">
                <div class="form-group">
                    <label>ID</label>
                    <s:textfield name="idRegistroUnico" value="%{entity.id}" label="Id" disabled="true" class="form-control"/>
                </div>
                <div class="form-group">
                    <label>Número de solicitud</label>
                    <s:textfield name="" value="%{entity.solicitudes.numeroSolicitud}" label="Número de solicitud" disabled="true"  class="form-control" />
                </div>
                <div class="form-group">
                    <label>Area</label>
                    <s:textfield name="idAreaSelected" label="Area" value="%{areaLogueada.nombre}" disabled="true"  class="form-control" />
                </div>
                <div class="form-group">
                    <label>Fecha entrada</label>
                    <s:textfield name="entity.fechaEntrada" value="%{entity.fechaEntrada}" label="Fecha entrada" disabled="true" class="fecha form-control" />
                </div>
                <div class="form-group">
                    <label>Fecha Salida</label>
                    <s:textfield name="entity.fechaSalida" value="%{entity.fechaSalida}" label="Fecha Salida" disabled="true" class="fecha form-control" />
                </div>
                <div class="form-group">
                    <label>Estado</label>
                    <s:select list="#@java.util.TreeMap@{'2':'Aprobado','3':'Rechazado'}" name="idEstadoSelected" label="Estado" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Observaciones</label>
                    <s:textarea name="AdministrarObservaciones" label="Observaciones"  class="form-control" />
                </div>
                <div class="form-group">
                    <label></label>
                    <s:submit value="Guardar" class="btn btn-primary"/>
                </div>
            </s:form>
            
            <%@include file="partes/footer.jsp" %>
        </div>
            
        <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
        <script>
            $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y H:m'});
        </script>
    </body>
</html>
