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
        <title>Administrativo</title>
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
            <table class="table table-bordered  table-hover">
                <thead>
                    <tr class="success">
                        <th>Apellido y nombre</th>
                        <th>DNI</th>
                        <th>Telefono fijo</th>
                        <th>Telefono celular</th>
                        <th>Email</th>
                        <th>Lugar de residencia</th>
                        <th>Motivo de comisión</th>
                        <th>Fecha de inicio</th>
                        <th>Fecha de finalización</th>
                        <th>Observaciones</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><s:property value="#session.DocentesForm.apellido" />, <s:property value="#session.DocentesForm.nombre" /></td>
                        <td><s:property value="#session.DocentesForm.dni" /></td>
                        <td><s:property value="#session.DocentesForm.telefonoFijo" /></td>
                        <td><s:property value="#session.DocentesForm.telefonoCelular" /></td>
                        <td><s:property value="#session.DocentesForm.email" /></td>
                        <td><s:property value="#session.DocentesForm.lugarResidencia" /></td>
                        <td><s:property value="#session.DocentesForm.motivoComision" /></td>
                        <td><s:property value="#session.DocentesForm.fechaInicio" /></td>
                        <td><s:property value="#session.DocentesForm.fechaFinalizacion" /></td>
                        <td><s:property value="#session.DocentesForm.observaciones" /></td>
                    </tr>
                </tbody>
            </table>
            <s:fielderror />
            <s:actionerror />
            <div class="row">
               <div class="col-sm-12">
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
                                <td><s:textfield name="numeroResolucion" value="%{#designacion.numeroResolucion}" label="Número de resolución" class="form-control" readonly="true" /></td>
                                <td><s:textfield name="categoria" value="%{#designacion.categoria}" label="Categoria" class="form-control" readonly="true" /></td>
                                <td><s:textfield name="desde" value="%{#designacion.desde}" label="Desde" class="fecha form-control" readonly="true" /></td>
                                <td><s:textfield name="hasta" value="%{#designacion.hasta}" label="Hasta" class="fecha form-control" readonly="true" /></td>
                                <td><s:textfield name="idDesignacion" value="%{#designacion.idDesignacion}" label="Id designacion" class="form-control" readonly="true" /></td>
                                <td><s:textfield name="fecNorma" value="%{#designacion.fecNorma}" label="Comisión" class="form-control" readonly="true" /></td>
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
                </div>
            </div>
             
            <s:include value="partes/footer.jsp" />
            <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
            <script>
                $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y'});
            </script>
          
        </div>
    </body>
</html>
