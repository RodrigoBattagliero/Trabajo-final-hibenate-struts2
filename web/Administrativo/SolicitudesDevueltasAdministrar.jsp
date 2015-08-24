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
        <title>Administrativo</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <h1 class="page-header">Administrar registro único de solicitud</h1>
            <div class="row">
               <div class="col-sm-2"></div>
               <div class="col-sm-8">
                <s:form action="PreparedRegistroUnico" theme="simple">
                    <div class="form-group">
                        <label>Número de solicitud</label>
                        <s:textfield name="" value="%{entity.solicitudes.numeroSolicitud}" label="Número de solicitud" disabled="true" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Area</label>
                        <s:select list="listAreas" listKey="id" listValue="nombre" name="idAreaSelected" label="Area" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Fecha entrada</label>
                        <s:textfield name="entity.fechaEntrada" value="%{entity.fechaEntrada}" label="Fecha entrada" disabled="true" class="form-control fecha" />
            </div>
                    <div class="form-group">
                        <label>Fecha Salida</label>
                        <s:textfield name="entity.fechaSalida" value="%{entity.fechaSalida}" label="Fecha Salida" disabled="true" class="form-control fecha" />
            </div>
                    <div class="form-group">
                        <label>Estado</label>
                        <s:select list="#@java.util.TreeMap@{'8':'Reintegrar'}" name="idEstadoSelected" label="Estado" class="form-control" />
            </div>
                    <div class="form-group">
                        <label>Observaciones</label>
                        <s:textarea name="entity.observaciones" label="Observaciones" class="form-control"  />
            </div>
                    <div class="form-group">
                        <s:submit value="Guardar" class="btn" />
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
