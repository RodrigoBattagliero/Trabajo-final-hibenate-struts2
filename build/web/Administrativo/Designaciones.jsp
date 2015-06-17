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
            
            <h1 class="page-header">Designaciones</h1>
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
                            <td>observaciones</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr id="duplicar">
                            <td><s:textfield name="numeroResolucion" label="Número de resolución" class="form-control" /></td>
                            <td><s:textfield name="categoria" label="Categoria" class="form-control" /></td>
                            <td><s:textfield name="desde" label="Desde" class="form-control fecha" /></td>
                            <td><s:textfield name="hasta" label="Hasta" class="form-control fecha" /></td>
                            <td><s:textfield name="dedicacion" label="Dedicacion" class="form-control" /></td>
                            <td><s:textarea name="observaciones" label="observaciones" class="form-control" /></td>
                        </tr>
                        <tr>
                            <td ><s:submit value="Guardar" class="btn" /></td>
                            <td ><button id="btnAgregar" class="btn">Agregar Elemento</button></td>
                        </tr>
                    </tbody>
                </table>
            </s:form>
            
            <%@include file="partes/footer.jsp" %>
            <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
            <script>
                $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y'});
            </script>
            <script>
                $(document).ready(function () 
                    {
                        $("#btnAgregar").click(function() 
                        {
                            var elementoNuevo = $("#duplicar").clone();
                            $("#duplicar").after(elementoNuevo);
                        });
                    });
            </script>
        </div>
    </body>
</html>
