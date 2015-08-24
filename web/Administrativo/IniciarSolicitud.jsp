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
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="../js/datetimepicker-master/jquery.datetimepicker.css"/>
        <title>Administrativo</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <ol class="breadcrumb">
                <li><a href="#">Inicio</a></li>
                <li class="active">Iniciar solicitud</li>
            </ol>
            <s:if test="entity.id" >
                <h1 class="page-header">Editar solicitud</h1>
                <s:set var="url">SolicitudUpdate</s:set>
            </s:if>
            <s:else>
                <h1 class="page-header">Iniciar solicitud</h1>
                <s:set var="url">SolicitudPreparar</s:set>
            </s:else>
            <s:actionerror />
            <s:fielderror />
            <div class="row">
                <div class="col-sm-2"></div>
                <div class="col-sm-8">
                <s:form action="%{url}" theme="simple">
                    <div class="form-group">
                        <label>Sede</label>
                        <s:select list="sedesList" listKey="id" listValue="nombre" name="idSelectedSede" value="entity.sedes.id" label="Sede" class="form-control"></s:select>
                    </div>
                    <div class="form-group">
                        <s:hidden name="entity.tipo" readonly="true" label="Tipo" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label>Número de solicitud</label>
                        <s:textfield name="entity.numeroSolicitud" label="Número de solicitud" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Fecha alta</label>
                        <s:textfield name="entity.fechaAlta" class="form-control fecha" label="Fecha alta" />
                    </div>
                    <div class="form-group">
                        <label>Observaciones</label>
                        <s:textarea name="entity.observaciones" label="Observaciones" class="form-control" />
                    </div>
                    <div class="form-group">
                        <s:submit value="Guardar" class="btn btn-primary" />
                    </div>
                 </s:form>
                </div>
                <div class="col-sm-2"></div>
            </div>
            <%@include file="partes/footer.jsp" %>
        </div>
        <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
        <script>
            $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y'});
        </script>		
    </body>
</html>

