<%-- 
    Document   : ConfirmarSolicitud
    Created on : May 16, 2015, 7:00:09 AM
    Author     : rodrigo
--%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
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
            <ol class="breadcrumb">
                <li><a href="#">Inicio</a></li>
                <li><a href="#">Iniciar solicitud</a></li>
                <li class="active">Confirmar solicitud</li>
            </ol>
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-header">Confirmar Solicitud</h1>

                    <h2>Solicitud</h2>
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr class="success">
                                <th>N° de solicitud</th>
                                <th>Fecha de presentación</th>
                                <th>Sede</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><s:property value="#session.SolicitudesForm.numeroSolicitud" /></td>
                                <td><s:property value="#session.SolicitudesForm.fechaAlta" /></td>
                                <td><s:property value="#session.SolicitudesForm.Sedes.nombre" /></td>
                            </tr>
                        </tbody>
                    </table>

                    <h2>Docente</h2>
                    <hr />
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
                    <h2>Traslado</h2>
                    <hr />
                    <table class="table table-hover table-bordered">
                        <thead>
                            <tr class="success">
                                <th>Numero comprobante</th>
                                <th>Importe</th>
                                <th>Proveedor</th>
                                <th>Desde</th>
                                <th>Hasta</th>
                                <th>Fecha y hora salida</th>
                                <th>Observaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="#session.ComprobanteTraslado" var="tras">
                                <tr>
                                    <td><s:property value="#tras.comprobantes.numeroComprobante" /></td>
                                    <td><s:property value="#tras.comprobantes.importe" /></td>
                                    <td><s:property value="#tras.comprobantes.proveedor" /></td>
                                    <td><s:property value="#tras.desde" /></td>
                                    <td><s:property value="#tras.hasta" /></td>
                                    <td><s:property value="#tras.fechaHoraSalida" /></td>
                                    <td><s:property value="#tras.comprobantes.observaciones" /></td>
                                </tr>
                            </s:iterator>
                            <s:if test="#b == 0">
                                <tr>
                                    <td colspan="6">
                                        No hay datos.
                                    </td>
                                </tr>
                            </s:if>
                        </tbody>
                    </table>
                    <h2>Alojamiento / Comida / Combustible</h2>
                    <hr />
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr class="success">
                                <th>Numero comprobante</th>
                                <th>Importe</th>
                                <th>Proveedor</th>
                                <th>Tipo</th>
                                <th>Descripción</th>
                                <th>Observaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:set name="b" value="0" />
                            <s:iterator value="#session.ComprobanteAlojamiento" var="alo">
                                <s:set name="b" value="1" />
                                <tr>
                                    <td><s:property value="#alo.comprobantes.numeroComprobante" /></td>
                                    <td><s:property value="#alo.comprobantes.importe" /></td>
                                    <td><s:property value="#alo.comprobantes.proveedor" /></td>
                                    <td><s:property value="#alo.tipo" /></td>
                                    <td><s:property value="#alo.descripcion" /></td>
                                    <td><s:property value="#alo.comprobantes.observaciones" /></td>
                                </tr>
                            </s:iterator>
                            <s:if test="#b == 0">
                                <tr>
                                    <td colspan="6">
                                        No hay datos.
                                    </td>
                                </tr>
                            </s:if>
                        </tbody>
                    </table>

                    <s:a action="IniciarSolcitud" class="btn btn-primary">Guardar</s:a>
                    <s:a action="index" class="btn btn-danger">Cancelar</s:a>
                </div>
            </div>
            <%@include file="partes/footer.jsp" %>
        </div>
    </body>
</html>