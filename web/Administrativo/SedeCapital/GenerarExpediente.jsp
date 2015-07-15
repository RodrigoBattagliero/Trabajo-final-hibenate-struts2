<%-- 
    Document   : GenerarExpediente
    Created on : May 16, 2015, 3:48:45 AM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../../bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="../../js/datetimepicker-master/jquery.datetimepicker.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <s:include value="../partes/menu.jsp" />
            <ol class="breadcrumb">
                <li><a href="#">Expedientes</a></li>
                <li class="active">Generar expediente</li>
            </ol>
            <h1 class="page-header">Expediente</h1>
            <s:actionerror />
            <s:fielderror />
            <s:form action="GenerarExpediente" theme="simple" method="post" class="form-inline">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr class="success">
                        <th>Numero de solicitud</th>
                        <th>Docente</th>
                        <th>Fecha de presentacion</th>
                        <th>Estado</th>
                        <th>Confirmado</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.RegistrosAConfirmar" var="list">
                        <tr>
                            <td><s:property value="#list[2].numeroSolicitud" /></td>
                            <td><s:property value="#list[3].apellido" />, <s:property value="#list[3].nombre" /></td>
                            <td><s:property value="#list[2].fechaAlta" /></td>
                            <td><s:property value="#list[1].nombre" /></td>
                            <td>
                                <s:checkbox name="%{#list[2].id}" value="true" />
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
                <tfoot>
                    <tr>
                        <td>
                            <label>Fecha</label>
                            <s:textfield name="fecha" label="Fecha" class="form-control fecha"  />
                        </td>
                        <td>
                            <label>N° expediente</label>
                            <s:textfield name="numeroExpediente" label="N de expediente" class="form-control" />
                        </td>
                        <td colspan="5"><s:submit value="Confirmar" class="btn btn-primary" /></td>
                    </tr>
                </tfoot>
            </table>
            </s:form>
            
            <s:include value="../partes/footer.jsp" />
        </div>
        <script src="../../js/datetimepicker-master/jquery.datetimepicker.js"></script>
        <script>
            $('.fecha').datetimepicker({lang : 'es',format: 'd/m/Y'});
        </script>
    </body>
</html>
