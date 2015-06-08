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
        <hr />
               <table>
                    <thead>
                            <tr>
                                <td>Importe</td>
                                <td>Número de comprobante</td>
                                <td>Proveedor</td>
                                <td>Area</td>
                                <td>Descripción</td>
                                <td>Observaciones</td>
                                <td>Acciones</td>
                            </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="entities" var="alojamiento">
                            <tr>
                                <td><s:property value="#alojamiento.comprobantes.importe"  /></td>
                                <td><s:property value="#alojamiento.comprobantes.numeroComprobante" /></td>
                                <td><s:property value="#alojamiento.comprobantes.proveedor"  /></td>
                                <td>
                                    <s:if test="#alojamiento.tipo == 1">
                                        Comida
                                    </s:if>
                                    <s:if test="#alojamiento.tipo == 2">
                                        Combustible
                                    </s:if>
                                    <s:if test="#alojamiento.tipo == 3">
                                        Alojamiento
                                    </s:if>
                                </td>
                                <td><s:property value="#alojamiento.descripcion"  /></td>
                                <td><s:property value="#alojamiento.comprobantes.observaciones" /></td>
                                <td>
                                    <s:url var="url" action="ComprobantesAlojameintoUpdateSelected">
                                        <s:param name="idComprobanteSelected" value="%{#alojamiento.id}"></s:param>
                                    </s:url>
                                    <s:a href="%{url}" >Editar</s:a>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
            </table>
        <hr />
        <s:if test="entity.id" >
        <s:form action="ComprobantesAlojamientoUpdate" theme="simple">
               <table>
                    <thead>
                            <tr>
                                <td>Id</td>
                                <td>Id</td>
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
                            <td><s:textfield name="entity.comprobantes.id" /></td>
                            <td><s:textfield name="entity.id" /></td>
                            <td><s:textfield name="entity.comprobantes.importe" label="Importe" /></td>
                            <td><s:textfield name="entity.comprobantes.numeroComprobante" label="Número de comprobante" /></td>
                            <td><s:textfield name="entity.comprobantes.proveedor" label="Proveedor" /></td>
                            <td>
                                <s:select list="#@java.util.TreeMap@{'1':'Comida', '2':'Combustible','3':'Alojamiento'}" name="entity.tipo" value="entity.tipo" label="Tipo" />
                            </td>
                            <td><s:textarea name="entity.descripcion" label="Descripción" /></td>
                            <td><s:textarea name="entity.comprobantes.observaciones" label="Observaciones" /></td>
                        </tr>
                    </tbody>
            </table>
            <s:submit value="Guardar" />
        </s:form>
        <hr />
        </s:if>
        <s:a action="DesignacionesUpdateForm">Continuar</s:a>
    </body>
</html>
