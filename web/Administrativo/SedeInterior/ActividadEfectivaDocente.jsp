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
            <h1>Actividad docente</h1>
            <s:fielderror />
            <s:actionerror />
            <s:form action="ActividadDocentePrepared">
                <s:textfield name="entity.fecha" label="Fecha" class="fecha" />
                <s:textfield name="entity.asignatura" label="Asignatura" />
                <s:textfield name="entity.idUnidadAcademica" label="idUnidadAcademica" />
                <s:textfield name="entity.carrera" label="carrera" />
                <s:textfield name="entity.idComision" label="idComision" />
                <s:textfield name="entity.idMateria" label="idMateria" />
                <s:checkbox name="entity.visadoBedelia" label="visadoBedelia" />
                <s:textarea name="entity.observaciones" label="observaciones" />

                <s:submit value="Guardar" class="btn"/>
            </s:form>
            
            <s:include value="../partes/footer.jsp" />
        </div>
            
        <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
        <script>
            $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y H:m'});
        </script>
    </body>
</html>
