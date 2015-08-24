<%-- 
    Document   : TrasladoIniciarSolicitud
    Created on : May 16, 2015, 6:21:18 AM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrativo</title>
        <link rel="stylesheet" type="text/css" href="../js/datetimepicker-master/jquery.datetimepicker.css"/>
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <ol class="breadcrumb">
                <li><a href="#">Inicio</a></li>
                <li><a href="#">Iniciar solicitud</a></li>
                <li class="active">Datos de traslado</li>
            </ol>
            <div class="row">
            <div class="col-sm-12">
            <h1 class="page-header">Datos de traslado</h1>
                <s:a action="AlojamientoForm" class="btn">Agregar datos de alojamiento/combustible</s:a>
                    <hr />
                    <div class="table-responsive">
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
                    </div>
                   <s:fielderror />
                   <s:actionerror />
                   <s:form action="DatosTrasladoPreparar" theme="simple">
                       <div class="table-responsive">
                       <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Importe</th>
                                    <th>Número de comprobante</th>
                                    <th>Proveedor</th>
                                    <th>Desde (Origen)</th>
                                    <th>Hasta (Destino)</th>
                                    <th style="width: 200px">Fecha y hora de salida</th>
                                    <th>Observaciones</th>
                                </tr>
                             </thead>
                            <tbody id="datos">
                                <s:if test="#session.ComprobanteTraslado">
                                    <s:iterator value="#session.ComprobanteTraslado" var="liq">
                                        <tr>
                                            <td><s:textfield name="trasladoComprobantesImporte" value="%{#liq.comprobantes.importe}" class="form-control" theme="simple"></s:textfield></td>
                                            <td><s:textfield name="trasadoComprobantesNumeroComprobante" value="%{#liq.comprobantes.numeroComprobante}" class="form-control" theme="simple"></s:textfield></td>
                                            <td><s:textfield name="trasladoComprobantesProveedor" value="%{#liq.comprobantes.proveedor}" class="form-control" theme="simple"></s:textfield></td>
                                            <td><s:textfield name="trasladoDesde" value="%{#liq.desde}" class="form-control" theme="simple"></s:textfield></td>
                                            <td><s:textfield name="trasladoHasta" value="%{#liq.hasta}" class="form-control" theme="simple"></s:textfield></td>
                                            <td><s:textfield name="trasladoFechaHoraSalida" value="%{#liq.fechaHoraSalida}" class="form-control fecha" theme="simple"></s:textfield></td>
                                            <td><s:textfield name="trasladoComprobantesObservaciones" value="%{#liq.comprobantes.observaciones}" class="form-control" theme="simple"></s:textfield></td>
                                        </tr>
                                    </s:iterator>
                                </s:if>
                            </tbody>
                            <tfoot>
                                <s:if test="#session.ComprobanteTraslado">
                                    <tr>
                                        <td><button type="button" id="btnAgregar" class="btn btn-success">Agregar fila</button></td>
                                        <td><button type="button" id="btnEliminar" class="btn btn-danger">Eliminar último fila</button></td>
                                        <td colspan="2"><s:a action="RegistroUnicoForm" class="btn btn-warning">Continuar sin agregar datos de traslado</s:a></td>
                                        <td><s:submit value="Guardar" id="botonGuardar" class="btn btn-primary" /></td>
                                    </tr>
                                </s:if>
                                <s:else>
                                    <tr>
                                        <td><button type="button" id="btnAgregar" class="btn btn-success">Agregar fila</button></td>
                                        <td><button type="button" id="btnEliminar" class="btn btn-danger" style="display:none">Eliminar último fila</button></td>
                                        <td colspan="2"><s:a action="RegistroUnicoForm" class="btn btn-warning">Continuar sin agregar datos de traslado</s:a></td>
                                        <td><s:submit value="Guardar" id="botonGuardar" class="btn btn-primary" cssStyle="display:none" /></td>
                                    </tr>
                                </s:else>
                            </tfoot>
                     </table>
                    </div>
                </s:form>
                </div>
                </div>
                <%@include file="partes/footer.jsp" %>
        </div>
        <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
        <script>
            $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y H:m'});
        </script>
        <script>
            
            $(document).ready(function () 
                {  
                    /*
                    $("#btnAgregar").on("click", function(){   
                        var tr = '<tr class="fila" ><td><div class="input-group"><div class="input-group-addon">$</div><input type="text" name="trasladoComprobantesImporte" value="" id="DatosTrasladoPreparar_trasladoComprobantesImporte" class="form-control"/></div></td><td><input type="text" name="trasadoComprobantesNumeroComprobante" value="" id="DatosTrasladoPreparar_trasadoComprobantesNumeroComprobante" class="form-control" /></td><td><input type="text" name="trasladoComprobantesProveedor" value="" id="DatosTrasladoPreparar_trasladoComprobantesProveedor" class="form-control" /></td><td><input type="text" name="trasladoDesde" value="" id="DatosTrasladoPreparar_trasladoDesde" class="form-control" /></td><td><input type="text" name="trasladoHasta" value="" id="DatosTrasladoPreparar_trasladoHasta" class="form-control" /></td><td><input type="text" name="trasladoFechaHoraSalida" value="" id="DatosTrasladoPreparar_trasladoFechaHoraSalida" class="form-control fecha" /></td><!--<td>--><input type="hidden" name="trasladofechaHoraRegreso" value="00/00/0000 00:00" id="DatosTrasladoPreparar_trasladofechaHoraRegreso" class="form-control fecha" /><!--</td>--><td><textarea name="trasladoComprobantesObservaciones" cols="" rows="" id="DatosTrasladoPreparar_trasladoComprobantesObservaciones" class="form-control" ></textarea></td></tr>';
                        $("#datos").append(tr);
                        $("#botonGuardar").css("display","block");
                        $("#btnEliminar").css("display","block");
                        $("#datos").
                    });
                    */
                    $("#btnAgregar").click(function() 
                    {   
                        var tr = '<tr class="fila" ><td><div class="input-group"><div class="input-group-addon">$</div><input type="text" name="trasladoComprobantesImporte" value="" id="DatosTrasladoPreparar_trasladoComprobantesImporte" class="form-control"/></div></td><td><input type="text" name="trasadoComprobantesNumeroComprobante" value="" id="DatosTrasladoPreparar_trasadoComprobantesNumeroComprobante" class="form-control" /></td><td><input type="text" name="trasladoComprobantesProveedor" value="" id="DatosTrasladoPreparar_trasladoComprobantesProveedor" class="form-control" /></td><td><input type="text" name="trasladoDesde" value="" id="DatosTrasladoPreparar_trasladoDesde" class="form-control" /></td><td><input type="text" name="trasladoHasta" value="" id="DatosTrasladoPreparar_trasladoHasta" class="form-control" /></td><td><input type="text" name="trasladoFechaHoraSalida" value="" id="DatosTrasladoPreparar_trasladoFechaHoraSalida" class="form-control fecha" /></td><!--<td>--><input type="hidden" name="trasladofechaHoraRegreso" value="00/00/0000 00:00" id="DatosTrasladoPreparar_trasladofechaHoraRegreso" class="form-control fecha" /><!--</td>--><td><textarea name="trasladoComprobantesObservaciones" cols="" rows="" id="DatosTrasladoPreparar_trasladoComprobantesObservaciones" class="form-control" ></textarea></td></tr>';
                        $("#datos").append(tr);
                        $("#botonGuardar").css("display","block");
                        $("#btnEliminar").css("display","block");
                    });
                    $("#btnEliminar").click(function()
                    {
                        $(".fila:last-child").remove();
                        var cantidad = $(".fila").size();
                        if(cantidad === 0){
                            $("#botonGuardar").css("display","none");
                            $("#btnEliminar").css("display","none");
                        }
                    });
                });
            
        </script>
    </body>
</html>
