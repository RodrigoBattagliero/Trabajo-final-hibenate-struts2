<%-- 
    Document   : datosACompletar
    Created on : May 19, 2015, 5:11:41 PM
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
                <li><a href="#">Solicitudes</a></li>
                <li><a href="#">Solicitudes a completar</a></li>
                <li class="active">Designaciones</li>
            </ol>
            <h1 class="page-header">Designaciones</h1>
            <s:fielderror />
            <s:actionerror />
            <s:form action="designacionPrepared" theme="simple">
            <table class="table table-bordered table-hover table-striped">
                    <thead>
                        <tr class="success">
                            <th>Número de resolución</th>
                            <th>Categoria</th>
                            <th>Desde</th>
                            <th>Hasta</th>
                            <th>Id designación</th>
                            <th>Fecha de norma</th>
                            <th>observaciones</th>
                            <th>Seleccionar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="entities" var="designacion">
                            <tr>
                                <td><s:textfield name="numeroResolucion" value="%{#designacion.numeroResolucion}" label="Número de resolución" class="form-control" /></td>
                            <td><s:textfield name="categoria" value="%{#designacion.categoria}" label="Categoria" class="form-control" /></td>
                            <td><s:textfield name="desde" value="%{#designacion.desde}" label="Desde" class="fecha form-control"  /></td>
                            <td><s:textfield name="hasta" value="%{#designacion.hasta}" label="Hasta" class="fecha form-control"  /></td>
                            <td><s:textfield name="idDesignacion" value="%{#designacion.idDesignacion}" label="Id designacion" class="form-control" /></td>
                            <td><s:textfield name="fecNorma" value="%{#designacion.fecNorma}" label="Comisión" class="form-control" /></td>
                            <td><s:textarea name="observaciones" value="%{#designacion.observaciones}" label="observaciones" class="form-control" /></td>
                            <td><s:select list="{'no','si'}" name="seleccionado" /></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="8"><s:submit value="Guardar" class="btn btn-primary"/></td>
                        </tr>
                    </tfoot>
                </table>
            </s:form>
            
             
            <script src="../js/jquery-1.11.3.min.js"></script>
            <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
            <script>
                $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y'});
            </script>
          
        </div>
    </body>
</html>
