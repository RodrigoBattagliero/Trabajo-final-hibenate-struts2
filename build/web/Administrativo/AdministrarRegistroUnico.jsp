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
            <h1 class="page-header">Administrar regitro único</h1>
            <s:form action="administrarRegistroUnico">
                <div class="form-group">
                    <label>Número de solicitud</label>
                    <s:textfield name="" value="%{entity.solicitudes.numeroSolicitud}" label="Número de solicitud" disabled="true" class="form-control" />
		</div>
                <div class="form-group">
                    <label>Area</label>
                    <s:textfield name="idAreaSelected" label="Area" value="%{areaLogueada.nombre}" disabled="true" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Fecha entrada</label>
                    <s:textfield name="entity.fechaEntrada" value="%{entity.fechaEntrada}" label="Fecha entrada" disabled="true" class="form-control fecha"  />
                </div>
                <div class="form-group">
                    <label>Fecha Salida</label>
                    <s:textfield name="entity.fechaSalida" value="%{entity.fechaSalida}" label="Fecha Salida" class="form-control fecha"  />
                </div>
                <div class="form-group">
                    <label>Estado</label>
                    <s:select list="listEstados" listKey="id" listValue="nombre" name="idEstadoSelected" label="Estado" class="form-control" />
		</div>
                <div class="form-group">
                    <label>Observaciones</label>
                    <s:textarea name="AdministrarObservaciones" label="Observaciones" class="form-control"  />
		</div>
                <div class="form-group">
                    <s:submit value="Guardar" class="btn" />
		</div>
            </s:form>
            
            <%@include file="partes/footer.jsp" %>
        </div>
            
        <script src="../js/jquery-1.11.3.min.js"></script>
        <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
        <script>
            $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y'});
        </script>
    </body>
</html>
