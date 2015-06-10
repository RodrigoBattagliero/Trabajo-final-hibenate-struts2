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
        <script src="../js/jquery-1.11.3.min.js"></script>
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
                            <td>observaciones</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr id="duplicar">
                            <td><s:textfield name="numeroResolucion" label="Número de resolución" /></td>
                            <td><s:textfield name="categoria" label="Categoria" /></td>
                            <td><s:textfield name="desde" label="Desde" /></td>
                            <td><s:textfield name="hasta" label="Hasta" /></td>
                            <td><s:textfield name="dedicacion" label="Dedicacion" /></td>
                            <td><s:textarea name="observaciones" label="observaciones" /></td>
                        </tr>
                    </tbody>
                </table>

                <s:submit value="Guardar" />
            </s:form>
             <button id="btnAgregar">Agregar Elemento</button>
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
