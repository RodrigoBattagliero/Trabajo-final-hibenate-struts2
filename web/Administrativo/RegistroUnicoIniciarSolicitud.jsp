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
        <title>Administrativo</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <ol class="breadcrumb">
                <li><a href="#">Inicio</a></li>
                <li><a href="#">Iniciar solicitud</a></li>
                <li class="active">Crear registro único de solicitud</li>
            </ol>
            <h1 class="page-header">Registro único de solicitud</h1>
            <s:actionerror />
            <s:fielderror />
            <div class="row">
               <div class="col-sm-2"></div>
               <div class="col-sm-8">
                <s:form action="RegistroUnicoPrepare" theme="simple">
                    <div class="form-group">
                        <label>Fecha de entrada</label>
                        <s:textfield name="entity.fechaEntrada" label="Fecha de entrada" class="form-control fecha" />
                    </div>
                    <div class="form-group">
                        <label>Area</label>
                        <s:select list="#@java.util.TreeMap@{'2':'Profesor'}" label="Area" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Estado</label>
                        <s:select list="listEstados" listKey="id" listValue="nombre" name="idEstadoSelected" label="Estado" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Confirmado</label>
                        <s:checkbox name="entity.confirmado" label="Confirmado" value="true" disabled="true" class="form-control" />
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
        
    

