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
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <s:form action="DatosAlojamientoPreparar" theme="simple">
                   <table class="table table-striped">
                        <thead>
                                <tr>
                                    <td>Importe</td>
                                    <td>Número de comprobante</td>
                                    <td>Proveedor</td>
                                    <td>Area</td>
                                    <td>Descripción</td>
                                    <td>Observaciones</td>
                                </tr>
                        </thead>
                        <tbody>
                            <tr >
                                <td><s:textfield name="alojamientoComprobantesImporte" label="Importe" /></td>
                                <td><s:textfield name="alojamientoComprobantesNumeroComprobante" label="Número de comprobante" /></td>
                                <td><s:textfield name="alojamientoComprobantesNroveedor" label="Proveedor" /></td>
                                <td>
                                    <select name="alojamientoTipo" id="DatosAlojamientoPreparar_alojamientoTipo">
                                        <option value="1">Comida</option>
                                        <option value="2">Combustible</option>
                                        <option value="3">Alojamiemto</option>
                                    </select>
                                </td>
                                <td><s:textarea name="alojamientoDescripcion" label="Descripción" /></td>
                                <td><s:textarea name="alojamientoComprobantesObservaciones" label="Observaciones" /></td>
                            </tr>
                        </tbody>
                </table>
                <s:submit value="Guardar" />
            </s:form>
            <button id="btnAgregar">Agregar Elemento</button>
            
            <script src="../js/jquery-1.11.3.min.js"></script>
            <script>
                $(document).ready(function () 
                    {
                        $("#btnAgregar").click(function() 
                        {
                            var tr = '<tr id="duplicar"><td><input type="text" name="alojamientoComprobantesImporte" value="" id="DatosAlojamientoPreparar_alojamientoComprobantesImporte"/></td><td><input type="text" name="alojamientoComprobantesNumeroComprobante" value="" id="DatosAlojamientoPreparar_alojamientoComprobantesNumeroComprobante"/></td><td><input type="text" name="alojamientoComprobantesNroveedor" value="" id="DatosAlojamientoPreparar_alojamientoComprobantesNroveedor"/></td><td><select name="alojamientoTipo" id="DatosAlojamientoPreparar_alojamientoTipo"><option value="1">Comida</option><option value="2">Combustible</option><option value="3">Alojamiemto</option></select></td><td><textarea name="alojamientoDescripcion" cols="" rows="" id="DatosAlojamientoPreparar_alojamientoDescripcion"></textarea></td><td><textarea name="alojamientoComprobantesObservaciones" cols="" rows="" id="DatosAlojamientoPreparar_alojamientoComprobantesObservaciones"></textarea></td></tr>';
                            $("tbody").append(tr);
                        });
                    });
            </script>
        </div>
    </body>
</html>
