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
        <link rel="stylesheet" type="text/css" href="../../js/datetimepicker-master/jquery.datetimepicker.css"/>
        <link rel="stylesheet" type="text/css" href="../../bootstrap/css/bootstrap.css" />
        <title>Administrativo</title>
    </head>
    <body>
        <div class="container">
            <s:include value="../partes/menu.jsp" />
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
            <s:form action="setActDoc" theme="simple">
                    <s:iterator value="entities" var="designacion">
                        <div class="table-responsive">
                            
                        
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Número Resolución</th>
                                    <th>Categoria</th>
                                    <th>Desde</th>
                                    <th>Hasta</th>
                                    <th>Id designacion</th>
                                    <th>Fecha de norma</th>
                                    <th>Observaciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="success">
                                    <td><s:property value="#designacion.numeroResolucion" /></td>
                                    <td><s:property value="#designacion.categoria" /></td>
                                    <td><s:property value="#designacion.desde" /></td>
                                    <td><s:property value="#designacion.hasta" /></td>
                                    <td><s:property value="#designacion.idDesignacion" /></td>
                                    <td><s:property value="#designacion.fecNorma" /></td>
                                    <td><s:property value="#designacion.observaciones" /></td>
                                </tr>

                                <tr>
                                    <td colspan="7">
                                   <table class="table table-hover">
                                                <thead>
                                                    <th>Unidad academica</th>
                                                    <th>Carrera</th>
                                                    <th width="130">Plan</th>
                                                    <th>Materia</th>
                                                    <th width="130">fecha</th>
                                                    <th>Seleccionar</th>
                                                </thead>
                                                <tbody>
                                                    <s:iterator value="#designacion.actividadDocenteses" var="act">
                                                   
                                                            <tr>
                                                                <td>
                                                                    <s:hidden name="idDesignacion" value="%{#designacion.id}"/>
                                                                    <s:hidden name="unidadAcademica" value="%{#act.unidadAcademica}" />
                                                                    <s:hidden name="carrera" value="%{#act.carrera}"/>
                                                                    <s:hidden name="materia" value="%{#act.materia}"/>
                                                                    <s:hidden name="comision" value="%{#act.comision}" readonly="true" theme="simple" class="form-control" /> 
                                                                    <s:textfield name="nombreUnidadAcademica" value="%{#act.nombreUnidadAcademica}" readonly="true" theme="simple" class="form-control" /> 
                                                                </td>
                                                                <td><s:textfield name="nombreCarrera" value="%{#act.nombreCarrera}" readonly="true" theme="simple" class="form-control" /></td>
                                                                <td><s:textfield name="plan" value="%{#act.plan}" readonly="true"  theme="simple" class="form-control" /> </td>
                                                                <td><s:textfield name="nombreMateria" value="%{#act.nombreMateria}" readonly="true"  theme="simple" class="form-control" />  </td>
                                                                <td><s:textfield name="fecha" theme="simple" class="form-control fecha" /> </td>
                                                                <td><s:select list="#@java.util.TreeMap@{'no':'No','si':'Si'}" name="visadoBedelia" theme="simple" class="form-control" /></td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="6"><input type="text" name="observaciones" class="form-control" placeholder="Observaciones" ></td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="6"></td>
                                                            </tr>
                                                    </s:iterator>
                                                </tbody>
                                            </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        </div>
                    </s:iterator>
                <s:submit value="Guardar" class="btn btn-primary" />
                    </s:form>
            
            <%@include file="../partes/footer.jsp" %>
        </div>
        <script src="../../js/datetimepicker-master/jquery.datetimepicker.js"></script>
        <script>
            $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y'});
        </script>
    </body>
</html>
