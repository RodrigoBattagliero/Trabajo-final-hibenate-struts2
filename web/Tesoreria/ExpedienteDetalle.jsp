<%-- 
    Document   : expedienteDetalle
    Created on : 17-Jun-2015, 23:46:08
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
        <title>Tesorería</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <ol class="breadcrumb">
                <li><a href="#">Inicio</a></li>
                <li><a href="#">Expedientes</a></li>
                <li class="active">Detalle</li>
            </ol>
            <table class="table table-bordered table-hover">
                <thead>
                    <tr class="success">
                        <th>N° solicitud</th>
                        <th>Docente</th>
                        <th>Fecha inicio</th>
                        <th>Importe declarado</th>
                        <th>Importe reconocido</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="detalle" var="sol">
                        <tr>
                            <td><s:property value="#sol[0].numeroSolicitud" /></td>
                            <td><s:property value="#sol[1].apellido" />,<s:property value="#sol[1].nombre" /></td>
                            <td><s:property value="#sol[0].fechaAlta" /></td>
                            <td><s:property value="#sol[2].importeDeclarado" /></td>
                            <td><s:property value="#sol[2].reconocimientoImporteTotal" /></td>
                        </tr>
                    </s:iterator>
                </tbody>
                <tfoot>
                    <tr>
                        <td>
                            <s:a action="ConfirmarTodos" class="btn btn-primary">Confirmar todos</s:a>
                        </td>
                    </tr>
                </tfoot>
            </table>
            
            <s:include value="partes/footer.jsp" />
        </div>
    </body>
</html>
