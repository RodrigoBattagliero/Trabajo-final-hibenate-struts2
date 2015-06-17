<%-- 
    Document   : DesignacionesSolicitudForm
    Created on : May 21, 2015, 11:17:16 PM
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
            <s:actionerror />
            <s:fielderror />
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
                    <s:iterator value="entities" var="designacion">
                        <tr>
                            <td><s:property value="#designacion.numeroResolucion" /></td>
                            <td><s:property value="#designacion.categoria" /></td>
                            <td><s:property value="#designacion.desde" /></td>
                            <td><s:property value="#designacion.hasta" /></td>
                            <td><s:property value="#designacion.dedicacion" /></td>
                            <td><s:property value="#designacion.observaciones" /></td>
                            <td>
                                <s:url var="url" action="ActividadDocenteUpdateForm">
                                    <s:param name="idDesignacionSelected" value="#designacion.id"></s:param>
                                </s:url>
                                <s:a href="%{url}" >Editar Actividad docente</s:a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
            <s:if test="entity.id" >
                <s:form action="DesignacionUpdate" theme="simple" >
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <td>Id</td>
                                <td>Número Resolución</td>
                                <td>Categoria</td>
                                <td>Desde</td>
                                <td>Hasta</td>
                                <td>Dedicación</td>
                                <td>Observaciones</td>
                            </tr>
                        </thead>
                        <tbody>
                                <tr>
                                    <td><s:textfield name="entity.id" class="form-control"  /></td>
                                    <td><s:textfield name="entity.numeroResolucion" class="form-control" /></td>
                                    <td><s:textfield name="entity.categoria" class="form-control" /></td>
                                    <td><s:textfield name="entity.desde" class="fecha form-control" /></td>
                                    <td><s:textfield name="entity.hasta" class="fecha form-control" /></td>
                                    <td><s:textfield name="entity.dedicacion" class="form-control" /></td>
                                    <td><s:textarea name="entity.observaciones" class="form-control" /></td>
                                </tr>
                                <tr>
                                    <td colspan="7"><s:submit value="Modificar" class="btn" /></td>
                                </tr>
                        </tbody>
                    </table>
                </s:form>
                <hr />
            </s:if>
                
            <%@include file="partes/footer.jsp" %>
        </div>
            
        <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
        <script>
            $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y H:m'});
        </script>
    </body>
</html>