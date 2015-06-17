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
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <h1 class="page-header">Actividad docente</h1>
            <s:fielderror />
            <s:actionerror />
            <s:form action="ActividadDocentePrepared" theme="simple">
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
                    <s:submit value="Guardar" class="btn"/>
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
