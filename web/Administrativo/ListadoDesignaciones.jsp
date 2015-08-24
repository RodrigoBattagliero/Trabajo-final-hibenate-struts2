<%-- 
    Document   : ListadoDesignaciones
    Created on : May 21, 2015, 10:09:40 PM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <title>Administrativo</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <div class="row">
               <div class="col-sm-12">
                <h1 class="page-header">Designaciones</h1>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <td>Número Resolución</td>
                            <td>Categoria</td>
                            <td>Desde</td>
                            <td>Hasta</td>
                            <td>Dedicación</td>
                            <td>Observaciones</td>
                            <td>Acciones</td>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="entities" var="list">
                            <tr>
                                <td><s:property value="#list.numeroResolucion" /></td>
                                <td><s:property value="#list.categoria" /></td>
                                <td><s:property value="#list.desde" /></td>
                                <td><s:property value="#list.hasta" /></td>
                                <td><s:property value="#list.dedicacion" /></td>
                                <td><s:property value="#list.observaciones" /></td>
                                <td><s:a action="" class="btn">Agregar datos de Actividad</s:a></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                </div>
            </div>
            
            <%@include file="partes/footer.jsp" %>
        </div>
    </body>
</html>
