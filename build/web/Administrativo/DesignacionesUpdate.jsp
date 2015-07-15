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
            <s:form action="DesignacionUpdate" theme="simple" >
            <table class="table table-striped" > 
                <thead>
                    <tr>
                        <th>Número de Resolución</th>
                        <th>Categoria</th>
                        <th>Desde</th>
                        <th>Hasta</th>
                        <th>Id designación</th>
                        <th>Fecha de normas</th>
                        <th>Observaciones</th>
                        
                    </tr>
                </thead>
                <tbody>
                     <s:iterator value="update" var="designacion">
                            <tr>
                                <td>
                                    <s:hidden name="id" value="%{#designacion.id}" />
                                    <s:textfield name="numeroResolucion" value="%{#designacion.numeroResolucion}" label="Número de resolución" class="form-control" theme="simple" />
                                </td>
                                <td><s:textfield name="categoria" value="%{#designacion.categoria}" label="Categoria" class="form-control" theme="simple" /></td>
                                <td><s:textfield name="desde" value="%{#designacion.desde}" label="Desde" class="fecha form-control" theme="simple" /></td>
                                <td><s:textfield name="hasta" value="%{#designacion.hasta}" label="Hasta" class="fecha form-control" theme="simple" /></td>
                                <td><s:textfield name="idDesignacion" value="%{#designacion.idDesignacion}" label="Id designacion" class="form-control" theme="simple" /></td>
                                <td><s:textfield name="fecNorma" value="%{#designacion.fecNorma}" label="Comisión" class="form-control" theme="simple" /></td>
                                <td><s:textarea name="observaciones" value="%{#designacion.observaciones}" label="observaciones" class="form-control" theme="simple" /></td>
                                
                            </tr>
                        </s:iterator>
                        <!--<s:iterator value="entities" var="designacion">
                            <tr>
                                <td><s:textfield name="numeroResolucion" value="%{#designacion.numeroResolucion}" label="Número de resolución" class="form-control" theme="simple" /></td>
                                <td><s:textfield name="categoria" value="%{#designacion.categoria}" label="Categoria" class="form-control" theme="simple" /></td>
                                <td><s:textfield name="desde" value="%{#designacion.desde}" label="Desde" class="fecha form-control" theme="simple" /></td>
                                <td><s:textfield name="hasta" value="%{#designacion.hasta}" label="Hasta" class="fecha form-control" theme="simple" /></td>
                                <td><s:textfield name="idDesignacion" value="%{#designacion.idDesignacion}" label="Id designacion" class="form-control" theme="simple" /></td>
                                <td><s:textfield name="fecNorma" value="%{#designacion.fecNorma}" label="Comisión" class="form-control" theme="simple" /></td>
                                <td><s:textarea name="observaciones" value="%{#designacion.observaciones}" label="observaciones" class="form-control" theme="simple" /></td>
                                
                            </tr>
                        </s:iterator>-->
                </tbody>
                <tfooter>
                    <tr>
                        <td><s:submit value="Actualizar" class="btn btn-default" /></td>
                        <td><s:a action="LiquidacionesUpdateForm" class="btn btn-default">Continuar</s:a></td>
                    </tr>
                </tfooter>
            </table>
            </s:form>
            <!--
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
                                <td>Id designación</td>
                                <td>Fecha de norma</td>
                                <td>Observaciones</td>
                            </tr>
                        </thead>
                        <tbody>
                                <tr>
                                    <td><s:textfield name="entity.id" class="form-control" /></td>
                                    <td><s:textfield name="entity.numeroResolucion" class="form-control" /></td>
                                    <td><s:textfield name="entity.categoria" class="form-control" /></td>
                                    <td><s:textfield name="entity.desde" class="fecha form-control" /></td>
                                    <td><s:textfield name="entity.hasta" class="fecha form-control" /></td>
                                    <td><s:textfield name="idDesignacion" value="%{#designacion.idDesignacion}" label="Id designacion" class="form-control" /></td>
                                    <td><s:textfield name="fecNorma" value="%{#designacion.fecNorma}" label="Comisión" class="form-control" /></td>
                                    <td><s:textarea name="entity.observaciones" class="form-control" /></td>
                                </tr>
                                <tr>
                                    <td colspan="7"><s:submit value="Modificar" class="btn" /></td>
                                </tr>
                        </tbody>
                    </table>
                </s:form>
            </s:if>
            -->
            
            
        <%@include file="partes/footer.jsp" %>
        <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
        <script>
            $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y'});
        </script>
        </div>
    </body>
</html>