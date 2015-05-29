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
        <link src="../css/style.css"></link>
        <script src="../js/jquery-1.11.3.min.js"></script>
    </head>
    <body>
        <h1>Datos de traslado</h1>
        <s:a action="AlojamientoForm">Agregar datos de alojamiento/combustible</s:a>
        
        
           <s:form action="DatosTrasladoPreparar" theme="simple">
               <table border="1">
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
                        <td><s:textfield name="trasladoFechaHoraSalida" label="Fecha y hora de salida" /></td>
                        <td><s:textfield name="trasladofechaHoraRegreso" label="Fecha y hora de regreso" /></td>
                        <td><s:textarea name="trasladoComprobantesObservaciones" label="Observaciones" /></td>
                     </tr>
                    </tbody>
             </table>

                
            
            <s:submit value="Guardar" />
        </s:form>
            <button id="btnAgregar">Agregar Elemento</button>
        <script>
            $(document).ready(function () 
                {
                    $("#btnAgregar").click(function() 
                    {
                        var elementoNuevo = $("#duplicar").clone();
                        $("#duplicar").after(elementoNuevo);
                    });
                });
        </script>
    </body>
</html>
