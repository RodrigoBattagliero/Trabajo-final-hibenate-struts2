<%-- 
    Document   : ConsultarDetalle
    Created on : 04-Jun-2015, 17:56:21
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
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
                <li><a href="#">Consultar solicitudes</a></li>
                <li class="active">Detalle</li>
            </ol>
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-header">Detalle</h1>
                        <s:set var="numero" value="1"></s:set>
                        <s:iterator value="#session.grafico" var="area" >
                            <a href="#"  data-toggle="tooltip" data-placement="top" title="<s:property value="#area[0]"></s:property>">
                                    <div class="estado" style="background: <s:property value="#area[1]"></s:property>">
                                        <s:property value="#numero"></s:property>
                                    </div>
                            </a>
                            <s:set var="numero" value="%{#numero + 1}"></s:set>
                        </s:iterator>
                    <h4>Solicitud</h4>
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
                                <td><s:property value="#session.Solicitudes.numeroSolicitud" /></td>
                                <td><s:property value="#session.Solicitudes.fechaAlta" /></td>
                                <td><s:property value="#session.Sedes.nombre" /></td>
                            </tr>
                        </tbody>
                    </table>
                    <!-- Registro unico -->
                    <h4>Registro unico</h4>
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr class="success">
                                <th>Fecha entrada</th>
                                <th>Fecha salida</th>
                                <th>Area</th>
                                <th>Estado</th>
                                <th>Confirmado</th>
                                <th>Observaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="#session.RegistroUnico" var="reg">
                                <s:if test="#reg.estados.id != 9">
                                <tr>
                                    <td><s:property value="#reg.fechaEntrada" /></td>
                                    <td><s:property value="#reg.fechaSalida" /></td>
                                    <td><s:property value="#reg.areas.nombre" /></td>
                                    <td><s:property value="#reg.estados.nombre" /></td>
                                    <td><s:property value="#reg.confirmado" /></td>
                                    <td><s:property value="#reg.observaciones" /></td>
                                </tr>
                                </s:if>
                            </s:iterator>
                        </tbody>
                    </table>
                    <!-- Docente -->
                    <h4>Docente</h4>
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr class="success">
                                <th>Apellido y nombre</th>
                                <th>DNI</th>
                                <th>Tel fijo</th>
                                <th>Tel cel</th>
                                <th>Email</th>
                                <th>Lugar de residencia</th>
                                <th>Motivo de comisión</th>
                                <th>Fecha de inicio</th>
                                <th>Fecha de finalización</th>
                                <th>Observaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:if test="#session.Docentes[0]">
                            <tr>
                                <td><s:property value="#session.Docentes[0].apellido" />, <s:property value="#session.Docentes[0].nombre" /></td>
                                <td><s:property value="#session.Docentes[0].dni" /></td>
                                <td><s:property value="#session.Docentes[0].telefonoFijo" /></td>
                                <td><s:property value="#session.Docentes[0].telefonoCelular" /></td>
                                <td><s:property value="#session.Docentes[0].email" /></td>
                                <td><s:property value="#session.Docentes[0].lugarResidencia" /></td>
                                <td><s:property value="#session.Docentes[0].motivoComision" /></td>
                                <td><s:property value="#session.Docentes[0].fechaInicio" /></td>
                                <td><s:property value="#session.Docentes[0].fechaFinalizacion" /></td>
                                <td><s:property value="#session.Docentes[0].observaciones" /></td>
                            </tr>
                            </s:if>
                            <s:else>
                                <tr>
                                    <td colspan="10">No hay datos</td>
                                </tr>
                            </s:else>
                        </tbody>
                    </table>
                    <!-- Traslado -->
                    <h4>Traslado</h4>
                    <table class="table table-bordered table-hover">
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
                            <s:set name="b" value="0" />
                            <s:iterator value="#session.Traslados" var="tras">
                                <s:set name="b" value="1" />
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
                                    <td colspan="8">
                                        No hay datos.
                                    </td>
                                </tr>
                            </s:if>
                        </tbody>
                    </table>
                    <!-- Alojamiento -->
                    <h4>Alojamiento</h4>
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
                            <s:iterator value="#session.Alojamientos" var="alo">
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
                    <!-- Designaciones -->
                    <h4>Desiganciones</h4>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr >
                                <th>N° resolución</th>
                                <th>Categoría</th>
                                <th>Desde</th>
                                <th>Hasta</th>
                                <th>Observaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:set name="b" value="0" />
                            <s:iterator value="#session.Designaciones" var="des">
                                <s:set name="b" value="1" />
                                <tr class="success">
                                    <td><s:property value="#des.numeroResolucion" /></td>
                                    <td><s:property value="#des.categoria" /></td>
                                    <td><s:property value="#des.desde" /></td>
                                    <td><s:property value="#des.hasta" /></td>
                                    <td><s:property value="#des.descripcion" /></td>
                                </tr>
                                <s:set name="b1" value="0" />
                                <s:iterator value="#des.actividadDocenteses" var="act" >
                                    <s:set name="b1" value="1" />
                                    <tr>
                                        <td colspan="7">
                                            <table class="table table-bordered">
                                                <thead>
                                                    <th>Fecha</th>
                                                    <th>Asignatura</th>
                                                    <th>Unidad academica</th>
                                                    <th>Carrera</th>
                                                    <th>Comision</th>
                                                    <th>Plan</th>
                                                    <th>Observaciones</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td><s:property value="#act.fecha" /></td>
                                                        <td><s:property value="#act.nombreMateria" /></td>
                                                        <td><s:property value="#act.nombreUnidadAcademica" /></td>
                                                        <td><s:property value="#act.nombreCarrera" /></td>
                                                        <td><s:property value="#act.comision" /></td>
                                                        <td><s:property value="#act.plan" /></td>
                                                        <td><s:property value="#act.observaciones" /></td>
                                                    </tr>
                                                    <s:if test="#b1 == 0">
                                                        <tr>
                                                            <td colspan="7">
                                                                No hay datos.
                                                            </td>
                                                        </tr>
                                                    </s:if>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                </s:iterator>
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
                    <!-- Liquidaciones -->
                    <h4>Liquidaciones</h4>
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr class="success">
                                <th>R. gasto comida</th>
                                <th>R. gasto alojamiento</th>
                                <th>R. gasto combustible</th>
                                <th>R. gasto pasajes</th>
                                <th>Importe declarado</th>
                                <th>R. importe total</th>
                                <th>Observaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:if test="#session.Liquidaciones[0]">
                            <tr>
                                <td><s:property value="#session.Liquidaciones[0].reconocimientoGastoComida" /></td>
                                <td><s:property value="#session.Liquidaciones[0].reconocimientoGastoAlojamiento.nombre" /></td>
                                <td><s:property value="#session.Liquidaciones[0].reconocimientoGastoCombustible" /></td>
                                <td><s:property value="#session.Liquidaciones[0].reconocimientoGastoPasajes" /></td>
                                <td><s:property value="#session.Liquidaciones[0].importeDeclarado" /></td>
                                <td><s:property value="#session.Liquidaciones[0].reconocimientoImporteTotal" /></td>
                                <td><s:property value="#session.Liquidaciones[0].observaciones" /></td>
                            </tr>
                            </s:if>
                            <s:else>
                                <tr>
                                    <td colspan="7">No hay datos</td>
                                </tr>
                            </s:else>

                        </tbody>
                    </table>
                </div>
            </div>
            <%@include file="partes/footer.jsp" %>
            <script>
                $(function () {
                    $('[data-toggle="tooltip"]').tooltip()
                })
            </script>
        </div>
    </body>
</html>