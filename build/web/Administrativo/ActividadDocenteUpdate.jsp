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
        <title>Administrativo</title>
        
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <h1 class="page-header">Modificar actividad docente</h1>
            <s:fielderror />
            <s:actionerror />
            <div class="row">
               <div class="col-sm-2"></div>
               <div class="col-sm-8">
                <s:form action="ActividadDocenteUpdate">
                    <div class="form-group">
                        <label>ID</label>
                        <s:textfield name="entity.id" label="Id" disabled="true" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Fecha</label>
                        <s:textfield name="entity.fecha" label="Fecha" class="form-control fecha" />
                    </div>
                    <div class="form-group">
                        <label>Asignatura</label>
                        <s:textfield name="entity.asignatura" label="Asignatura" class="form-control" />
            </div>
                    <div class="form-group">
                        <label>Unidad académica</label>
                        <s:textfield name="entity.idUnidadAcademica" label="idUnidadAcademica" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Carrera</label>
                        <s:textfield name="entity.carrera" label="carrera" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>ID Comisión</label>
                        <s:textfield name="entity.idComision" label="idComision" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>ID Materia</label>
                        <s:textfield name="entity.idMateria" label="idMateria" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Visado bedelía</label>
                        <s:checkbox name="entity.visadoBedelia" label="visadoBedelia" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>Observaciones</label>
                        <s:textarea name="entity.observaciones" label="observaciones" class="form-control" />
                    </div>
                    <div class="form-group">
                        <s:submit value="Modificar" />
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
