<%-- 
    Document   : Buscar
    Created on : 03-Jun-2015, 19:00:09
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../js/datetimepicker-master/jquery.datetimepicker.css"/>
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <title></title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <s:form action="Consultar" theme="simple">
                <div class="form-group input-group">
                    <label>Nombre del docente</label>
                    <s:textfield name="nombreDocente" label="Nombre del docente" class="form-control" />
                </div>
                <div class="form-group input-group">
                    <label>Apellido del docente</label>
                    <s:textfield name="apellidoDocente" label="Apellido del docente" class="form-control" />
                </div>
                <div class="form-group input-group">
                    <label>Fecha de presentación</label>
                    <s:textfield name="fechaDePresentacion" label="Fecha de presentación" class="form-control fecha" />
                </div>
                <div class="form-group input-group">
                    <s:submit value="Consultar" class="btn" />
                </div>
            </s:form>
            <table class="table table-striped">
                <thead>
                    <td>N solicitud</td>
                    <td>Docente</td>
                    <td>Fecha de presentación</td>
                    <td>Acciones</td>
                </thead>
                <tbody>
                    <s:iterator value="entities" var="list">
                        <tr>
                            <td><s:property value="#list[0].solicitudes.numeroSolicitud" /></td>
                            <td><s:property value="#list[1].nombre" /></td>
                            <td><s:property value="#list[0].solicitudes.fechaAlta" /></td>
                            <td>
                                <s:url var="url1" action="ConsultarDetalle">
                                    <s:param name="idSolicitudSelected" value="%{#list[0].solicitudes.id}"></s:param>
                                </s:url>
                                <s:url var="url2" action="EditarForm">
                                    <s:param name="idSolicitudSelected" value="%{#list[0].solicitudes.id}"></s:param>
                                </s:url>
                                <s:a href="%{url1}" >Detalle</s:a>
                                <s:a href="%{url2}" >Editar</s:a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>
            
        <%@include file="partes/footer.jsp" %>
        <script src="../js/datetimepicker-master/jquery.datetimepicker.js"></script>
        <script>
            $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y'});
        </script>
    </body>
</html>

