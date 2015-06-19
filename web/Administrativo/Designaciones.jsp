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
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <s:fielderror />
            <s:actionerror />
            <s:form action="designacionPrepared" theme="simple">
            <table class="table table-striped">
                    <thead>
                        <tr>
                            <td>Número de resolución</td>
                            <td>Categoria</td>
                            <td>Desde</td>
                            <td>Hasta</td>
                            <td>Dedicacion</td>
                            <td>Comisión</td>
                            <td>observaciones</td>
                            <td>Seleccionar</td>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="entities" var="designacion">
                            <tr>
                                <td><s:textfield name="numeroResolucion" value="%{#designacion.numeroResolucion}" label="Número de resolución" class="form-control" /></td>
                            <td><s:textfield name="categoria" value="%{#designacion.categoria}" label="Categoria" class="form-control" /></td>
                            <td><s:textfield name="desde" value="%{#designacion.desde}" label="Desde" class="fecha form-control"  /></td>
                            <td><s:textfield name="hasta" value="%{#designacion.hasta}" label="Hasta" class="fecha form-control"  /></td>
                            <td><s:textfield name="dedicacion" value="%{#designacion.dedicacion}" label="Dedicacion" class="form-control" /></td>
                            <td><s:textfield name="idComision" value="%{#designacion.idComision}" label="Comisión" class="form-control" /></td>
                            <td><s:textarea name="observaciones" value="%{#designacion.observaciones}" label="observaciones" class="form-control" /></td>
                            <td><s:select list="{'no','si'}" name="seleccionado" /></td>
                            </tr>
                        </s:iterator>
                            <tr>
                                <td><s:submit value="Guardar" class="btn"/></td>
                            </tr>
                    </tbody>
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
