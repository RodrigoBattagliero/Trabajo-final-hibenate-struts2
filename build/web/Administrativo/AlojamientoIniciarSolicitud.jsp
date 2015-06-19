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
            
            <h1 class="page-header">Datos de alojamiento / combustible / comida</h1>
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
                            <!--
                            <tr >
                                <td><s:textfield name="alojamientoComprobantesImporte" label="Importe" class="form-control" /></td>
                                <td><s:textfield name="alojamientoComprobantesNumeroComprobante" label="Número de comprobante" class="form-control" /></td>
                                <td><s:textfield name="alojamientoComprobantesNroveedor" label="Proveedor" class="form-control" /></td>
                                <td>
                                    <select name="alojamientoTipo" id="DatosAlojamientoPreparar_alojamientoTipo" class="form-control">
                                        <option value="1">Comida</option>
                                        <option value="2">Combustible</option>
                                        <option value="3">Alojamiemto</option>
                                    </select>
                                </td>
                                <td><s:textarea name="alojamientoDescripcion" label="Descripción" class="form-control" /></td>
                                <td><s:textarea name="alojamientoComprobantesObservaciones" label="Observaciones" class="form-control" /></td>
                            </tr>
                            -->
                        </tbody>
                </table>
                <s:submit value="Guardar" class="btn" />
            </s:form>
            <s:a action="AlojamientoBack" cssClass="btn" >Volver</s:a>
            <button id="btnAgregar" class="btn">Agregar Elemento</button>
            <button id="btnEliminar" class="btn">Eliminar elemento</button>
            <%@include file="partes/footer.jsp" %>
            <script>
                $(document).ready(function () 
                    {
                        $("#btnAgregar").click(function() 
                        {
                            var tr = '<tr class="fila" ><td><input type="text" name="alojamientoComprobantesImporte" value="" id="DatosAlojamientoPreparar_alojamientoComprobantesImporte" class="form-control"/></td><td><input type="text" name="alojamientoComprobantesNumeroComprobante" value="" id="DatosAlojamientoPreparar_alojamientoComprobantesNumeroComprobante" class="form-control"/></td><td><input type="text" name="alojamientoComprobantesNroveedor" value="" id="DatosAlojamientoPreparar_alojamientoComprobantesNroveedor" class="form-control"/></td><td><select name="alojamientoTipo" id="DatosAlojamientoPreparar_alojamientoTipo" class="form-control"><option value="1">Comida</option><option value="2">Combustible</option><option value="3">Alojamiemto</option></select></td><td><textarea name="alojamientoDescripcion" cols="" rows="" id="DatosAlojamientoPreparar_alojamientoDescripcion" class="form-control"></textarea></td><td><textarea name="alojamientoComprobantesObservaciones" cols="" rows="" id="DatosAlojamientoPreparar_alojamientoComprobantesObservaciones" class="form-control"></textarea></td></tr>';
                            $("tbody").append(tr);
                        });
                        $("#btnEliminar").click(function()
                         {
                             $(".fila:last-child").remove();
                         });
                    });
            </script>
        </div>
    </body>
</html>
