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
        <title>JSP Page</title>
        <style>
            input{
                width: 150px;
            }

            body{
                width: 500px;
                margin:0px auto;
            }
            table thead th {
                background-color: #999999
            }
            table tbody tr:nth-child(odd){
                background-color: #ccc;
            }
            table tbody tr:hover{
                background-color: #DEDEDE;
            }
        </style>
    </head>
    <body>
        <h1>Detalle</h1>
        <hr />
        <!-- Solicitud -->
        <h2>Solicitud</h2>
        <hr />
        <table>
            <thead>
                <tr>
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
        <h2>Registro unico</h2>
        <hr />
        <table>
            <thead>
                <th>Fecha entrada</th>
                <th>Fecha salida</th>
                <th>Area</th>
                <th>Estado</th>
                <th>Confirmado</th>
                <th>Observaciones</th>
            </thead>
            <tbody>
                <s:iterator value="#session.RegistroUnico" var="reg">
                    <tr>
                        <td><s:property value="#reg.fechaEntrada" /></td>
                        <td><s:property value="#reg.fechaSalida" /></td>
                        <td><s:property value="#reg.areas.nombre" /></td>
                        <td><s:property value="#reg.estados.nombre" /></td>
                        <td><s:property value="#reg.confirmado" /></td>
                        <td><s:property value="#reg.observaciones" /></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
        <!-- Docente -->
        <h2>Docente</h2>
        <hr />
        <table>
            <thead>
                <tr>
                    <th>Apellido y nombre</th>
                    <th>Depto academico</th>
                    <th>DNI</th>
                    <th>Telefono</th>
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
                    <td><s:property value="#session.Docentes[0].apellido" />, <s:property value="#session.Docentes[0].nombre" /></td>
                    <td><s:property value="#session.Docentes[0].departamentosAcademicos.nombre" /></td>
                    <td><s:property value="#session.Docentes[0].dni" /></td>
                    <td><s:property value="#session.Docentes[0].telefono" /></td>
                    <td><s:property value="#session.Docentes[0].email" /></td>
                    <td><s:property value="#session.Docentes[0].lugarResidencia" /></td>
                    <td><s:property value="#session.Docentes[0].motivoComision" /></td>
                    <td><s:property value="#session.Docentes[0].fechaInicio" /></td>
                    <td><s:property value="#session.Docentes[0].fechaFinalizacion" /></td>
                    <td><s:property value="#session.Docentes[0].observaciones" /></td>
                </tr>
            </tbody>
        </table>
        <!-- Traslado -->
        <h2>Traslado</h2>
        <hr />
        <table>
            <thead>
                <th>Numero comprobante</th>
                <th>Importe</th>
                <th>Proveedor</th>
                <th>Desde</th>
                <th>Hasta</th>
                <th>Fecha y hora salida</th>
                <th>Fecha y hora de regreso</th>
                <th>Observaciones</th>
            </thead>
            <tbody>
                <s:iterator value="#session.Traslados" var="tras">
                    <tr>
                        <td><s:property value="#tras.comprobantes.numeroComprobante" /></td>
                        <td><s:property value="#tras.comprobantes.importe" /></td>
                        <td><s:property value="#tras.comprobantes.proveedor" /></td>
                        <td><s:property value="#tras.desde" /></td>
                        <td><s:property value="#tras.hasta" /></td>
                        <td><s:property value="#tras.fechaHoraSalida" /></td>
                        <td><s:property value="#tras.fechaHoraRegreso" /></td>
                        <td><s:property value="#tras.comprobantes.observaciones" /></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
        <!-- Alojamiento -->
        <h2>Alojamiento</h2>
        <hr />
        <table>
            <thead>
                <th>Numero comprobante</th>
                <th>Importe</th>
                <th>Proveedor</th>
                <th>Tipo</th>
                <th>Descripción</th>
                <th>Observaciones</th>
            </thead>
            <tbody>
                <s:iterator value="#session.Alojamientos" var="alo">
                <a href="index.jsp"></a>
                    <tr>
                        <td><s:property value="#alo.comprobantes.numeroComprobante" /></td>
                        <td><s:property value="#alo.comprobantes.importe" /></td>
                        <td><s:property value="#alo.comprobantes.proveedor" /></td>
                        <td><s:property value="#alo.tipo" /></td>
                        <td><s:property value="#alo.descripcion" /></td>
                        <td><s:property value="#alo.comprobantes.observaciones" /></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
        <!-- Designaciones -->
        <h2>Desiganciones</h2>
        <hr />
        <table>
            <thead>
                <th>N° resolución</th>
                <th>Categoría</th>
                <th>Desde</th>
                <th>Hasta</th>
                <th>Dedicación</th>
                <th>Observaciones</th>
            </thead>
            <tbody>
                <s:iterator value="#session.Designaciones" var="des">
                    <tr>
                        <td><s:property value="#des.numeroResolucion" /></td>
                        <td><s:property value="#des.categoria" /></td>
                        <td><s:property value="#des.desde" /></td>
                        <td><s:property value="#des.hasta" /></td>
                        <td><s:property value="#des.dedicacion" /></td>
                        <td><s:property value="#des.descripcion" /></td>
                    </tr>
                    <s:iterator value="#des.actividadDocenteses" var="act" >
                        <tr>
                    <table>
                        <thead>
                            <th>Fecha</th>
                            <th>Asignatura</th>
                            <th>Unidad academica</th>
                            <th>Materia</th>
                            <th>Carrera</th>
                            <th>Comision</th>
                            <th>Visado bedelia</th>
                            <th>Observaciones</th>
                        </thead>
                        <tbody>
                            <tr>
                                <td><s:property value="#act.fecha" /></td>
                                <td><s:property value="#act.asignatura" /></td>
                                <td><s:property value="#act.idUnidadAcademica" /></td>
                                <td><s:property value="#act.idMateria" /></td>
                                <td><s:property value="#act.carrera" /></td>
                                <td><s:property value="#act.idComision" /></td>
                                <td><s:property value="#act.visadoBedelia" /></td>
                                <td><s:property value="#act.observaciones" /></td>
                            </tr>
                        </tbody>
                    </table>
                        </tr>
                    </s:iterator>
                </s:iterator>
            </tbody>
        </table>
        <!-- Liquidaciones -->
        <h2>Liquidaciones</h2>
        <hr />
        <table>
            <thead>
                <tr>
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
                <tr>
                    <td><s:property value="#session.Liquidaciones[0].reconocimientoGastoComida" /></td>
                    <td><s:property value="#session.Liquidaciones[0].reconocimientoGastoAlojamiento.nombre" /></td>
                    <td><s:property value="#session.Liquidaciones[0].reconocimientoGastoCombustible" /></td>
                    <td><s:property value="#session.Liquidaciones[0].reconocimientoGastoPasajes" /></td>
                    <td><s:property value="#session.Liquidaciones[0].importeDeclarado" /></td>
                    <td><s:property value="#session.Liquidaciones[0].reconocimientoImporteTotal" /></td>
                    <td><s:property value="#session.Liquidaciones[0].observaciones" /></td>
                </tr>
            </tbody>
        </table>
        <!--  -->
    </body>
</html>

