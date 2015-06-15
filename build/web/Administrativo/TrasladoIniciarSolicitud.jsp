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
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="../js/datetimepicker-master/jquery.datetimepicker.css"/>
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
                <h1>Datos de traslado</h1>
                <s:a action="AlojamientoForm" class="btn">Agregar datos de alojamiento/combustible</s:a>


                   <s:form action="DatosTrasladoPreparar" theme="simple">
                       <table class="table table-striped">
                            <thead>
                                <tr>
                                    <td>Importe</td>
                                    <td>Número de comprobante</td>
                                    <td>Proveedor</td>
                                    <td>Desde</td>
                                    <td>Hasta</td>
                                    <td>Fecha y hora de salida</td>
                                    <td>Fecha y hora de regreso</td>
                                    <td>Observaciones</td>
                                </tr>
                             </thead>
                            <tbody>
                             <tr id="duplicar">
                                <td><s:textfield name="trasladoComprobantesImporte" label="Importe" /></td>
                                <td><s:textfield name="trasadoComprobantesNumeroComprobante" label="Número de comprobante" /></td>
                                <td><s:textfield name="trasladoComprobantesProveedor" label="Proveedor" /></td>
                                <td><s:textfield name="trasladoDesde" label="Desde" /></td>
                                <td><s:textfield name="trasladoHasta" label="Hasta" /></td>
                                <td><s:textfield name="trasladoFechaHoraSalida" label="Fecha y hora de salida" class="fecha" /></td>
                                <td><s:textfield name="trasladofechaHoraRegreso" label="Fecha y hora de regreso" class="fecha" /></td>
                                <td><s:textarea name="trasladoComprobantesObservaciones" label="Observaciones" /></td>
                             </tr>
                            </tbody>
                     </table>



                    <s:submit value="Guardar" class="btn" />
                    <s:a action="RegistroUnicoForm" class="btn">Continuar</s:a>
                </s:form>
                    <button id="btnAgregar" class="btn">Agregar Elemento</button>
        </div>
        <script src="../js/jquery-1.11.3.min.js"></script>
        <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
        <script>
            $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y H:m'});
        </script>
        <script>
            $(document).ready(function () 
                {  
                    $("#btnAgregar").click(function() 
                    {
                        var tr = '<tr ><td><input type="text" name="trasladoComprobantesImporte" value="" id="DatosTrasladoPreparar_trasladoComprobantesImporte"/></td><td><input type="text" name="trasadoComprobantesNumeroComprobante" value="" id="DatosTrasladoPreparar_trasadoComprobantesNumeroComprobante"/></td><td><input type="text" name="trasladoComprobantesProveedor" value="" id="DatosTrasladoPreparar_trasladoComprobantesProveedor"/></td><td><input type="text" name="trasladoDesde" value="" id="DatosTrasladoPreparar_trasladoDesde"/></td><td><input type="text" name="trasladoHasta" value="" id="DatosTrasladoPreparar_trasladoHasta"/></td><td><input type="text" name="trasladoFechaHoraSalida" value="" id="DatosTrasladoPreparar_trasladoFechaHoraSalida"/></td><td><input type="text" name="trasladofechaHoraRegreso" value="" id="DatosTrasladoPreparar_trasladofechaHoraRegreso"/></td><td><textarea name="trasladoComprobantesObservaciones" cols="" rows="" id="DatosTrasladoPreparar_trasladoComprobantesObservaciones"></textarea></td></tr>';
                        $("tbody").append(tr);
                    });
                });
        </script>
    </body>
</html>
