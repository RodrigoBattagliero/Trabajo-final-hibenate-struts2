<%-- 
    Document   : ActividadEfectivaDocente
    Created on : May 21, 2015, 8:55:45 PM
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
        <title>Dirección académica administrativa</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <h1 class="page-header">Actividad docente</h1>
            <s:fielderror />
            <s:actionerror />
            <s:form action="ActividadDocenteUpdate" theme="simple">
                <div class="form-group">
                    <label>ID</label>
                    <s:textfield name="entity.id" label="Id" disabled="true" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Fecha</label>
                    <s:textfield name="entity.fecha" label="Fecha" class="fecha form-control" />
                </div>
                <div class="form-group">
                    <label>Asignatura</label>
                    <s:textfield name="entity.asignatura" label="Asignatura" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Id unidad academica</label>
                    <s:textfield name="entity.idUnidadAcademica" label="idUnidadAcademica" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Carrera</label>
                    <s:textfield name="entity.carrera" label="carrera" class="form-control" />
                </div>
                <div class="form-group">
                    <label>id comision</label>
                    <s:textfield name="entity.idComision" label="idComision" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Id materia</label>
                    <s:textfield name="entity.idMateria" label="idMateria" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Visado bedelia</label>
                    <s:checkbox name="entity.visadoBedelia" label="visadoBedelia" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Observaciones</label>
                    <s:textarea name="entity.observaciones" label="observaciones" class="form-control" />
                </div>
                <div class="form-group">
                    <s:submit value="Modificar" class="btn" />
                </div>
            </s:form>
        </div>
        <%@include file="partes/footer.jsp" %>
        <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
        <script>
            $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y H:m'});
        </script>
    </body>
</html>
