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
        <script src="../js/jquery-1.11.3.min.js"></script>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <h1 class="page-header">Datos de traslado</h1>
            <s:actionerror />
            <s:fielderror />
            <s:a action="AlojamientoForm" class="btn">Agregar datos de alojamiento/combustible</s:a>

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
                                <td>Acciones</td>
                            </tr>
                         </thead>
                        <tbody>
                            <s:iterator value="entities" var="entity">
                            <tr>
                               <td><s:property value="#entity.comprobantes.importe"  /></td>
                               <td><s:property value="#entity.comprobantes.numeroComprobante"  /></td>
                               <td><s:property value="#entity.comprobantes.proveedor"  /></td>
                               <td><s:property value="#entity.desde" /></td>
                               <td><s:property value="#entity.hasta" /></td>
                               <td><s:property value="#entity.fechaHoraSalida"  /></td>
                               <td><s:property value="#entity.fechaHoraRegreso"  /></td>
                               <td><s:property value="#entity.comprobantes.observaciones"  /></td>
                               <td>
                                    <s:url var="url" action="ComprobantesTrasladoUpdateSelected">
                                        <s:param name="idComprobanteSelected" value="%{#entity.id}"></s:param>
                                    </s:url>
                                    <s:a href="%{url}" class="btn" >Editar</s:a>
                               </td>
                            </tr>
                            </s:iterator>
                        </tbody>
                 </table>


            <hr />
            <s:if test="entity.id" >
             <s:form action="ComprobantesTrasladoUpdate" theme="simple">
                   <table class="table table-striped">
                        <thead>
                            <tr>
                                <td>Id</td>
                                <td>Id</td>
                                <td>Importe</td>
                                <td>Número de comprobante</td>
                                <td>Proveedor</td>
                                <td>Desde</td>
                                <td>Hasta</td>
                                <td>Fecha y hora de salida</td>
                                <td>Fecha y hora de regreso</td>
                                <td>Observaciones</td>
                                <td>Acciones</td>
                            </tr>
                         </thead>
                        <tbody>
                            <tr>
                                <td><s:textfield name="entity.comprobantes.id" label="Importe" disabled="disable" class="form-control" /></td>
                               <td><s:textfield name="entity.id" label="Importe" disabled="disable"  class="form-control" /></td>
                               <td><s:textfield name="entity.comprobantes.importe" label="Importe"  class="form-control" /></td>
                               <td><s:textfield name="entity.comprobantes.numeroComprobante" label="Número de comprobante"  class="form-control" /></td>
                               <td><s:textfield name="entity.comprobantes.proveedor" label="Proveedor"  class="form-control" /></td>
                               <td><s:textfield name="entity.desde" label="Desde" class="form-control" /></td>
                               <td><s:textfield name="entity.hasta" label="Hasta" class="form-control"/></td>
                               <td><s:textfield name="entity.fechaHoraSalida" label="Fecha y hora de salida"  class="form-control fecha" /></td>
                               <td><s:textfield name="entity.fechaHoraRegreso" label="Fecha y hora de regreso"  class="form-control fecha" /></td>
                               <td><s:textarea name="entity.comprobantes.observaciones" label="Observaciones"  class="form-control" /></td>
                               <td><s:submit value="Modificar" class="btn" /></td>
                            </tr>
                        </tbody>
                 </table>

            </s:form>
             </s:if>
            <s:a href="ComprobantesAlojameintoUpdateForm" class="btn">Continuar</s:a>
            
            <%@include file="partes/footer.jsp" %>
        </div>
                
        <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
        <script>
            $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y H:m'});
        </script>
    </body>
</html>
