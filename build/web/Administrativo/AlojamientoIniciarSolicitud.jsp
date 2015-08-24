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
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <ol class="breadcrumb">
                <li><a href="#">Inicio</a></li>
                <li><a href="#">Iniciar solicitud</a></li>
                <li class="active">Datos de alojamiento / combustible / comida</li>
            </ol>
            <div class="row">
               <div class="col-sm-12">
                <h1 class="page-header">Datos de alojamiento / combustible / comida</h1>
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
                <s:form action="DatosAlojamientoPreparar" theme="simple">
                       <table class="table table-striped">
                            <thead>
                                    <tr>
                                        <td>Importe</td>
                                        <td>Número de comprobante</td>
                                        <td>Proveedor</td>
                                        <td>Tipo</td>
                                        <td>Descripción</td>
                                        <td>Observaciones</td>
                                    </tr>
                            </thead>
                            <tbody id="datos">

                            </tbody>

                            <tfoot>
                                <tr>
                                    <td><button type="button" id="btnAgregar" class="btn btn-success">Agregar fila</button></td>
                                    <td><button type="button" id="btnEliminar" class="btn btn-danger" style="display:none">Eliminar última fila</button></td>
                                    <td><s:a action="AlojamientoBack" cssClass="btn btn-warning" >Volver sin agregar datos</s:a></td>
                                    <td><s:submit value="Guardar" id="botonGuardar" class="btn btn-primary" cssStyle="display:none" /></td>
                                </tr>
                            </tfoot>
                    </table>

                </s:form>
                </div>
            </div>
            <%@include file="partes/footer.jsp" %>
            <script>
                $(document).ready(function () 
                    {
                        $("#btnAgregar").click(function() 
                        {
                            var tr = '<tr class="fila" ><td><div class="input-group"><div class="input-group-addon">$</div><input type="text" name="alojamientoComprobantesImporte" value="" id="DatosAlojamientoPreparar_alojamientoComprobantesImporte" class="form-control"/></div></td><td><input type="text" name="alojamientoComprobantesNumeroComprobante" value="" id="DatosAlojamientoPreparar_alojamientoComprobantesNumeroComprobante" class="form-control"/></td><td><input type="text" name="alojamientoComprobantesNroveedor" value="" id="DatosAlojamientoPreparar_alojamientoComprobantesNroveedor" class="form-control"/></td><td><select name="alojamientoTipo" id="DatosAlojamientoPreparar_alojamientoTipo" class="form-control"><option value="1">Comida</option><option value="2">Combustible</option><option value="3">Alojamiemto</option></select></td><td><textarea name="alojamientoDescripcion" cols="" rows="" id="DatosAlojamientoPreparar_alojamientoDescripcion" class="form-control"></textarea></td><td><textarea name="alojamientoComprobantesObservaciones" cols="" rows="" id="DatosAlojamientoPreparar_alojamientoComprobantesObservaciones" class="form-control"></textarea></td></tr>';
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
        </div>
    </body>
</html>
